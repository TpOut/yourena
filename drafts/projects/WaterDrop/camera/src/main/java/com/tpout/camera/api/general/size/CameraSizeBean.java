package com.tpout.camera.api.general.size;

import android.hardware.Camera;

import com.tpout.camera.api.general.AspectRatio;
import com.tpout.camera.api.general.Constants;

import java.util.Set;
import java.util.SortedSet;

/**
 * Created by TpOut on 19-1-21.<br>
 * Email address: 416756910@qq.com<br>
 */
public class CameraSizeBean {

    public final SizeMap mPreviewSizes = new SizeMap();

    public final SizeMap mPictureSizes = new SizeMap();

    public AspectRatio mAspectRatio = Constants.DEFAULT_ASPECT_RATIO;

    public void refreshSize(Camera camera) {
        Camera.Parameters parameters = camera.getParameters();

        mPreviewSizes.clear();
        for (Camera.Size size : parameters.getSupportedPreviewSizes()) {
            mPreviewSizes.add(new Size(size.width, size.height));
        }

        mPictureSizes.clear();
        for (Camera.Size size : parameters.getSupportedPictureSizes()) {
            mPictureSizes.add(new Size(size.width, size.height));
        }
    }

    public SortedSet<Size> getRatioSizes() {
        SortedSet<Size> sizes = mPreviewSizes.getSizes(mAspectRatio);
        if (sizes == null) { // Not supported
            mAspectRatio = AspectRatio.chooseDefaultAspectRatio(mPreviewSizes);
            sizes = mPreviewSizes.getSizes(mAspectRatio);
        }
        return sizes;
    }

    public Set<AspectRatio> getSupportedAspectRatios() {
        SizeMap idealAspectRatios = mPreviewSizes;
        for (AspectRatio aspectRatio : idealAspectRatios.getRatios()) {
            if (mPictureSizes.getSizes(aspectRatio) == null) {
                idealAspectRatios.remove(aspectRatio);
            }
        }
        return idealAspectRatios.getRatios();
    }

}
