package com.yourena.tpout.settings.processing;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

/**
 * Created by shengjieli on 18-1-5.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class ProcessingViewModel extends ViewModel {

    private LiveData<Integer> mLiveData;

    public ProcessingViewModel() {

    }

    private void refresh() {
        if (null == mLiveData) {
            mLiveData = new MutableLiveData<>();
        }
        ((MutableLiveData) mLiveData).setValue(ProcessingBean.sProcessingBean.state);
    }

    @NonNull
    public LiveData<Integer> getCallBeanLiveData() {
        refresh();
        return mLiveData;
    }

}
