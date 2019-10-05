package com.tpout.camera.api.api21;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.Surface;

import com.tpout.baselib.util.AppUtil;
import com.tpout.camera.api.general.AspectRatio;
import com.tpout.camera.api.general.CameraXCallback;
import com.tpout.camera.api.general.CameraXHelperImpl;
import com.tpout.camera.api.general.Constants;
import com.tpout.camera.api.general.preview.PreviewImpl;
import com.tpout.camera.api.general.size.CameraSizeBean;
import com.tpout.camera.api.general.size.Size;
import com.tpout.camera.api.general.size.SizeMap;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Set;
import java.util.SortedSet;

import timber.log.Timber;

/**
 * Created by shengjieli on 18-12-27.<br>
 * Email address: 416756910@qq.com<br>
 */
@RequiresApi(21)
public class Camera2Helper extends CameraXHelperImpl {

    private static final String TAG = Camera2Helper.class.getSimpleName();

    private static final SparseIntArray INTERNAL_FACINGS = new SparseIntArray();

    static {
        INTERNAL_FACINGS.put(Constants.FACING_BACK, CameraCharacteristics.LENS_FACING_BACK);
        INTERNAL_FACINGS.put(Constants.FACING_FRONT, CameraCharacteristics.LENS_FACING_FRONT);
    }

    /**
     * Max preview width/height that is guaranteed by Camera2 API
     */
    private static final int MAX_PREVIEW_WIDTH  = 1920;
    private static final int MAX_PREVIEW_HEIGHT = 1080;

    private CameraXCallback mCameraXCallBack;

    private final CameraManager mCameraManager;

    private String mCameraId;

    private CameraCharacteristics mCameraCharacteristics;

    CameraDevice mCamera;

    CameraCaptureSession mCaptureSession;

    CaptureRequest.Builder mPreviewRequestBuilder;

    private ImageReader mImageReader;

    private CameraSizeBean mCameraSizeBean = new CameraSizeBean();
    private int            mDisplayOrientation;

    private int mFacing;

    private boolean mAutoFocus;

    private int mFlash;

    public Camera2Helper() {
        mCameraManager = (CameraManager) AppUtil.getApp().getSystemService(Context.CAMERA_SERVICE);
    }

    private final CameraDevice.StateCallback mCameraDeviceCallback
            = new CameraDevice.StateCallback() {

        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            mCamera = camera;
            if (null != mCameraXCallBack) {
                mCameraXCallBack.onCameraOpened(true);
            }
        }

        @Override
        public void onClosed(@NonNull CameraDevice camera) {
            if (null == mCameraXCallBack) {
                mCameraXCallBack.onCameraClosed();
            }
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            mCamera = null;
        }

