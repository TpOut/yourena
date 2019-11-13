package com.tpout.jetpack.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;


/**
 * Created by shengjieli on 18-1-3.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class CallViewModel extends ViewModel {

    private LiveData<MyCall> mLiveData;

    public CallViewModel() {

    }

    private void refresh() {
        if (null == mLiveData) {
            mLiveData = new MutableLiveData<>();
        }
        CallHelper instance = CallHelper.getInstance();
        ((MutableLiveData<MyCall>) mLiveData).setValue(instance.getMyCall());
    }

    @NonNull
    public LiveData<MyCall> getCallBeanLiveData() {
        refresh();
        return mLiveData;
    }

}
