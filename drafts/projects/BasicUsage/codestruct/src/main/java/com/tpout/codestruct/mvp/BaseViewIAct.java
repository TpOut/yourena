package com.tpout.codestruct.mvp;

import android.os.Bundle;

/**
 * Created by shengjieli on 17-12-28.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public abstract class BaseViewIAct<T extends BaseActPresenter> extends BaseAct implements BaseActViewI<T> {

    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = setPresenter();
    }

}