        @Override
        public void onError(@NonNull CameraDevice camera, int error) {
            Log.e(TAG, "onError: " + camera.getId() + " (" + error + ")");
            mCamera = null;
        }

    };

    private final CameraCaptureSession.StateCallback mSessionCallback
            = new CameraCaptureSession.StateCallback() {

        @Override
        public void onConfigured(@NonNull CameraCaptureSession session) {
            if (mCamera == null) {
                return;
            }
            mCaptureSession = session;
            updateAutoFocus();
            updateFlash();
            try {
                mCaptureSession.setRepeatingRequest(mPreviewRequestBuilder.build(),
                        mCaptureCallback, null);
            } catch (CameraAccessException e) {
                Timber.tag(TAG).d("Failed to start camera preview because it couldn't access camera. %s", e.getMessage());
            } catch (IllegalStateException e) {
                Timber.tag(TAG).d("Failed to start camera preview. %s", e.getMessage());
            }
        }

        @Override
        public void onConfigureFailed(@NonNull CameraCaptureSession session) {
            Timber.tag(TAG).d("Failed to configure capture session.");
        }

        @Override
        public void onClosed(@NonNull CameraCaptureSession session) {
            if (mCaptureSession != null && mCaptureSession.equals(session)) {
                mCaptureSession = null;
            }
        }

    };

    private final PictureCaptureCallback mCaptureCallback = new PictureCaptureCallback() {

        @Override
        public void onPreCapture() {
            mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER,
                    CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER_START);
            setState(STATE_PRE_CAPTURE);
            try {
                mCaptureSession.capture(mPreviewRequestBuilder.build(), this, null);
                mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER,
                        CaptureRequest.CONTROL_AE_PRECAPTURE_TRIGGER_IDLE);
            } catch (CameraAccessException e) {
                Log.e(TAG, "Failed to run precapture sequence.", e);
            }
        }

        @Override
        public void onReady() {
            captureStillPicture();
        }

    };

    private final ImageReader.OnImageAvailableListener mOnImageAvailableListener
            = new ImageReader.OnImageAvailableListener() {

        @Override
        public void onImageAvailable(ImageReader reader) {
            try (Image image = reader.acquireNextImage()) {
                Image.Plane[] planes = image.getPlanes();
                if (planes.length > 0) {
                    ByteBuffer buffer = planes[0].getBuffer();
                    byte[] data = new byte[buffer.remaining()];
                    buffer.get(data);
                    mCameraXCallBack.onPictureTaken(data);
                }
            }
        }

    };

    @Override
    public boolean isCameraOpened() {
        return null == mCamera;
    }

    @SuppressLint("MissingPermission")
    public boolean openCamera() {
        boolean openSuccess = false;
        try {
            mCameraManager.openCamera(mCameraId, mCameraDeviceCallback, null);
            openSuccess = true;
        } catch (CameraAccessException e) {
            throw new RuntimeException("Failed to open camera: " + mCameraId, e);
        }
        return openSuccess;
    }

    /**
     * 这里本身就有关联了。。所以在想是不是没必要这么分开来写
     *
     * @param preview
     */
    void startCaptureSession(PreviewImpl preview) {
        if (mCaptureSession != null) {
            return;
        }
        if (null == mCamera || !preview.isAvailable() || mImageReader == null) {
            return;
        }
        Size previewSize = chooseOptimalSize(preview);
        preview.setBufferSize(previewSize.getWidth(), previewSize.getHeight());
        Surface surface = preview.getSurface();
        try {
            mPreviewRequestBuilder = mCamera.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            mPreviewRequestBuilder.addTarget(surface);
            mCamera.createCaptureSession(Arrays.asList(surface, mImageReader.getSurface()),
                    mSessionCallback, null);
        } catch (CameraAccessException e) {
            throw new RuntimeException("Failed to start camera session");
        }
    }

    public void stopCaptureSession() {
        if (mCaptureSession != null) {
            mCaptureSession.close();
            mCaptureSession = null;
        }
    }

    private Size chooseOptimalSize(PreviewImpl preview) {
        int surfaceLonger, surfaceShorter;
        final int surfaceWidth = preview.getWidth();
        final int surfaceHeight = preview.getHeight();
        if (surfaceWidth < surfaceHeight) {
            surfaceLonger = surfaceHeight;
            surfaceShorter = surfaceWidth;
        } else {
            surfaceLonger = surfaceWidth;
            surfaceShorter = surfaceHeight;
        }
        SortedSet<Size> candidates = mCameraSizeBean.mPreviewSizes.getSizes(mCameraSizeBean.mAspectRatio);

        // Pick the smallest of those big enough
        for (Size size : candidates) {
            if (size.getWidth() >= surfaceLonger && size.getHeight() >= surfaceShorter) {
                return size;
            }
        }
        // If no size is big enough, pick the largest one.
        return candidates.last();
    }

    public void prepareImageReader() {
        if (mImageReader != null) {
            mImageReader.close();
        }
        Size largest = mCameraSizeBean.mPreviewSizes.getSizes(mCameraSizeBean.mAspectRatio).last();
        mImageReader = ImageReader.newInstance(largest.getWidth(), largest.getHeight(),
                ImageFormat.JPEG, /* maxImages */ 2);
        mImageReader.setOnImageAvailableListener(mOnImageAvailableListener, null);
    }

    public void stopPreviewAndCamera() {
        stopCaptureSession();
        if (mCamera != null) {
            mCamera.close();
            mCamera = null;
        }
        if (mImageReader != null) {
            mImageReader.close();
            mImageReader = null;
        }
    }

    public boolean chooseCameraIdByFacing() {
        try {
            int internalFacing = INTERNAL_FACINGS.get(mFacing);
            final String[] ids = mCameraManager.getCameraIdList();
            if (ids.length == 0) { // No camera
                Timber.tag(TAG).e("There is no camera");
                return false;
            }
            for (String id : ids) {
                CameraCharacteristics characteristics = mCameraManager.getCameraCharacteristics(id);
                Integer level = characteristics.get(
                        CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
                if (level == null ||
                        level == CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY) {
                    continue;
                }
                Integer internal = characteristics.get(CameraCharacteristics.LENS_FACING);
                if (internal == null) {
                    Timber.tag(TAG).e("There is no facing camera");
                } else if (internal == internalFacing) {
                    mCameraId = id;
                    mCameraCharacteristics = characteristics;
                    return true;
                }
            }
            // Not found
            mCameraId = ids[0];
            mCameraCharacteristics = mCameraManager.getCameraCharacteristics(mCameraId);
            Integer level = mCameraCharacteristics.get(
                    CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL);
            if (level == null ||
                    level == CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY) {
                return false;
            }
            Integer internal = mCameraCharacteristics.get(CameraCharacteristics.LENS_FACING);
            if (internal == null) {
                throw new NullPointerException("Unexpected state: LENS_FACING null");
            }
            for (int i = 0, count = INTERNAL_FACINGS.size(); i < count; i++) {
                if (INTERNAL_FACINGS.valueAt(i) == internal) {
                    mFacing = INTERNAL_FACINGS.keyAt(i);
                    return true;
                }
            }
            // The operation can reach here when the only camera device is an external one.
            // We treat it as facing back.
            mFacing = Constants.FACING_BACK;
            return true;
        } catch (CameraAccessException e) {
            throw new RuntimeException("Failed to get a list of camera devices", e);
        }
    }

    public CameraSizeBean getCameraSizeBean() {
        return mCameraSizeBean;
    }

    public <T> void refreshSizeBean(Class<T> outputClass) {
        StreamConfigurationMap map = mCameraCharacteristics.get(
                CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (map == null) {
            Timber.tag(TAG).d("Failed to get configuration map: " + mCameraId);
        } else {
            mCameraSizeBean.mPreviewSizes.clear();
            for (android.util.Size size : map.getOutputSizes(outputClass)) {
                int width = size.getWidth();
                int height = size.getHeight();
                if (width <= MAX_PREVIEW_WIDTH && height <= MAX_PREVIEW_HEIGHT) {
                    mCameraSizeBean.mPreviewSizes.add(new Size(width, height));
                }
            }
        }

        mCameraSizeBean.mPictureSizes.clear();
        collectPictureSizes(mCameraSizeBean.mPictureSizes, map);
        for (AspectRatio ratio : mCameraSizeBean.mPreviewSizes.getRatios()) {
            if (!mCameraSizeBean.mPictureSizes.getRatios().contains(ratio)) {
                mCameraSizeBean.mPreviewSizes.remove(ratio);
            }
        }

        if (!mCameraSizeBean.mPreviewSizes.getRatios().contains(mCameraSizeBean.mAspectRatio)) {
            mCameraSizeBean.mAspectRatio = mCameraSizeBean.mPreviewSizes.getRatios().iterator().next();
        }
    }

    protected void collectPictureSizes(SizeMap sizes, StreamConfigurationMap map) {
        for (android.util.Size size : map.getOutputSizes(ImageFormat.JPEG)) {
            mCameraSizeBean.mPictureSizes.add(new Size(size.getWidth(), size.getHeight()));
        }
    }

    /**
     * invoke just same as {@link Camera2Preview#stop()} + {@link Camera2Preview#start()}
     *
     * @param facing
     */
    public <T> void setFacing(int facing, Class<T> outputClass) {
        if (mFacing == facing) {
            return;
        }
        mFacing = facing;
        if (null != mCamera) {
            stopPreviewAndCamera();
            if (!chooseCameraIdByFacing()) {
                return;
            }
            refreshSizeBean(outputClass);
            prepareImageReader();
            openCamera();
        }
    }

    @Override
    public int getFacing() {
        return mFacing;
    }

    public void setAutoFocus(boolean autoFocus) {
        if (mAutoFocus == autoFocus) {
            return;
        }
        mAutoFocus = autoFocus;
        if (mPreviewRequestBuilder != null) {
            updateAutoFocus();
            if (mCaptureSession != null) {
                try {
                    mCaptureSession.setRepeatingRequest(mPreviewRequestBuilder.build(),
                            mCaptureCallback, null);
                } catch (CameraAccessException e) {
                    mAutoFocus = !mAutoFocus; // Revert
                }
            }
        }
    }

    void updateAutoFocus() {
        if (mAutoFocus) {
            int[] modes = mCameraCharacteristics.get(
                    CameraCharacteristics.CONTROL_AF_AVAILABLE_MODES);
            // Auto focus is not supported
            if (modes == null || modes.length == 0 ||
                    (modes.length == 1 && modes[0] == CameraCharacteristics.CONTROL_AF_MODE_OFF)) {
                mAutoFocus = false;
                mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE,
                        CaptureRequest.CONTROL_AF_MODE_OFF);
            } else {
                mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE,
                        CaptureRequest.CONTROL_AF_MODE_CONTINUOUS_PICTURE);
            }
        } else {
            mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE,
                    CaptureRequest.CONTROL_AF_MODE_OFF);
        }
    }

    @Override
    public boolean getAutoFocus() {
        return mAutoFocus;
    }

    public void setFlash(int flash) {
        if (mFlash == flash) {
            return;
        }
        int saved = mFlash;
        mFlash = flash;
        if (mPreviewRequestBuilder != null) {
            updateFlash();
            if (mCaptureSession != null) {
                try {
                    mCaptureSession.setRepeatingRequest(mPreviewRequestBuilder.build(),
                            mCaptureCallback, null);
                } catch (CameraAccessException e) {
                    mFlash = saved; // Revert
                }
            }
        }
    }

    void updateFlash() {
        switch (mFlash) {
            case Constants.FLASH_OFF:
                mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,
                        CaptureRequest.CONTROL_AE_MODE_ON);
                mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE,
                        CaptureRequest.FLASH_MODE_OFF);
                break;
            case Constants.FLASH_ON:
                mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,
                        CaptureRequest.CONTROL_AE_MODE_ON_ALWAYS_FLASH);
                mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE,
                        CaptureRequest.FLASH_MODE_OFF);
                break;
            case Constants.FLASH_TORCH:
                mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,
                        CaptureRequest.CONTROL_AE_MODE_ON);
                mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE,
                        CaptureRequest.FLASH_MODE_TORCH);
                break;
            case Constants.FLASH_AUTO:
                mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,
                        CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
                mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE,
                        CaptureRequest.FLASH_MODE_OFF);
                break;
            case Constants.FLASH_RED_EYE:
                mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,
                        CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH_REDEYE);
                mPreviewRequestBuilder.set(CaptureRequest.FLASH_MODE,
                        CaptureRequest.FLASH_MODE_OFF);
                break;
        }
    }

    @Override
    public int getFlash() {
        return mFlash;
    }

    public void setDisplayOrientation(int displayOrientation) {
        mDisplayOrientation = displayOrientation;
    }

    @Override
    public Set<AspectRatio> getSupportedAspectRatios() {
        return mCameraSizeBean.getSupportedAspectRatios();
    }

    @Override
    public AspectRatio getAspectRatio() {
        return mCameraSizeBean.mAspectRatio;
    }

    public void takePicture() {
        if (mAutoFocus) {
            lockFocus();
        } else {
            captureStillPicture();
        }
    }

    private void lockFocus() {
        mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER,
                CaptureRequest.CONTROL_AF_TRIGGER_START);
        try {
            mCaptureCallback.setState(PictureCaptureCallback.STATE_LOCKING);
            mCaptureSession.capture(mPreviewRequestBuilder.build(), mCaptureCallback, null);
        } catch (CameraAccessException e) {
            Log.e(TAG, "Failed to lock focus.", e);
        }
    }

    /**
     * Captures a still picture.
     */
    void captureStillPicture() {
        try {
            CaptureRequest.Builder captureRequestBuilder = mCamera.createCaptureRequest(
                    CameraDevice.TEMPLATE_STILL_CAPTURE);
            captureRequestBuilder.addTarget(mImageReader.getSurface());
            captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE,
                    mPreviewRequestBuilder.get(CaptureRequest.CONTROL_AF_MODE));
            switch (mFlash) {
                case Constants.FLASH_OFF:
                    captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,
                            CaptureRequest.CONTROL_AE_MODE_ON);
                    captureRequestBuilder.set(CaptureRequest.FLASH_MODE,
                            CaptureRequest.FLASH_MODE_OFF);
                    break;
                case Constants.FLASH_ON:
                    captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,
                            CaptureRequest.CONTROL_AE_MODE_ON_ALWAYS_FLASH);
                    break;
                case Constants.FLASH_TORCH:
                    captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,
                            CaptureRequest.CONTROL_AE_MODE_ON);
                    captureRequestBuilder.set(CaptureRequest.FLASH_MODE,
                            CaptureRequest.FLASH_MODE_TORCH);
                    break;
                case Constants.FLASH_AUTO:
                    captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,
                            CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
                    break;
                case Constants.FLASH_RED_EYE:
                    captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,
                            CaptureRequest.CONTROL_AE_MODE_ON_AUTO_FLASH);
                    break;
            }
            // Calculate JPEG orientation.
            @SuppressWarnings("ConstantConditions")
            int sensorOrientation = mCameraCharacteristics.get(
                    CameraCharacteristics.SENSOR_ORIENTATION);
            captureRequestBuilder.set(CaptureRequest.JPEG_ORIENTATION,
                    (sensorOrientation +
                            mDisplayOrientation * (mFacing == Constants.FACING_FRONT ? 1 : -1) +
                            360) % 360);
            // Stop preview and capture a still picture.
            mCaptureSession.stopRepeating();
            mCaptureSession.capture(captureRequestBuilder.build(),
                    new CameraCaptureSession.CaptureCallback() {
                        @Override
                        public void onCaptureCompleted(@NonNull CameraCaptureSession session,
                                                       @NonNull CaptureRequest request,
                                                       @NonNull TotalCaptureResult result) {
                            unlockFocus();
                        }
                    }, null);
        } catch (CameraAccessException e) {
            Log.e(TAG, "Cannot capture a still picture.", e);
        }
    }

    /**
     * Unlocks the auto-focus and restart camera preview. This is supposed to be called after
     * capturing a still picture.
     */
    void unlockFocus() {
        mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER,
                CaptureRequest.CONTROL_AF_TRIGGER_CANCEL);
        try {
            mCaptureSession.capture(mPreviewRequestBuilder.build(), mCaptureCallback, null);
            updateAutoFocus();
            updateFlash();
            mPreviewRequestBuilder.set(CaptureRequest.CONTROL_AF_TRIGGER,
                    CaptureRequest.CONTROL_AF_TRIGGER_IDLE);
            mCaptureSession.setRepeatingRequest(mPreviewRequestBuilder.build(), mCaptureCallback,
                    null);
            mCaptureCallback.setState(PictureCaptureCallback.STATE_PREVIEW);
        } catch (CameraAccessException e) {
            Log.e(TAG, "Failed to restart camera preview.", e);
        }
    }

    public void setCallback(CameraXCallback cameraXCallback) {
        mCameraXCallBack = cameraXCallback;
    }
}
