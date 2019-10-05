package com.yourena.tpout.settings.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yourena.tpout.settings.R;

/**
 * Created by shengjieli on 18-1-5.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class SimpleFrag extends BaseFrag {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_simple, container, false);
    }


}
