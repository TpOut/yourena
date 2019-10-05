package com.yourena.tpout.jizhi.frag;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yourena.tpout.jizhi.R;
import com.yourena.tpout.jizhi.app.base.BaseFrag;
import com.yourena.tpout.jizhi.app.util.SpUtil;

/**
 * Created by 41675 on 2018/2/23.
 */

public class UserFrag extends BaseFrag {

    private static String KEY_NAME = "KEY_NAME";

    public static UserFrag newInstance(String name) {
        UserFrag frag = new UserFrag();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_NAME, name);
        frag.setArguments(bundle);
        return frag;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private TextInputEditText mEt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEt = view.findViewById(R.id.tiet);
        if (null != getArguments()) {
            mEt.setText(getArguments().getString(KEY_NAME));
        }
        view.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpUtil.getInstance().putString(SpUtil.KEY_USER_NAME, mEt.getText().toString());
                UserFrag.this.dismiss();
            }
        });
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }
}
