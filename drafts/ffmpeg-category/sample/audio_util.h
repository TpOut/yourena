//
// Created by tpout on 19-4-29.
//

#ifndef NEWS_AUDIO_UTIL_H
#define NEWS_AUDIO_UTIL_H

#include <libavcodec/avcodec.h>
#include "log_util.h"
#include "codec_util.h"

void encode_pcm_to_aac(const char *src_file, const char *out_file);
void save_pcm(const char* pcm, int length, const char* out_path);

#endif //NEWS_AUDIO_UTIL_H
