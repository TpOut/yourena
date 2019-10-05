package com.yourena.tpout.settings.frag.pref;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yourena.tpout.librarys.util.LogUtil;

/**
 * Created by shengjieli on 17-7-15.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class BasePrefFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

    protected String TAG = getClass().getSimpleName();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LogUtil.d(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        LogUtil.d(TAG, "onAttach");
        super.onAttach(context);
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        LogUtil.d(TAG, "onAttachFragment");
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        LogUtil.d(TAG, "onInflate");
        super.onInflate(context, attrs, savedInstanceState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        LogUtil.d(TAG, "onViewStateRestored");
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.d(TAG, "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        LogUtil.d(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        LogUtil.d(TAG, "onResume");
        super.onResume();

    }

    @Override
    public void onStart() {
        LogUtil.d(TAG, "onStart");
        super.onStart();

    }

    @Override
    public void onPause() {
        LogUtil.d(TAG, "onPause");
        super.onPause();

    }

    @Override
    public void onStop() {
        LogUtil.d(TAG, "onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        LogUtil.d(TAG, "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        LogUtil.d(TAG, "onDetach");
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        LogUtil.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        LogUtil.d(TAG, "onSharedPreferenceChanged and key is " + key);

    }
}
