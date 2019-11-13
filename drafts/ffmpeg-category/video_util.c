//
// Created by tpout on 19-4-29.
//

#include "video_util.h"

static void *trans_420sp_to_420p(uint8_t *dst, uint8_t *src, size_t size);

//static void rotate_yuv420p_90(uint8_t *dst, uint8_t *src, int w, int h);

static void save_420p(uint8_t *yuv420p, size_t width, size_t height, const char *path);

static void save_420p_to_h264(uint8_t *src, int width, int height, int frame_rate, int frame_count, const char *path);

//注意这里的yuv_src 和 yuv_temp 转化和赋值
void save_420sp_to_h264(uint8_t *yuv_src, int rotate_angle, bool flip, size_t width, size_t height, int frame_rate, int frame_count, const char *path_dst) {
    uint8_t *yuv_temp = malloc(width * height * 3 / 2);
    if (NULL == yuv_temp) {
        LOG_D("malloc mid420 fail ! ");
        return;
    }
    trans_420sp_to_420p(yuv_temp, yuv_src, width * height);
//    rotate_yuv420p_90(yuv_src, yuv_temp, width, height);
    if (flip) {
        //    flip_yuv420p_90(mid420p, src, width, height);
    }
//    save_420p_to_h264(yuv_temp, width, height, frame_rate, frame_count, path_dst);
    free(yuv_temp);
}

//注意这里的yuv_src 和 yuv_temp 转化和赋值
void save_420sp_to_420p(uint8_t *yuv_src, int rotate_angle, bool flip, size_t width, size_t height, int frame_rate, int frame_count, const char *path_dst) {
    uint8_t *yuv_temp = malloc(width * height * 3 / 2);
    if (NULL == yuv_temp) {
        LOG_D("malloc mid420 fail ! ");
        return;
    }
    trans_420sp_to_420p(yuv_temp, yuv_src, width * height);
//    rotate_yuv420p_90(yuv_src, yuv_temp, width, height);
    if (flip) {
        //    flip_yuv420p_90(mid420p, src, width, height);
    }
    save_420p(yuv_temp, width, height, path_dst);
    free(yuv_temp);
}

void save_420p(uint8_t *yuv420p, size_t width, size_t height, const char *path) {
    FILE *f = fopen(path, "ab+");
    if (!f) {
        LOG_D("Could not open %s\n", path);
        return;
    }
    fwrite(yuv420p, 1, width * height * 3 / 2, f);
    fclose(f);
}

static void rotate_yuv420p_90(uint8_t *dst, uint8_t *src, int width, int height) {
    int iSum = width*height;
    int k=0;
    for ( int i=width; i>0; i-- ) {
        for ( int j=iSum; j>0; j-=width ) {
            dst[k] = src[j-i];
            k++;
        }
    }
    width  /= 2;
    height /= 2;
    int iSumu=width*height;
    for ( int i=width; i>0; i-- ) {
        for ( int j=iSumu; j>0; j-=width ) {
            dst[k] = src[iSum+j-i];
            k++;
        }
    }
    iSum += width*height;
    int iSumv=width*height;
    for ( int i=width; i>0; i-- ) {
        for ( int j=iSumv; j>0; j-=width ) {
            dst[k] = src[iSum+j-i];
            k++;
        }
    }
}

static void *trans_420sp_to_420p(uint8_t *yuv420p, uint8_t *yuv420sp, size_t size) {

    uint8_t *uc_y = yuv420sp;
    uint8_t *uc_vu = yuv420sp + size;

    uint8_t *uc_y_tmp = yuv420p;
    uint8_t *uc_u_tmp = yuv420p + size;
    uint8_t *uc_v_tmp = yuv420p + size * 5 / 4;

    memcpy(uc_y_tmp, uc_y, size);

    for (int j = 0, i = 0; j < size / 2; j += 2, i++) {
        uc_v_tmp[i] = uc_vu[j];
        uc_u_tmp[i] = uc_vu[j + 1];
    }
}

