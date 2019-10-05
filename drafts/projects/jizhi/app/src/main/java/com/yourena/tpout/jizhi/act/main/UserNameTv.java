package com.yourena.tpout.jizhi.act.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.yourena.tpout.jizhi.app.util.SpUtil;

/**
 * Created by 41675 on 2018/2/23.
 * 用于抽屉头布局的用户名
 */

public class UserNameTv extends AppCompatTextView implements
        SharedPreferences.OnSharedPreferenceChangeListener {

    public UserNameTv(Context context) {
        super(context);
        init();
    }

    public UserNameTv(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UserNameTv(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setTextBySp();
        SpUtil.getInstance().addChangeListener(this);
    }

    private void setTextBySp() {
        String text = SpUtil.getInstance().getString(SpUtil.KEY_USER_NAME);
//        if (text.equals("")) {
//            text = "";
//        }
        setText(text);
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        SpUtil.getInstance().removeChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences
                                                  sharedPreferences,
                                          String key) {
        if (key.equals(SpUtil.KEY_USER_NAME)) {
            setTextBySp();
        }
    }
}
