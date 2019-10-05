package com.yourena.tpout.settings.custompref;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

import com.yourena.tpout.librarys.util.LogUtil;
import com.yourena.tpout.settings.R;

import java.util.List;

/**
 * Created by shengjieli on 17-7-17.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 * 自定义失败。。。只能自定义header，即布局中的list
 * 替换fragment的container不能用，因为prefs的id不可改
 */

public class CustomPreferenceActivity extends PreferenceActivity {

    private String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_custom);
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        super.onBuildHeaders(target);
        loadHeadersFromResource(R.xml.header, target);
    }

    @Override
    public boolean onIsMultiPane() {
        return false;
    }

    public void startPreferenceFragment(Fragment fragment, boolean push) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.prefs, fragment);
        if (push) {
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.addToBackStack(":android:prefs");
        } else {
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        }
        transaction.commitAllowingStateLoss();
    }

    // TODO: 17-7-15 这个的判断意义是什么。暂时没看
    @Override
    protected boolean isValidFragment(String fragmentName) {
        String[] paths = fragmentName.split("\\.");
        String lastPath = paths[paths.length - 1];
        LogUtil.d(TAG, "isValidFragment() -- args fragmentName is : " + fragmentName + " | lastPath is " + lastPath);
        if (lastPath.equals("SettingPrefFragment1")
                || lastPath.equals("SettingPrefFragment2")) {
            return true;
        }
        return false;
    }
}
