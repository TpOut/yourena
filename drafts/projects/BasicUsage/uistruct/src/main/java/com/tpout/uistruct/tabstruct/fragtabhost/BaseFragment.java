package com.tpout.uistruct.tabstruct.fragtabhost;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tpout.uistruct.R;

/**
 * Created by shengjieli on 17-10-28.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_base, container, false);
    }
}
