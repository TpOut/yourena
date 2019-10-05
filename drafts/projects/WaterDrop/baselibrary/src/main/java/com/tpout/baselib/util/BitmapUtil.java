package com.tpout.baselib.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by TpOut on 18-12-27.<br>
 * Email address: 416756910@qq.com<br>
 */
public class BitmapUtil {

    public static Bitmap scaleSizeFromPath(int targetWidth, int targetHeight, String filePath) {

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetWidth, photoH / targetHeight);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        return BitmapFactory.decodeFile(filePath, bmOptions);
    }
}
