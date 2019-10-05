/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tpout.camera.api.general.preview;

import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;


/**
 * Encapsulates all the operations related to camera preview in a backward-compatible manner.
 */
public abstract class PreviewImpl {

    public interface PreviewCallback {
        void onSurfaceChanged();
    }

    private PreviewCallback mPreviewCallback;

    private int mWidth;

    private int mHeight;

    public void setPreviewCallback(PreviewCallback previewCallback) {
        mPreviewCallback = previewCallback;
    }

    public abstract Surface getSurface();

    public abstract View getView();

    public abstract Class getOutputClass();

    public abstract void setDisplayOrientation(int displayOrientation);

    public abstract boolean isAvailable();

    protected void dispatchSurfaceChanged() {
        mPreviewCallback.onSurfaceChanged();
    }

    public SurfaceHolder getSurfaceHolder() {
        return null;
    }

    public SurfaceTexture getSurfaceTexture() {
        return null;
    }

    public void setBufferSize(int width, int height) {
    }

    public void setSize(int width, int height) {
        mWidth = width;
        mHeight = height;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

}
