package com.yourena.tpout.wxworkautosign;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by shengjieli on 18-10-22.<br>
 * Email address: 416756910@qq.com<br>
 */
public class WeakHandler extends Handler {

    public WeakReference<MyAccessibilityService> mReference;

    public WeakHandler(MyAccessibilityService myAccessibilityService) {
        mReference = new WeakReference<>(myAccessibilityService);
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        MyAccessibilityService myAccessibilityService = mReference.get();
        if (null != myAccessibilityService) {
            myAccessibilityService.doMessage(msg);
        }
    }
}
