package com.tpout.baselib.permission;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.tpout.baselib.helper.IntentHelper;

/**
 * Created by shengjieli on 18-12-28.<br>
 * Email address: 416756910@qq.com<br>
 */
public class PermissionDialogHelper {

    public static void showToSettingDialog(final Context actContext) {
        new AlertDialog.Builder(actContext)
                .setTitle("需求权限")
                .setNegativeButton("走开", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("哦", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        IntentHelper.jumpToSetting((Activity) actContext);
                    }
                })
                .show();
    }

}
