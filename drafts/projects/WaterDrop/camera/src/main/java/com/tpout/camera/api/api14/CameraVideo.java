//package com.tpout.camera.api.old;
//
//import android.media.CamcorderProfile;
//import android.media.MediaRecorder;
//
//import java.io.IOException;
//
//import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;
//
///**
// * Created by shengjieli on 18-12-27.<br>
// * Email address: 416756910@qq.com<br>
// */
//public class CameraVideo {
//
//    private MediaRecorder mRecorder;
//
//    /**
//     * 一般需要camera的准备工作让体验更好 --- camera open/setPreviewDisplay/startPreview
//     * 方法的执行顺序不能变动
//     */
//    private boolean prepareVideoRecorder(){
//        mCamera = getCameraInstance();
//        mRecorder = new MediaRecorder();
//        mRecorder.setCamera(mCamera);
//        mRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
//        mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
//        CamcorderProfile camcorderProfile = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
//        mRecorder.setProfile(camcorderProfile);
//        mRecorder.setOutputFile(createMediaTempFile(MEDIA_TYPE_VIDEO).toString());
////        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_2_TS);// >=26
//        mRecorder.setPreviewDisplay(mPreview.getHolder().getSurface());
//        try {
//            mRecorder.prepare();
//        } catch (IOException e) {
//            releaseMediaRecorder();
//            mCamera.lock();
//            return false;
//        }
//        return true;
//    }
//
//    private void timeLapseVideo(){
//        mRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_TIME_LAPSE_HIGH));
//        mRecorder.setCaptureRate(0.1); // capture a frame every 10 seconds
//    }
//
//    private void start(){
//        mRecorder.start();
//    }
//
//    private void stop(){
//        if(null != mRecorder){
//            mRecorder.stop();
//            mRecorder.reset();//可选
//            mRecorder.release();
//        }
//        mCamera.release();
//    }
//
//
//}
