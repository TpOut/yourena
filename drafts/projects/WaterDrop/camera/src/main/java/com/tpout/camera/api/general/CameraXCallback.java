package com.tpout.camera.api.general;

public interface CameraXCallback {
        void onCameraOpened(boolean openSuccess);

        void onPictureTaken(byte[] data);

        void onCameraClosed();
    }