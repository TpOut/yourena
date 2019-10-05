package com.tpout.baselib.supportautodispose;

import android.support.annotation.RestrictTo;

import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

import static android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP;

@RestrictTo(LIBRARY_GROUP)
public abstract class MainThreadDisposable implements Disposable {
    private final AtomicBoolean unsubscribed = new AtomicBoolean();

    @Override
    public final boolean isDisposed() {
        return unsubscribed.get();
    }

    @Override
    public final void dispose() {
        if (unsubscribed.compareAndSet(false, true)) {
            if (AutoDisposeAndroidUtil.isMainThread()) {
                onDispose();
            } else {
                AndroidSchedulers.mainThread().scheduleDirect(new Runnable() {
                    @Override
                    public void run() {
                        onDispose();
                    }
                });
            }
        }
    }

    protected abstract void onDispose();
}