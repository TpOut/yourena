package com.tpout.baselib.supportautodispose;

import android.os.Looper;

import com.uber.autodispose.android.AutoDisposeAndroidPlugins;

import io.reactivex.functions.BooleanSupplier;

public class AutoDisposeAndroidUtil {

    private static final BooleanSupplier MAIN_THREAD_CHECK = new BooleanSupplier() {
        @Override
        public boolean getAsBoolean() throws Exception {
            return Looper.myLooper() == Looper.getMainLooper();
        }
    };

    private AutoDisposeAndroidUtil() {
    }

    public static boolean isMainThread() {
        return AutoDisposeAndroidPlugins.onCheckMainThread(MAIN_THREAD_CHECK);
    }
}