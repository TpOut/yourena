
#include <jni.h>

#include "audio_util.h"
#include "video_util.h"

JNIEXPORT void JNICALL
Java_com_tpout_AvEncoder_saveRaw420sp(JNIEnv *env, jclass type, jbyteArray yuv420sp_, jint rotateAngle, jboolean flip, jint width, jint height, jint frameRate, jint frameCount, jstring outPath_) {
    jbyte *yuv420sp = (*env)->GetByteArrayElements(env, yuv420sp_, NULL);
    const char *outPath = (*env)->GetStringUTFChars(env, outPath_, 0);

    LOG_D("camera frame length : %d", (*env)->GetArrayLength(env, yuv420sp_));

    save_420sp_to_420p(yuv420sp, rotateAngle, flip, width, height, frameRate, frameCount, outPath);

    (*env)->ReleaseByteArrayElements(env, yuv420sp_, yuv420sp, 0);
    (*env)->ReleaseStringUTFChars(env, outPath_, outPath);
}

JNIEXPORT void JNICALL
Java_com_tpout_AvEncoder_encode420pToH264(JNIEnv *env, jclass type, jint rotateAngle, jboolean flip, jint width, jint height, jint frameRate, jint frameCount, jstring srcPath_, jstring outPath_) {
    const char *srcPath = (*env)->GetStringUTFChars(env, srcPath_, 0);
    const char *outPath = (*env)->GetStringUTFChars(env, outPath_, 0);

    resave_420p_to_h264(NULL,width,height,frameRate,frameCount,srcPath,outPath);

    (*env)->ReleaseStringUTFChars(env, srcPath_, srcPath);
    (*env)->ReleaseStringUTFChars(env, outPath_, outPath);
}

JNIEXPORT void JNICALL
Java_com_tpout_AvEncoder_saveRawPcm(JNIEnv *env, jclass type, jbyteArray pcm_, jint len, jstring outPath_) {
    jbyte *pcm = (*env)->GetByteArrayElements(env, pcm_, NULL);
    const char *outPath = (*env)->GetStringUTFChars(env, outPath_, 0);

    save_pcm(pcm, len, outPath);

    (*env)->ReleaseByteArrayElements(env, pcm_, pcm, 0);
    (*env)->ReleaseStringUTFChars(env, outPath_, outPath);
}

JNIEXPORT void JNICALL
Java_com_tpout_AvEncoder_encodePcmToAac(JNIEnv *env, jclass type, jstring srcPath_, jstring outPath_) {
    const char *srcPath = (*env)->GetStringUTFChars(env, srcPath_, 0);
    const char *outPath = (*env)->GetStringUTFChars(env, outPath_, 0);

    encode_pcm_to_aac(srcPath, outPath);

    (*env)->ReleaseStringUTFChars(env, srcPath_, srcPath);
    (*env)->ReleaseStringUTFChars(env, outPath_, outPath);
}
