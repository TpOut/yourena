package com.yourena.tpout.settings.official;

import android.os.Build;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;

import com.yourena.tpout.librarys.util.LogUtil;
import com.yourena.tpout.settings.R;

import java.util.List;

/**
 * Created by shengjieli on 17-7-14.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 * <p>
 * 使用标头的话必须要继承PreferenceActivity，以期使用onBuildHeaders()
 * 如此，在720sw的设备上，会自动分成左右布局（本来是深层布局）
 */

public class MyPreferenceActivity extends PreferenceActivity {

    private String TAG = getClass().getSimpleName();
    private List<Header> mList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        addPreferencesFromResource(R.xml.preference1);

        //如果小于3.0版本，onBuildHeaders无效
        //需要做逻辑处理，具体参看原文 https://developer.android.google.cn/guide/topics/ui/settings.html#PreferenceHeaders

    }

    /**
     * 调用此方法之后，就不会调用onCreate()了
     *
     * @param target
     */
    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.header, target);
        mList = target;
        mList.clear();
        //这里有个问题，header的每个项，会对应一个fragment，也可以传入data，那么如何动态传？
        //感觉只能是写两个xml，然后动态的判断加载。。。
        loadHeadersFromResource(R.xml.header, target);
    }

    @Override
    public boolean onIsMultiPane() {
        return true;
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
