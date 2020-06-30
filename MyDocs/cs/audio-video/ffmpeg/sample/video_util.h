//
// Created by tpout on 19-4-29.
//

#ifndef NEWS_VIDEO_UTIL_H
#define NEWS_VIDEO_UTIL_H

#include <libavcodec/avcodec.h>
#include <libavutil/opt.h>
#include "log_util.h"
#include "codec_util.h"
#include <stdbool.h>

//这个方法估计需要多线程安全队列实现，暂时不会写，先不管
void save_420sp_to_h264(uint8_t *yuv_src, int rotate_angle, bool flip, size_t width, size_t height, int frame_rate, int frame_count, const char *path_dst);

//这个方法需要占用内存比较大，1秒几十兆
void save_420sp_to_420p(uint8_t *yuv_src, int rotate_angle, bool flip, size_t width, size_t height, int frame_rate, int frame_count, const char *path_dst);

//和save_420sp_to_420p配合使用
void resave_420p_to_h264(uint8_t *src, int width, int height, int frame_rate, int frame_count, const char *path_src,const char *path_dst);

#endif //NEWS_VIDEO_UTIL_H
