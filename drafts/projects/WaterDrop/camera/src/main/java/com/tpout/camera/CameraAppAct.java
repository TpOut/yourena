package com.tpout.camera;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import com.tpout.baselib.permission.RequestPhotoFrag;
import com.tpout.baselib.util.BitmapUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by shengjieli on 18-12-26.<br>
 * Email address: 416756910@qq.com<br>
 */
public class CameraAppAct extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_VIDEO_CAPTURE = 2;

    private VideoView mVideoView;
    private ImageView mImageView;

    private RequestPhotoFrag.OnEndCallback mPhotoCallback;
    private RequestPhotoFrag.OnEndCallback mVideoCallback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_start_other_camera_app);
        initView();
    }

    private void initView() {
        mImageView = findViewById(R.id.iv);
        mVideoView = findViewById(R.id.vv);
    }

    /**
     * 拍照
     *
     * @param v
     */
    public void onCapture(View v) {
        if (null == mPhotoCallback) {
            mPhotoCallback = new RequestPhotoFrag.OnEndCallback() {
                @Override
                public void onEnd() {
                    dispatchTakeRawPictureIntent();
//        dispatchTakePictureIntent();
                }
            };
        }
        RequestPhotoFrag.newInstance(mPhotoCallback).showNow(getSupportFragmentManager(), "temp");
    }

    /**
     * 如果只是要获取较小的图，可以在result的data中获取
     */
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void dispatchTakeRawPictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.tpout.camera.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    /**
     * 拍摄
     *
     * @param v
     */
    public void onRecord(View v) {
        if (null == mVideoCallback) {
            mVideoCallback = new RequestPhotoFrag.OnEndCallback() {
                @Override
                public void onEnd() {
                    dispatchTakeVideoIntent();
                }
            };
        }
        RequestPhotoFrag.newInstance(mVideoCallback).showNow(getSupportFragmentManager(), "temp");
    }

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = intent.getExtras();
            //这里的data只能算是一个icon的清晰度
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageView.setImageBitmap(BitmapUtil.scaleSizeFromPath(mImageView.getWidth(), mImageView.getHeight(), mCurrentPhotoPath));
            mVideoView.setVisibility(View.GONE);
//            galleryAddPic();
        } else if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = intent.getData();
            mVideoView.setVideoURI(videoUri);
            mVideoView.setVisibility(View.VISIBLE);
            mVideoView.start();
        }
    }

}
