package com.tpout.camera.api.api14;

import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.view.SurfaceHolder;

import com.tpout.baselib.util.ToastUtil;
import com.tpout.camera.api.general.AspectRatio;
import com.tpout.camera.api.general.CameraXCallback;
import com.tpout.camera.api.general.CameraXHelperImpl;
import com.tpout.camera.api.general.Constants;
import com.tpout.camera.api.general.size.CameraSizeBean;
import com.tpout.camera.api.general.size.Size;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import timber.log.Timber;

import static android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK;
import static android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT;
import static com.tpout.baselib.util.DisplayOrientationDetector.isLandscape;

/**
 * Created by TpOut on 19-1-21.<br>
 * Email address: 416756910@qq.com<br>
 */
public class Camera1Helper extends CameraXHelperImpl {

    public static final int INVALID_CAMERA_ID = -1;

    private static final SparseArrayCompat<String> FLASH_MODES = new SparseArrayCompat<>();

    static {
        FLASH_MODES.put(Constants.FLASH_OFF, Camera.Parameters.FLASH_MODE_OFF);
        FLASH_MODES.put(Constants.FLASH_ON, Camera.Parameters.FLASH_MODE_ON);
        FLASH_MODES.put(Constants.FLASH_TORCH, Camera.Parameters.FLASH_MODE_TORCH);
        FLASH_MODES.put(Constants.FLASH_AUTO, Camera.Parameters.FLASH_MODE_AUTO);
        FLASH_MODES.put(Constants.FLASH_RED_EYE, Camera.Parameters.FLASH_MODE_RED_EYE);
    }

    /**
     * 指相机的功能模式，随时更新，随时获取
     * 然而模式有boolean ，int ，还在考虑要不要添加一个bean
     */
    private List<String> mFeatureModes;
    private boolean      hasStartPreview;

    private Camera.FaceDetectionListener mFaceDetectionListener;
    private CameraXCallback              mCallback;

    private final AtomicBoolean isPictureCaptureInProgress = new AtomicBoolean(false);

    private Camera         mCamera;
    /**
     * 目前来看，id 和功能状态等，都不能从camera 中获取，所以都需要备份一个
     */
    private int            mCameraId;
    private int            mFlashMode      = Constants.FLASH_OFF;
    private int            mFacing;
    private boolean        mAutoFocus;
    private CameraSizeBean mCameraSizeBean = new CameraSizeBean();

    /**
     * invalid camera id should be  0 to maxId continuously
     *
     * @return cameraId, min is {@link #INVALID_CAMERA_ID}
     */
    private int getMaxId() {
        return Camera.getNumberOfCameras() - 1;
    }

    public int getFrontCamera() {
        return getCameraId(CAMERA_FACING_FRONT);
    }

    public int getBackCamera() {
        return getCameraId(CAMERA_FACING_BACK);
    }

