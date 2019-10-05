package com.tpout.baselib.permission;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by shengjieli on 18-12-27.<br>
 * Email address: 416756910@qq.com<br>
 */
public class FeatureUtil {

    public static boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }




}
