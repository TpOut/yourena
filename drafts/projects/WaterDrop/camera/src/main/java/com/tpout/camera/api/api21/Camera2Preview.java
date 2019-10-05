package com.tpout.camera.api.api21;

import android.support.annotation.RequiresApi;

import com.tpout.camera.api.general.AspectRatio;
import com.tpout.camera.api.general.CameraXCallback;
import com.tpout.camera.api.general.CameraXHelperImpl;
import com.tpout.camera.api.general.CameraXPreviewImpl;
import com.tpout.camera.api.general.preview.PreviewImpl;
import com.tpout.camera.api.general.size.CameraSizeBean;

/**
 * Created by TpOut on 19-1-22.<br>
 * Email address: 416756910@qq.com<br>
 */
@RequiresApi(21)
public class Camera2Preview extends CameraXPreviewImpl {

    private Camera2Helper mCamera2Helper;

    private int             mDisplayOrientation;
    private CameraXCallback mCallback;

    public Camera2Preview(CameraXCallback cameraXCallback, PreviewImpl preview) {
        super(cameraXCallback, preview);
        mCallback = cameraXCallback;

        mCamera2Helper = new Camera2Helper();
        mCamera2Helper.setCallback(new CameraXCallback() {
            @Override
            public void onCameraOpened(boolean openSuccess) {
                if (openSuccess) {
                    mCamera2Helper.startCaptureSession(mPreview);
                }
                if (null != mCallback) {
                    mCallback.onCameraOpened(true);
                }
            }

            @Override
            public void onPictureTaken(byte[] data) {
                if (null != mCallback) {
                    mCallback.onPictureTaken(data);
                }
            }

            @Override
            public void onCameraClosed() {
                if (null != mCallback) {
                    mCallback.onCameraClosed();
                }
            }
        });

        mPreview.setPreviewCallback(new PreviewImpl.PreviewCallback() {
            @Override
            public void onSurfaceChanged() {
                mCamera2Helper.startCaptureSession(mPreview);
            }
        });
    }

    public boolean start() {
        if (!mCamera2Helper.chooseCameraIdByFacing()) {
            return false;
        }
        mCamera2Helper.refreshSizeBean(mPreview.getOutputClass());
        mCamera2Helper.prepareImageReader();
        return mCamera2Helper.openCamera();
    }

    public void stop() {
        mCamera2Helper.stopPreviewAndCamera();
    }

    @Override
    public CameraXHelperImpl getCameraXHelper() {
        return mCamera2Helper;
    }

    @Override
    public void setFacing(int facing) {
        mCamera2Helper.setFacing(facing, mPreview.getOutputClass());
    }

    public void setDisplayOrientation(int displayOrientation) {
        mDisplayOrientation = displayOrientation;
        mPreview.setDisplayOrientation(mDisplayOrientation);
    }

    public boolean setAspectRatio(AspectRatio ratio) {
        CameraSizeBean cameraSizeBean = mCamera2Helper.getCameraSizeBean();
        if (ratio == null || ratio.equals(cameraSizeBean.mAspectRatio) ||
                !cameraSizeBean.mPreviewSizes.getRatios().contains(ratio)) {
            // TODO: Better error handling
            return false;
        }
        cameraSizeBean.mAspectRatio = ratio;
        mCamera2Helper.prepareImageReader();
        mCamera2Helper.stopCaptureSession();
        mCamera2Helper.startCaptureSession(mPreview);
        return true;
    }

}
