package com.yourena.tpout.settings.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yourena.tpout.settings.R;

/**
 * Created by shengjieli on 18-1-5.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 * <p>
 * 由于官方的setting使用preferenceActivity，且固定死了形式，所以如果要实现自己的结构<br>
 * 需要重新架构并类似的实现preference
 */

public class MyCustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_mycustomview);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_container, new SimpleFrag())
                .commit();
    }

}
