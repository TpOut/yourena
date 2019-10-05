package com.tpout.codestruct.mvp;

/**
 * Created by shengjieli on 17-5-4.
 * Email address: shengjieli@ecarx.com.cn
 */

public interface BaseViewI<T extends BasePresenter> {
    T setPresenter();
}
