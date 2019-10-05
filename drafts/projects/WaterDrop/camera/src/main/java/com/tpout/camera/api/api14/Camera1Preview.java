package com.tpout.camera.api.api14;

import android.hardware.Camera;
import android.view.SurfaceHolder;

import com.tpout.baselib.util.DisplayOrientationDetector;
import com.tpout.camera.api.general.AspectRatio;
import com.tpout.camera.api.general.CameraXCallback;
import com.tpout.camera.api.general.CameraXHelperImpl;
import com.tpout.camera.api.general.CameraXPreviewImpl;
import com.tpout.camera.api.general.preview.PreviewImpl;
import com.tpout.camera.api.general.size.CameraSizeBean;
import com.tpout.camera.api.general.size.Size;

import java.util.Set;
import java.util.SortedSet;

/**
 * Created by TpOut on 19-1-21.<br>
 * Email address: 416756910@qq.com<br>
 */
public class Camera1Preview extends CameraXPreviewImpl {

    private Camera1Helper mCamera1Helper;

    private int mDisplayOrientation;

    public Camera1Preview(CameraXCallback cameraXCallback, PreviewImpl preview) {
        super(cameraXCallback, preview);
        mCamera1Helper = new Camera1Helper();
        mCamera1Helper.setCallback(cameraXCallback);

        mPreview.setPreviewCallback(new PreviewImpl.PreviewCallback() {
            @Override
            public void onSurfaceChanged() {
                setUpPreview();
                mCamera1Helper.updateCameraParameters(getPreviewSize(), mDisplayOrientation);
            }
        });
    }

    @Override
    public boolean start() {
        mCamera1Helper.releaseCamera();
        if (mCamera1Helper.openCamera(mCamera1Helper.getFrontCamera())) {
            mCamera1Helper.refreshCameraSizeBean();
            mCamera1Helper.updateCameraParameters(getPreviewSize(), mDisplayOrientation);
            mCamera1Helper.setDisplayOrientation(mDisplayOrientation);
            if (mPreview.isAvailable()) {
                setUpPreview();
                mCamera1Helper.startPreview();
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void stop() {
        mCamera1Helper.stopPreview();
        mCamera1Helper.releaseCamera();
    }

    @Override
    public CameraXHelperImpl getCameraXHelper() {
        return mCamera1Helper;
    }

    /**
     * 对于camera 1 而言，不会出现SurfaceHolder 了。。
     */
    public void setUpPreview() {
        if (mPreview.getOutputClass() == SurfaceHolder.class) {
            mCamera1Helper.stopPreview();
            mCamera1Helper.setPreviewDisplay(mPreview.getSurfaceHolder());
            mCamera1Helper.startPreview();
        } else {
            mCamera1Helper.setPreviewDisplay(mPreview.getSurfaceTexture());
        }
    }

    private Size getPreviewSize() {
        return getOptimalSize(mCamera1Helper.getCameraSizeBean().getRatioSizes(), mDisplayOrientation);
    }

    @SuppressWarnings("SuspiciousNameCombination")
    public Size getOptimalSize(SortedSet<Size> sizes, int displayOrientation) {
        if (!mPreview.isAvailable()) { // Not yet laid out
            return sizes.first(); // Return the smallest size
        }
        int desiredWidth;
        int desiredHeight;
        final int surfaceWidth = mPreview.getWidth();
        final int surfaceHeight = mPreview.getHeight();
        if (DisplayOrientationDetector.isLandscape(displayOrientation)) {
            desiredWidth = surfaceHeight;
            desiredHeight = surfaceWidth;
        } else {
            desiredWidth = surfaceWidth;
            desiredHeight = surfaceHeight;
        }
        Size result = null;
        for (Size size : sizes) { // Iterate from small to large
            if (desiredWidth <= size.getWidth() && desiredHeight <= size.getHeight()) {
                return size;
            }
            result = size;
        }
        return result;
    }

    @Override
    public void setFacing(int facing) {
        mCamera1Helper.setFacing(facing, getPreviewSize(), mDisplayOrientation);
    }

    public boolean setAspectRatio(AspectRatio ratio) {
        Camera camera = mCamera1Helper.getCamera();
        CameraSizeBean cameraSizeBean = mCamera1Helper.getCameraSizeBean();
        if (cameraSizeBean.mAspectRatio == null || null == camera) {
            // Handle this later when camera is opened
            cameraSizeBean.mAspectRatio = ratio;
            return true;
        } else if (!cameraSizeBean.mAspectRatio.equals(ratio)) {
            final Set<Size> sizes = cameraSizeBean.mPreviewSizes.getSizes(ratio);
            if (sizes == null) {
                throw new UnsupportedOperationException(ratio + " is not supported");
            } else {
                cameraSizeBean.mAspectRatio = ratio;
                mCamera1Helper.updateCameraParameters(getPreviewSize(), mDisplayOrientation);
                return true;
            }
        }
        return false;
    }

    public void setDisplayOrientation(int displayOrientation) {
        if (mDisplayOrientation == displayOrientation) {
            return;
        }
        mDisplayOrientation = displayOrientation;
        mCamera1Helper.setCameraRotation(mDisplayOrientation);
        mCamera1Helper.stopPreview();
        mCamera1Helper.setDisplayOrientation(mDisplayOrientation);
        mCamera1Helper.startPreview();
    }

}
