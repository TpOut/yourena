//
// Created by tpout on 19-4-29.
//

#ifndef NEWS_CODEC_UTIL_H
#define NEWS_CODEC_UTIL_H

#include <libavcodec/avcodec.h>
#include "codec_util.h"
#include "log_util.h"

void encode_and_save(AVCodecContext *enc_ctx, AVFrame *frame, AVPacket *pkt,
                     FILE *outfile);

#endif //NEWS_CODEC_UTIL_H
