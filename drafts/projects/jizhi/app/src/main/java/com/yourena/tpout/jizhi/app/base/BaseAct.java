package com.yourena.tpout.jizhi.app.base;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by 41675 on 2018/2/18.
 */

public class BaseAct extends AppCompatActivity {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    protected String TAG = this.getClass().getSimpleName();


}
