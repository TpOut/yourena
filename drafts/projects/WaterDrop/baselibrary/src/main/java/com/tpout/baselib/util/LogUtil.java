package com.tpout.baselib.util;

import com.example.baselibrary.BuildConfig;

import org.jetbrains.annotations.Nullable;

import timber.log.Timber;

/**
 * Created by shengjieli on 18-12-27.<br>
 * Email address: 416756910@qq.com<br>
 */
public class LogUtil {

    public static void init() {
        Timber.plant(new Timber.DebugTree() {
            @Override
            protected boolean isLoggable(@Nullable String tag, int priority) {
                return BuildConfig.DEBUG;
            }
        });
    }

}
