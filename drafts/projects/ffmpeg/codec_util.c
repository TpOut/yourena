//
// Created by tpout on 19-4-29.
//

#include "codec_util.h"

void encode_and_save(AVCodecContext *enc_ctx, AVFrame *frame, AVPacket *pkt,
                     FILE *outfile) {
    int ret;

    /* send the frame to the encoder */
    if (frame) {
//        printf("Send frame %3"PRId64"\n", frame->pts);
        LOG_D("Send frame %3lld and data length %d \n", frame->pts, strlen(frame->data[0]));
    }

    ret = avcodec_send_frame(enc_ctx, frame);
    if (ret < 0) {
        fprintf(stderr, "Error sending a frame for encoding\n");
        LOG_D("Error sending a frame for encoding\n");
    }

    while (ret >= 0) {
        ret = avcodec_receive_packet(enc_ctx, pkt);
        if (ret == AVERROR(EAGAIN)) {
            LOG_D("EAGAIN");
            av_packet_unref(pkt);
            return;
        } else if (ret == AVERROR_EOF) {
            LOG_D("EOF");
            return;
        } else if (ret < 0) {
            fprintf(stderr, "Error during encoding pkt\n");
            LOG_D("Error during encoding pkt\n");
        }

//        printf("Write packet %3"PRId64" (size=%5d)\n", pkt->pts, pkt->size);
        LOG_D("Write packet %3lld (size=%5d)\n", pkt->pts, pkt->size);
        fwrite(pkt->data, 1, pkt->size, outfile);

    }
}
