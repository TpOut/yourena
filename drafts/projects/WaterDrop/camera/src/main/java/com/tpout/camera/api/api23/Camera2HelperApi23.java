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

package com.tpout.camera.api.api23;

import android.annotation.TargetApi;
import android.graphics.ImageFormat;
import android.hardware.camera2.params.StreamConfigurationMap;

import com.tpout.camera.api.api21.Camera2Helper;
import com.tpout.camera.api.general.CameraXCallback;
import com.tpout.camera.api.general.preview.PreviewImpl;
import com.tpout.camera.api.general.size.Size;
import com.tpout.camera.api.general.size.SizeMap;


@TargetApi(23)
public class Camera2HelperApi23 extends Camera2Helper {

    @Override
    protected void collectPictureSizes(SizeMap sizes, StreamConfigurationMap map) {
        // Try to get hi-res output getSizes
        android.util.Size[] outputSizes = map.getHighResolutionOutputSizes(ImageFormat.JPEG);
        if (outputSizes != null) {
            for (android.util.Size size : map.getHighResolutionOutputSizes(ImageFormat.JPEG)) {
                sizes.add(new Size(size.getWidth(), size.getHeight()));
            }
        }
        if (sizes.isEmpty()) {
            super.collectPictureSizes(sizes, map);
        }
    }

}
