package com.yourena.tpout.wxworkautosign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import static com.yourena.tpout.wxworkautosign.MyAccessibilityService.NEED_OPERATE;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((TextView) findViewById(R.id.tv)).setText("当前是否开启辅助：" + isAccessibilitySettingsOn(MyAccessibilityService.class.getSimpleName(), getApplicationContext()));
    }

    public void onSignClick(View v) {
        NEED_OPERATE = true;
        startService(new Intent(this, MyAccessibilityService.class));
        gotoWeWork();
    }

    public static final String PACKAGE_WECHAT_WORK = "com.tencent.wework";

    private void gotoWeWork() {
        Intent intent = getPackageManager().getLaunchIntentForPackage(PACKAGE_WECHAT_WORK);
        if (null != intent) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    public void onGoAsSettings(View v) {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(intent);
    }

    private boolean isAccessibilitySettingsOn(String accessibilityServiceName, Context context) {
        int accessibilityEnable = 0;
        try {
            accessibilityEnable = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED, 0);
        } catch (Exception e) {
            Lg.d(TAG, "get accessibility enable failed, the err:" + e.getMessage());
        }
        if (accessibilityEnable == 1) {
            TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');
            String settingValue = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    String accessibilityService = mStringColonSplitter.next();
                    //华为获取的路径是com.yourena.tpout.wxworkautosign/com.yourena.tpout.wxworkautosign.MyAccessibilityService
                    String[] split = accessibilityService.split("\\.");
                    if (split[split.length - 1].equals(accessibilityServiceName)) {
                        Lg.d(TAG, "We've found the correct setting - accessibility is switched on!");
                        return true;
                    }
                }
            }
        } else {
            Lg.d(TAG, "Accessibility service disable");
        }
        return false;
    }

}
