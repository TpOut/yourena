package com.tpout.camera.module;

import com.tpout.baselib.base.BaseApp;
import com.tpout.baselib.util.AppUtil;
import com.tpout.baselib.util.LogUtil;

/**
 * Created by shengjieli on 18-12-28.<br>
 * Email address: 416756910@qq.com<br>
 */
public class MyApplication extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.init();
        AppUtil.init(this);
    }

}
