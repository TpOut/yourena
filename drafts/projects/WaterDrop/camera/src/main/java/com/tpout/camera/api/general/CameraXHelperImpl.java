package com.tpout.camera.api.general;

import java.util.Set;

/**
 * Created by TpOut on 19-1-23.<br>
 * Email address: 416756910@qq.com<br>
 */
public abstract class CameraXHelperImpl {

    public abstract boolean isCameraOpened();

    public abstract int getFacing();

    public abstract Set<AspectRatio> getSupportedAspectRatios();

    public abstract AspectRatio getAspectRatio();

    public abstract void setAutoFocus(boolean autoFocus);

    public abstract boolean getAutoFocus();

    public abstract void setFlash(int flash);

    public abstract int getFlash();

    public abstract void takePicture();

    public abstract void setDisplayOrientation(int displayOrientation);
}
