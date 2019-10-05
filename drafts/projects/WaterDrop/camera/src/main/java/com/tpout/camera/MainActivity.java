package com.tpout.camera;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tpout.camera.api.SystemApiAct;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onStartOtherCameraApp(View v) {
        startActivity(new Intent(this, CameraAppAct.class));
    }

    public void onInvokeSystemApi(View v) {
            startActivity(new Intent(this, SystemApiAct.class));
    }

}
