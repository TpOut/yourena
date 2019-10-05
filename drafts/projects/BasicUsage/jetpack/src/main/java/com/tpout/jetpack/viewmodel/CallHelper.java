package com.tpout.jetpack.viewmodel;



/**
 * Created by shengjieli on 18-1-2.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class    CallHelper {

    private static String TAG = CallHelper.class.getSimpleName();

    private static CallHelper sCallHelper;

    public static CallHelper getInstance() {
        if (null == sCallHelper) {
            sCallHelper = new CallHelper();
        }
        return sCallHelper;
    }

    private MyCall mMyCall;

    public CallHelper() {
        mMyCall = new MyCall();
        refresh();
//        mOnCall = OnCall.create(MyApplication.sApp);
    }

    private void refresh() {
        MyCall.refreshCall(mMyCall);
    }

    //other place change the call value
    public void changeCall(){

    }

    public MyCall getMyCall() {
        return mMyCall;
    }

    public void setMyCall(MyCall myCall) {
        mMyCall = myCall;
    }
}
