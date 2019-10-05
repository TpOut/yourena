package com.yourena.tpout.settings.custompref;

import android.os.Bundle;
import android.preference.PreferenceManager;

import com.yourena.tpout.settings.BaseActivity;
import com.yourena.tpout.settings.MyApplication;
import com.yourena.tpout.settings.R;
import com.yourena.tpout.settings.frag.pref.LifecyclePrefFragment;

/**
 * 简单使用三层布局<br>
 *     viewPager + fragment, fragment内部继续切换层级
 *     而不是像header一样左右分屏，属于垂直覆盖
 * 感觉这应该是人类能接受的最高层级了吧<br>
 * 目前没有写btn的点击事件，其实类似
 */

public class MainActivity extends BaseActivity {

    public String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //记得在xml中添加defaultValue
        PreferenceManager.setDefaultValues(MyApplication.sApp, R.xml.preference1, false);
        PreferenceManager.setDefaultValues(MyApplication.sApp, R.xml.preference2, false);

        getFragmentManager().beginTransaction()
                .replace(R.id.container, new LifecyclePrefFragment())
                .commit();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }


}
