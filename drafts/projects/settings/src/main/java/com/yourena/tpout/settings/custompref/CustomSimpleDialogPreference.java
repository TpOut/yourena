package com.yourena.tpout.settings.custompref;

import android.content.Context;
import android.preference.DialogPreference;
import android.util.AttributeSet;

import com.yourena.tpout.settings.R;

/**
 * Created by shengjieli on 17-10-25.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class CustomSimpleDialogPreference extends DialogPreference {

    public CustomSimpleDialogPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomSimpleDialogPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDialogLayoutResource(R.layout.activity_custom);
        setPositiveButtonText(android.R.string.ok);
        setNegativeButtonText(android.R.string.cancel);

        setDialogIcon(null);
    }

    @Override
    protected void onDialogClosed(boolean positiveResult) {
        // When the user selects "OK", persist the new value
        if (positiveResult) {
            persistString("someValueUserSelect");
        }
    }

}
