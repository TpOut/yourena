package com.tpout.baselib.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.example.baselibrary.BuildConfig;

import java.io.File;

/**
 * Created by shengjieli on 18-12-28.<br>
 * Email address: 416756910@qq.com<br>
 */
public class IntentHelper {

    /**
     * 图片拍摄的时候，由于uri前后不一样，所以需要这里兼容
     *
     * @return
     */
    private Uri getCapturePhotoUri(String targetName, Context context) {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N ?
                FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvider", new File(Environment.getExternalStorageDirectory(), targetName))
                : Uri.fromFile(new File(Environment.getExternalStorageDirectory(), targetName));
    }

    private void jumpToCameraApp(Activity actContext) {
        Intent imageCaptureIntent = new Intent();
        imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, getCapturePhotoUri("", actContext));
//        actContext.startActivityForResult(imageCaptureIntent, mCameraCode);
    }

    private void jumpToAlbumApp(Activity actContext) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        photoPickerIntent.setType("image/*");
//        actContext.startActivityForResult(photoPickerIntent, mCameraCode);
    }

    public static void jumpToSetting(Activity actContext) {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.parse("package:" + actContext.getPackageName()));
        actContext.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

}