static void save_420p_to_h264(uint8_t *src, int width, int height, int frame_rate, int frame_count, const char *path) {

    const char *codec_name = "libx264";

    int result = 0;

    FILE *fOut;

    fOut = fopen(path, "ab+");
    if (!fOut) {
        LOG_D("Could not open %s\n", path);
        result = -1;
        goto fail;
    }

    AVFrame *frame;
    frame = av_frame_alloc();
    if (!frame) {
        LOG_D("!frame\n");
        result = -1;
        goto fail1;
    }
    frame->width = width;
    frame->height = height;
    frame->format = AV_PIX_FMT_YUV420P;
    av_frame_get_buffer(frame, 32);//官网

    AVPacket *pkt;
    pkt = av_packet_alloc();
    if (!pkt) {
        LOG_D("!pkt\n");
        result = -1;
        goto fail2;
    }

    const AVCodec *codec;
    AVCodecContext *codecContext = NULL;
    codec = avcodec_find_encoder_by_name(codec_name);
    if (!codec) {
        LOG_D("Codec '%s' not found\n", codec_name);
        result = -1;
        goto fail3;
    }

    codecContext = avcodec_alloc_context3(codec);
    if (!codecContext) {
        LOG_D("Could not allocate video codec context\n");
        result = -1;
        goto fail3;
    }

    /* put sample parameters */
    codecContext->bit_rate = 400 * width * height / (1920 * 1080) * 10000;//固定编码器无效吧？
    /* resolution must be a multiple of two */
    codecContext->width = frame->width;//VIDEO_WIDTH
    codecContext->height = frame->height;//VIDEO_HEIGHT
    /* frames per second */
    codecContext->time_base = (AVRational) {1, frame_rate};//MAX_FRAME_RATE
    codecContext->framerate = (AVRational) {frame_rate, 1};//这个不确定，应该和time_base 保持一致吧？

    /* emit one intra frame every ten frames
     * check frame pict_type before passing frame
     * to encoder, if frame->pict_type is AV_PICTURE_TYPE_I
     * then gop_size is ignored and the output of encoder
     * will always be I frame irrespective to gop_size
     */
    codecContext->gop_size = 10;
    codecContext->max_b_frames = 1;
    codecContext->pix_fmt = AV_PIX_FMT_YUV420P;

    if (codec->id == AV_CODEC_ID_H264)
        av_opt_set(codecContext->priv_data, "preset", "slow", 0);

    /* open it */
    result = avcodec_open2(codecContext, codec, NULL);
    if (avcodec_open2(codecContext, codec, NULL) < 0) {
        LOG_D("Could not open codec: %s\n", av_err2str(result));
        result = -1;
        goto fail4;
    }

//    //读一帧数据出来
    fflush(stdout);
    result = av_frame_make_writable(frame);
    if (result < 0) {
        LOG_D("Error make frame writable \n");
        goto fail;
    }

    frame->data[0] = src;
    frame->data[1] = src + frame->linesize[0] * frame->height;
    frame->data[2] = src + frame->linesize[0] * frame->height + frame->linesize[1] * frame->height / 2;
//    memcpy(frame->data[0], src, frame->linesize[0] * frame->height);
//    memcpy(frame->data[1], src + frame->linesize[0] * frame->height, frame->linesize[1] * frame->height / 2);
//    memcpy(frame->data[2], src + frame->linesize[0] * frame->height + frame->linesize[1] * frame->height / 2, frame->linesize[2] * frame->height / 2);

    frame->pts = frame_count;
    LOG_D("编码第%d帧开始", frame_count);
    //这里还是的用安全队列处理
    encode_and_save(codecContext, frame, pkt, fOut);
    encode_and_save(codecContext, NULL, pkt, fOut);
    LOG_D("编码第%d帧结束", frame_count);;

    fail4:
    avcodec_free_context(&codecContext);
    fail3:
//    av_packet_free(&pkt);
    fail2:
    av_frame_free(&frame);
    fail1:
    fclose(fOut);
    fail:
    if (result < 0) {
        LOG_D("failed by some reason of above");
    }
}

