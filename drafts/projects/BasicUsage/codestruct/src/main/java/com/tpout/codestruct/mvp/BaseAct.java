package com.tpout.codestruct.mvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public abstract class BaseAct extends AppCompatActivity {

    protected String TAG;

    public abstract String getTag();

    public abstract void doHandleMessage(Message message);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getTag();
//        LogUtil.d(TAG, "onCreate");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        LogUtil.d(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
//        LogUtil.d(TAG, "onStart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
//        LogUtil.d(TAG, "onRestoreInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        LogUtil.d(TAG, "onResume");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        LogUtil.d(TAG, "onNewIntent");
    }

    @Override
    protected void onPause() {
        super.onPause();
//        LogUtil.d(TAG, "onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        LogUtil.d(TAG, "onSaveInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
//        LogUtil.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        LogUtil.d(TAG, "onDestroy");
    }

    public static class WeakRefHandler extends Handler {

        private WeakReference<Activity> mActivityReference;

        public WeakRefHandler(Activity activity) {
            mActivityReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final BaseAct activity = (BaseAct) mActivityReference.get();
            if (activity != null) {
//                LogUtil.d(activity.TAG, "activity.doHandleMessage");
                activity.doHandleMessage(msg);
            }
        }

    }

}
