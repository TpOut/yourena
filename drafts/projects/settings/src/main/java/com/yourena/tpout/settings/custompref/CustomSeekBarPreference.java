package com.yourena.tpout.settings.custompref;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;

/**
 * Created by shengjieli on 17-7-15.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class CustomSeekBarPreference extends Preference {

    public CustomSeekBarPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomSeekBarPreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomSeekBarPreference(Context context) {
        this(context, null);
    }



}
