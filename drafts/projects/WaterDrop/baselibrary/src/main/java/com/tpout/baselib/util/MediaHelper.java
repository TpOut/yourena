package com.tpout.baselib.util;

import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

/**
 * Created by shengjieli on 18-12-28.<br>
 * Email address: 416756910@qq.com<br>
 */
public class MediaHelper {

    //通知系统的media扫描器
    public static void notifyAlbum(String filePaths) {
//        if (Build.VERSION.SDK_INT <= 19) {
//            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//            File f = new File(filePaths);
//            Uri contentUri = Uri.fromFile(f);
//            mediaScanIntent.setData(contentUri);
//            AppUtil.getApp().sendBroadcast(mediaScanIntent);
//        } else {
        new MediaScanner(AppUtil.getApp()).scanFiles(new String[]{filePaths}, new String[]{MimeTypeMap.getSingleton().getMimeTypeFromExtension("jpg")});
//        }
    }

    public static Uri getOutputMediaFileUri(int type, String dirName) {
        return Uri.fromFile(createMediaTempFile(type, dirName));
    }

    public static File createMediaTempFile(int type, String dirName) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), dirName);
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Log.d("MyCameraApp", "failed to create directory");
                    return null;
                }
            }

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
            File mediaFile;
            if (type == MEDIA_TYPE_IMAGE) {
                mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                        "IMG_" + timeStamp + ".jpg");
            } else if (type == MEDIA_TYPE_VIDEO) {
                mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                        "VID_" + timeStamp + ".mp4");
            } else {
                return null;
            }
            return mediaFile;
        }
        return null;
    }

}