//比较耗时，子线程执行把
void resave_420p_to_h264(uint8_t *src, int width, int height, int frame_rate, int frame_count, const char *path_src, const char *path_dst) {

    const char *codec_name = "libx264";

    int result = 0;

    FILE *fOut;

    fOut = fopen(path_dst, "ab+");
    if (!fOut) {
        LOG_D("Could not open %s\n", path_dst);
        result = -1;
        goto fail;
    }

    AVFrame *frame;
    frame = av_frame_alloc();
    if (!frame) {
        LOG_D("!frame\n");
        result = -1;
        goto fail1;
    }
    frame->width = width;
    frame->height = height;
    frame->format = AV_PIX_FMT_YUV420P;
    av_frame_get_buffer(frame, 32);//官网

    AVPacket *pkt;
    pkt = av_packet_alloc();
    if (!pkt) {
        LOG_D("!pkt\n");
        result = -1;
        goto fail2;
    }

    const AVCodec *codec;
    AVCodecContext *codecContext = NULL;
    codec = avcodec_find_encoder_by_name(codec_name);
    if (!codec) {
        LOG_D("Codec '%s' not found\n", codec_name);
        result = -1;
        goto fail3;
    }

    codecContext = avcodec_alloc_context3(codec);
    if (!codecContext) {
        LOG_D("Could not allocate video codec context\n");
        result = -1;
        goto fail3;
    }

    /* put sample parameters */
    codecContext->bit_rate = 400 * width * height / (1920 * 1080) * 10000;//固定编码器无效吧？
    /* resolution must be a multiple of two */
    codecContext->width = frame->width;//VIDEO_WIDTH
    codecContext->height = frame->height;//VIDEO_HEIGHT
    /* frames per second */
    codecContext->time_base = (AVRational) {1, frame_rate};//MAX_FRAME_RATE
    codecContext->framerate = (AVRational) {frame_rate, 1};//这个不确定，应该和time_base 保持一致吧？

    /* emit one intra frame every ten frames
     * check frame pict_type before passing frame
     * to encoder, if frame->pict_type is AV_PICTURE_TYPE_I
     * then gop_size is ignored and the output of encoder
     * will always be I frame irrespective to gop_size
     */
    codecContext->gop_size = 10;
    codecContext->max_b_frames = 1;
    codecContext->pix_fmt = AV_PIX_FMT_YUV420P;

    if (codec->id == AV_CODEC_ID_H264)
        av_opt_set(codecContext->priv_data, "preset", "slow", 0);

    /* open it */
    result = avcodec_open2(codecContext, codec, NULL);
    if (avcodec_open2(codecContext, codec, NULL) < 0) {
        LOG_D("Could not open codec: %s\n", av_err2str(result));
        result = -1;
        goto fail4;
    }

    FILE *fSrc;

    fSrc = fopen(path_src, "rb+");
    if (!fSrc) {
        LOG_D("Could not open %s\n", path_dst);
        result = -1;
        goto fail4;
    }

    int readSize = 0;
    int frameCount = 0;
    while (1) {
        //读一帧数据出来
        fflush(stdout);
        result = av_frame_make_writable(frame);
        if (result < 0) {
            LOG_D("Error make frame writable \n");
            goto fail5;
        }

        readSize = fread(frame->data[0], 1, frame->linesize[0] * frame->height, fSrc);

        if (readSize == 0) {
            fprintf(stdout, "end of file\n");
            LOG_D("end of file when read %d\n", 2048);
            break;
        }

        readSize = fread(frame->data[1], 1, frame->linesize[1] * frame->height / 2, fSrc);
        readSize = fread(frame->data[2], 1, frame->linesize[2] * frame->height / 2, fSrc);

        frame->pts = frameCount;


        LOG_D("视频--编码第%d帧开始", frameCount);
        encode_and_save(codecContext, frame, pkt, fOut);
        LOG_D("视频--编码第%d帧结束", frameCount);
        frameCount++;
    }
    encode_and_save(codecContext, NULL, pkt, fOut);

    fail5:
    fclose(fSrc);
    fail4:
    avcodec_free_context(&codecContext);
    fail3:
    av_packet_unref(pkt);
    fail2:
    av_frame_free(&frame);
    fail1:
    fclose(fOut);
    fail:
    if (result < 0) {
        LOG_D("failed by some reason of above");
    }

}