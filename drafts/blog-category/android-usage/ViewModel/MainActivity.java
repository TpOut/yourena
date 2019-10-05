package com.tpout.jetpack.viewmodel;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tpout.jetpack.R;

public class MainActivity extends AppCompatActivity {

    private CallViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        viewModel = ViewModelProviders.of(this).get(CallViewModel.class);
        viewModel.getCallBeanLiveData().observe(this, new Observer<MyCall>() {
            @Override
            public void onChanged(@Nullable MyCall myCall) {
                //while MyCall changed in CallHelper(or every other place), this method is be invoked
            }
        });
    }

}
