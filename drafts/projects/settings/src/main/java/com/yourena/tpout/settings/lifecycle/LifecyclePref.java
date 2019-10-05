package com.yourena.tpout.settings.lifecycle;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.yourena.tpout.librarys.util.LogUtil;
import com.yourena.tpout.settings.R;

/**
 * Created by shengjieli on 2017/10/25.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class LifecyclePref extends Preference {

    private String TAG = this.getClass().getSimpleName();
    private ViewGroup parent;

    public LifecyclePref(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        LogUtil.d(TAG, "onCreateView");
        this.parent = parent;
        return super.onCreateView(parent);
    }

    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
        LogUtil.d(TAG, "onGetDefaultValue");
        return super.onGetDefaultValue(a, index);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        LogUtil.d(TAG, "onSaveInstanceState");
        return super.onSaveInstanceState();
    }


    @Override
    protected void onAttachedToActivity() {
        LogUtil.d(TAG, "onAttachedToActivity");
        super.onAttachedToActivity();
    }

    @Override
    protected void onAttachedToHierarchy(PreferenceManager preferenceManager) {
        LogUtil.d(TAG, "onAttachedToHierarchy");
        super.onAttachedToHierarchy(preferenceManager);
    }

    @Override
    protected void onBindView(View view) {
        LogUtil.d(TAG, "onBindView");
        super.onBindView(view);
    }

    @Override
    public void onParentChanged(Preference parent, boolean disableChild) {
        LogUtil.d(TAG, "onParentChanged");
        super.onParentChanged(parent, disableChild);
    }

    @Override
    public void onDependencyChanged(Preference dependency, boolean disableDependent) {
        LogUtil.d(TAG, "onDependencyChanged");
        super.onDependencyChanged(dependency, disableDependent);
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        LogUtil.d(TAG, "onRestoreInstanceState");
        super.onRestoreInstanceState(state);
    }

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        LogUtil.d(TAG, "onSetInitialValue");
        super.onSetInitialValue(restorePersistedValue, defaultValue);
    }

    @Override
    protected void onPrepareForRemoval() {
        LogUtil.d(TAG, "onPrepareForRemoval");
        super.onPrepareForRemoval();
    }

    @Override
    protected void onClick() {
        super.onClick();

    }
}