    /**
     * @param facingType {@link android.hardware.Camera.CameraInfo#CAMERA_FACING_FRONT}
     *                   {@link android.hardware.Camera.CameraInfo#CAMERA_FACING_BACK}
     * @return cameraId
     */
    private int getCameraId(int facingType) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        for (int i = 0, count = Camera.getNumberOfCameras(); i < count; i++) {
            Camera.getCameraInfo(i, cameraInfo);
            if (cameraInfo.facing == facingType) {
                return mCameraId = i;
            }
        }
        return INVALID_CAMERA_ID;
    }

    @Override
    public boolean isCameraOpened() {
        return null != mCamera;
    }

    @Nullable
    public Camera getCamera() {
        return mCamera;
    }

    public boolean openCamera(int cameraId) {
        boolean openSuccess = false;
        try {
            mCamera = Camera.open(cameraId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            openSuccess = null != mCamera;
            if (null != mCallback) {
                mCallback.onCameraOpened(openSuccess);
            }
        }
        return openSuccess;
    }

    public void startPreview() {
        if (null == mCamera) {
            return;
        }
        if (!hasStartPreview) {
            mCamera.startPreview();
            hasStartPreview = true;
        }
    }

    public void stopPreview() {
        if (null == mCamera) {
            return;
        }
        if (hasStartPreview) {
            hasStartPreview = false;
            mCamera.stopPreview();
        }
    }

    public void releaseCamera() {
        if (null == mCamera) {
            return;
        }
        mCamera.release();

        if (null != mCallback) {
            mCallback.onCameraClosed();
        }
    }

    public void setPreviewDisplay(SurfaceHolder surfaceHolder) {
        if (null == mCamera) {
            return;
        }
        try {
            mCamera.setPreviewDisplay(surfaceHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPreviewDisplay(SurfaceTexture surfaceTexture) {
        if (null == mCamera) {
            return;
        }
        try {
            mCamera.setPreviewTexture(surfaceTexture);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCameraRotation(int displayOrientation) {
        if (null == mCamera) {
            return;
        }
        setCameraRotationInternal(mCamera.getParameters(), displayOrientation);
        updateParametersInternal();
    }

    private void setCameraRotationInternal(Camera.Parameters parameters, int displayOrientation) {
        parameters.setRotation(calcCameraRotation(displayOrientation));
    }

    public void setDisplayOrientation(int displayOrientation) {
        if (null == mCamera) {
            return;
        }
        mCamera.setDisplayOrientation(calcDisplayOrientation(displayOrientation));
    }

    public CameraSizeBean getCameraSizeBean() {
        return mCameraSizeBean;
    }

    public void refreshCameraSizeBean() {
        mCameraSizeBean.refreshSize(mCamera);
    }

    public void updateCameraParameters(Size previewSize, int displayOrientation) {
        if (null == mCamera) {
            return;
        }
        stopPreview();
        Camera.Parameters parameters = mCamera.getParameters();

        parameters.setPreviewSize(previewSize.getWidth(), previewSize.getHeight());

        Size pictureSize = mCameraSizeBean.mPictureSizes.getSizes(mCameraSizeBean.mAspectRatio).last();
        parameters.setPictureSize(pictureSize.getWidth(), pictureSize.getHeight());

        setCameraRotationInternal(parameters, displayOrientation);
        setAutoFocusInternal(mAutoFocus);
        setFlashInternal(mFlashMode);

        updateParametersInternal();
        startPreview();
    }

    /**
     * Calculate display orientation
     * https://developer.android.com/reference/android/hardware/Camera.html#setDisplayOrientation(int)
     * <p>
     * This calculation is used for orienting the preview
     * <p>
     * Note: This is not the same calculation as the camera rotation
     *
     * @param screenOrientationDegrees Screen orientation in degrees
     * @return Number of degrees required to rotate preview
     */
    private int calcDisplayOrientation(int screenOrientationDegrees) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(mCameraId, cameraInfo);
        if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            return (360 - (cameraInfo.orientation + screenOrientationDegrees) % 360) % 360;
        } else {  // back-facing
            return (cameraInfo.orientation - screenOrientationDegrees + 360) % 360;
        }
    }


    /**
     * Calculate camera rotation
     * <p>
     * This calculation is applied to the output JPEG either via Exif Orientation tag
     * or by actually transforming the bitmap. (Determined by vendor camera API implementation)
     * <p>
     * Note: This is not the same calculation as the display orientation
     *
     * @param screenOrientationDegrees Screen orientation in degrees
     * @return Number of degrees to rotate image in order for it to view correctly.
     */
    private int calcCameraRotation(int screenOrientationDegrees) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(mCameraId, cameraInfo);
        if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            return (cameraInfo.orientation + screenOrientationDegrees) % 360;
        } else {  // back-facing
            final int landscapeFlip = isLandscape(screenOrientationDegrees) ? 180 : 0;
            return (cameraInfo.orientation + screenOrientationDegrees + landscapeFlip) % 360;
        }
    }

    private static final int FEATURE_FLASH       = 1;
    private static final int FEATURE_SMOOTH_ZOOM = 2;
    private static final int FEATURE_FOCUS       = 3;
    private static final int FEATURE_FOCUS_AREA  = 4;

    @IntDef({FEATURE_FLASH, FEATURE_SMOOTH_ZOOM, FEATURE_FOCUS, FEATURE_FOCUS_AREA})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FeatureType {
    }

    // TODO: 19-1-21 根据需要添加
    private boolean hasFeature(@FeatureType int feature) {
        Camera.Parameters parameters = mCamera.getParameters();
        switch (feature) {
            case FEATURE_FLASH:
                return null != parameters.getSupportedFocusModes();
            case FEATURE_SMOOTH_ZOOM:
                return parameters.isSmoothZoomSupported();
            case FEATURE_FOCUS:
                refreshFeatureModesRef(parameters.getSupportedFocusModes());
                return null != mFeatureModes; //always true
            case FEATURE_FOCUS_AREA:
                return 0 != parameters.getMaxNumFocusAreas();
        }
        return false;
    }

    private void refreshFeatureModesRef(List<String> featureModes) {
        mFeatureModes = featureModes;
    }

    /**
     * @param featureMode like: {@link Camera.Parameters#FOCUS_MODE_AUTO}
     * @return
     */
    private boolean hasFeatureMode(String featureMode) {
        // TODO: 19-1-21 目前只适合聚焦模式的判断
        return mFeatureModes.contains(featureMode);
    }

    /**
     * normally should invoke exactly , such as {@link #setAutoFocus}
     */
    public void setFeatureMode(String featureMode) {
        Camera.Parameters parameters = mCamera.getParameters();
        parameters.setFocusMode(featureMode);
        mCamera.setParameters(parameters);
    }

    /**
     * 示例当中，会setupPreview，但是
     * 一、preview 不属于camera 的api
     * 二、启动的时候，手动指定默认facing ，然后会setupPreview, 感觉后续set 无需重复
     * 所以暂时不写，看有没有问题
     *
     * @param facing {@link #getCameraId(int)}
     */
    public void setFacing(int facing, Size previewSize, int displayOrientation) {
        if (mFacing == facing) {
            return;
        }
        mFacing = facing;
        stopPreview();
        releaseCamera();
        startPreview();
        if (openCamera(mFacing)) {
            mCameraSizeBean.refreshSize(mCamera);
            updateCameraParameters(previewSize, displayOrientation);
            setDisplayOrientation(displayOrientation);
            startPreview();
        }
    }

    public int getFacing() {
        return mFacing;
    }

    /**
     * if initial , should invoke {@link #setAutoFocusInternal(boolean)}
     *
     * @param autoFocus
     */
    public void setAutoFocus(boolean autoFocus) {
        if (mAutoFocus == autoFocus) {
            return;
        }
        if (setAutoFocusInternal(autoFocus)) {
            updateParametersInternal();
        }
    }

    /**
     * @return {@code true} if modified.
     */
    private boolean setAutoFocusInternal(boolean autoFocus) {
        mAutoFocus = autoFocus;
        if (null != mCamera) {
            Camera.Parameters parameters = mCamera.getParameters();
            final List<String> modes = parameters.getSupportedFocusModes();
            if (autoFocus && modes.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE)) {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
            } else if (modes.contains(Camera.Parameters.FOCUS_MODE_FIXED)) {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_FIXED);
            } else if (modes.contains(Camera.Parameters.FOCUS_MODE_INFINITY)) {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_INFINITY);
            } else {
                parameters.setFocusMode(modes.get(0));
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean getAutoFocus() {
        if (null == mCamera) {
            return mAutoFocus;
        }
        String focusMode = mCamera.getParameters().getFocusMode();
        return focusMode != null && focusMode.contains("continuous");
    }

    /**
     * if initial , should invoke {@link #setFlashInternal(int)}
     *
     * @param flashMode
     */
    public void setFlash(int flashMode) {
        if (mFlashMode == flashMode) {
            return;
        }
        if (setFlashInternal(flashMode)) {
            updateParametersInternal();
        }
    }

    /**
     * todo :  modes more
     *
     * @return {@code true} if modified.
     */
    private boolean setFlashInternal(int flashMode) {
        if (null != mCamera) {
            Camera.Parameters parameters = mCamera.getParameters();
            List<String> modes = parameters.getSupportedFlashModes();

            String mode = FLASH_MODES.get(flashMode);
            if (modes != null && modes.contains(mode)) {
                parameters.setFlashMode(mode);
                mFlashMode = flashMode;
                return true;
            }
        }
        return false;
    }

    public int getFlash() {
        return mFlashMode;
    }


    /**
     * 人脸捕捉功能
     */
    public boolean hasFaceDetectionFeature() {
        Camera.Parameters params = mCamera.getParameters();
        return params.getMaxNumDetectedFaces() > 0;
    }

    /**
     * 在mCamera每次设置preview之后都要重新startFaceDetection
     */
    public void startFaceDetection() {
        if (null == mFaceDetectionListener) {
            mFaceDetectionListener = new Camera.FaceDetectionListener() {
                @Override
                public void onFaceDetection(Camera.Face[] faces, Camera camera) {
                    if (faces.length > 0) {
                        Timber.d("face detected: " + faces.length +
                                " Face 1 Location X: " + faces[0].rect.centerX() +
                                "Y: " + faces[0].rect.centerY());
                    }
                }
            };
            mCamera.setFaceDetectionListener(mFaceDetectionListener);
        }
        mCamera.startFaceDetection();
    }

    /**
     * 聚焦区域
     * Meter 大概翻译成 测光表，用来辅助处理聚焦
     */
    private void controlMeterAndFocusAreas() {
        Camera.Parameters params = mCamera.getParameters();

        if (params.getMaxNumMeteringAreas() > 0) {
            List<Camera.Area> meteringAreas = new ArrayList<Camera.Area>();
            //rect的大小是[-1000,-1000 -- 1000, 1000 ]
            //不受放大缩小、方向旋转影响
            Rect areaRect1 = new Rect(-100, -100, 100, 100);    // specify an area in center of image
            meteringAreas.add(new Camera.Area(areaRect1, 600)); // set weight to 60%
            Rect areaRect2 = new Rect(800, -1000, 1000, -800);  // specify an area in upper right of image
            meteringAreas.add(new Camera.Area(areaRect2, 400)); // set weight to 40%
            params.setMeteringAreas(meteringAreas);
        }

        updateParametersInternal();
    }

    /**
     * 任何修改过parameters 的操作都要调用这个生效
     */
    private void updateParametersInternal() {
        mCamera.setParameters(mCamera.getParameters());
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
        if (null == mCamera) {
            ToastUtil.showShort("等待相机初始化");
            return;
        }
        if (getAutoFocus()) {
            mCamera.cancelAutoFocus();
            mCamera.autoFocus(new Camera.AutoFocusCallback() {
                @Override
                public void onAutoFocus(boolean success, Camera camera) {
                    takePictureInternal();
                }
            });
        } else {
            takePictureInternal();
        }
    }

    public void takePictureInternal() {
        if (!isPictureCaptureInProgress.getAndSet(true)) {
            mCamera.takePicture(null, null, null, new Camera.PictureCallback() {
                @Override
                public void onPictureTaken(byte[] data, Camera camera) {
                    isPictureCaptureInProgress.set(false);
                    if (null != mCallback) {
                        mCallback.onPictureTaken(data);
                    }
                    camera.cancelAutoFocus();
                    camera.startPreview();
                }
            });
        }
    }

    public void setCallback(CameraXCallback callback) {
        mCallback = callback;
    }

}
