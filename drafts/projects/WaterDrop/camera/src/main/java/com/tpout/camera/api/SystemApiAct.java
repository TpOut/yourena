package com.tpout.camera.api;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.Group;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tpout.baselib.util.MediaHelper;
import com.tpout.camera.R;

import java.io.File;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;

/**
 * Created by shengjieli on 18-12-26.<br>
 * Email address: 416756910@qq.com<br>
 */
public class SystemApiAct extends AppCompatActivity {

    private Group      gAction;
    private CameraView mCameraView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_api_camera);
        mCameraView = findViewById(R.id.cv);
        mCameraView.addCallback(new CameraView.Callback() {
            @Override
            public void onPictureTaken(CameraView cameraView, byte[] data) {
                super.onPictureTaken(cameraView, data);
                File mediaTempFile = MediaHelper.createMediaTempFile(MEDIA_TYPE_IMAGE, getPackageName());
            }
        });
    }

    public void onCapture(View v) {

    }

    public void onRecord(View v) {
//        if (isRecording) {
//            // stop recording and release camera
//            mMediaRecorder.stop();  // stop the recording
//            releaseMediaRecorder(); // release the MediaRecorder object
//            mCamera.lock();         // take camera access back from MediaRecorder
//
//            // inform the user that recording has stopped
//            setCaptureButtonText("Capture");
//            isRecording = false;
//        } else {
//            // initialize video camera
//            if (prepareVideoRecorder()) {
//                // Camera is available and unlocked, MediaRecorder is prepared,
//                // now you can start recording
//                mMediaRecorder.start();
//
//                // inform the user that recording has started
//                setCaptureButtonText("Stop");
//                isRecording = true;
//            } else {
//                // prepare didn't work, release the camera
//                releaseMediaRecorder();
//                // inform user
//            }
//        }
    }

    public void onActionSave(View v) {

    }

    public void onActionCancel(View v) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        mCameraView.stop();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mCameraView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
