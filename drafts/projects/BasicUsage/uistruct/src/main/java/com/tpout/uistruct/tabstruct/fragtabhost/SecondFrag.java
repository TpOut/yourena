package com.tpout.uistruct.tabstruct.fragtabhost;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.tpout.uistruct.R;

/**
 * Created by shengjieli on 17-10-28.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class SecondFrag extends BaseFragment {

    public static final String TAG_SECOND = "TAG_SECOND";

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((TextView) view.findViewById(R.id.textView)).setText("SecondFrag");
    }
}
