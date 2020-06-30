//
// Created by tpout on 19-4-29.
//
#include "audio_util.h"

static int check_sample_fmt(const AVCodec *codec, enum AVSampleFormat sample_fmt);

static int select_sample_rate(const AVCodec *codec);

static int select_channel_layout(const AVCodec *codec);

void save_pcm(const char* pcm, int length, const char* out_path){
    LOG_D("start write new pcm frame :  %d", length);

    FILE *store = fopen(out_path, "ab+");
    fwrite(pcm, 1, length, store);
    fclose(store);

    LOG_D("end write new pcm frame");
}

void encode_pcm_to_aac(const char *src_file, const char *out_file) {
    int result = 0;

    const AVCodec *codec;
    AVCodecContext *codecContext = NULL;

    codec = avcodec_find_encoder_by_name("libfdk_aac");
    if (!codec) {
        result = -1;
        LOG_D("Codec not found\n");
        goto fail;
    }
    codecContext = avcodec_alloc_context3(codec);
    if (!codecContext) {
        LOG_D("Could not allocate audio codec context\n");
        result = -1;
        goto fail;
    }

    codecContext->sample_fmt = AV_SAMPLE_FMT_S16;
    if (!check_sample_fmt(codec, codecContext->sample_fmt)) {
        result = -1;
        LOG_D("Encoder does not support sample format %s",
              av_get_sample_fmt_name(codecContext->sample_fmt));
        goto fail1;
    }

    codecContext->sample_rate = 44100;
    codecContext->channel_layout = av_get_default_channel_layout(1);
    codecContext->channels = 1;
    LOG_D("codecContext->channels %d\n", codecContext->channels);

    /* open it */
    if (avcodec_open2(codecContext, codec, NULL) < 0) {
        result = -1;
        LOG_D("Could not open codec\n");
        goto fail1;
    }

    FILE *fSrc;
    fSrc = fopen(src_file, "rb+");
    if (!fSrc) {
        LOG_D("Could not open %s\n", src_file);
        result = -1;
        goto fail1;
    }

    AVPacket *pkt;
    pkt = av_packet_alloc();
    if (!pkt) {
        result = -1;
        LOG_D("could not allocate the packet\n");
        goto fail2;
    }

    AVFrame *frame;
    frame = av_frame_alloc();
    if (!frame) {
        result = -1;
        LOG_D("Could not allocate audio frame\n");
        goto fail3;
    }

    frame->nb_samples = codecContext->frame_size;
    frame->format = codecContext->sample_fmt;
    frame->channel_layout = codecContext->channel_layout;

    result = av_frame_get_buffer(frame, 0);
    if (result < 0) {
        result = -1;
        LOG_D("Could not allocate audio data buffers\n");
        goto fail4;
    }

    FILE *fOut;
    fOut = fopen(out_file, "ab+");
    if (!fOut) {
        LOG_D("Could not open %s\n", out_file);
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

        readSize = fread(frame->data[0], 1, 2048, fSrc);

        if (readSize == 0) {
            fprintf(stdout, "end of file\n");
            LOG_D("end of file when read %d\n", 2048);
            frameCount++;
            break;
        }

        LOG_D("音频--编码第%d帧开始", frameCount);
        encode_and_save(codecContext, frame, pkt, fOut);
        LOG_D("音频--编码第%d帧结束", frameCount);
        frameCount++;

    }
    encode_and_save(codecContext, NULL, pkt, fOut);

    fail5:
    fclose(fOut);
    fail4:
    av_frame_free(&frame);
    fail3:
    av_packet_unref(pkt);
    fail2:
    fclose(fSrc);
    fail1:
    avcodec_free_context(&codecContext);
    fail:
    if (result == -1) {
        LOG_D("no error found or failed by above reason \n");
    }
}

static int select_channel_layout(const AVCodec *codec) {
    const uint64_t *p;
    uint64_t best_ch_layout = 0;
    int best_nb_channels = 0;

    if (!codec->channel_layouts)
        return AV_CH_LAYOUT_STEREO;

    p = codec->channel_layouts;
    while (*p) {
        int nb_channels = av_get_channel_layout_nb_channels(*p);

        if (nb_channels > best_nb_channels) {
            best_ch_layout = *p;
            best_nb_channels = nb_channels;
        }
        p++;
    }
    return best_ch_layout;
}

static int select_sample_rate(const AVCodec *codec) {
    const int *p;
    int best_samplerate = 0;

    if (!codec->supported_samplerates)
        return 44100;

    p = codec->supported_samplerates;
    while (*p) {
        if (!best_samplerate || abs(44100 - *p) < abs(44100 - best_samplerate))
            best_samplerate = *p;
        p++;
    }
    return best_samplerate;
}

static int check_sample_fmt(const AVCodec *codec, enum AVSampleFormat sample_fmt) {
    const enum AVSampleFormat *p = codec->sample_fmts;

    while (*p != AV_SAMPLE_FMT_NONE) {
        if (*p == sample_fmt)
            return 1;
        p++;
    }
    return 0;
}