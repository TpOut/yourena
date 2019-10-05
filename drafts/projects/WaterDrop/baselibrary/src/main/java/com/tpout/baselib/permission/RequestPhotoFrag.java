package com.tpout.baselib.permission;

import android.Manifest;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.tpout.baselib.base.BaseDialogFrag;
import com.tpout.baselib.util.ToastUtil;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;


/**
 * Created by shengjieli on 2018/5/11.<br>
 * Email address: 416756910@qq.com<br>
 */
@RuntimePermissions
public class RequestPhotoFrag extends BaseDialogFrag {

    public interface OnEndCallback {
        void onEnd();
    }

    private OnEndCallback mOnEndCallback;

    public static RequestPhotoFrag newInstance(OnEndCallback callback) {
        RequestPhotoFrag requestPhotoFragment = new RequestPhotoFrag();
        requestPhotoFragment.mOnEndCallback = callback;
        return requestPhotoFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        RequestPhotoFragPermissionsDispatcher.requestCameraWithPermissionCheck(this);
        return super.onCreateDialog(savedInstanceState);
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    public void requestCamera() {
        RequestPhotoFragPermissionsDispatcher.requestExternalWithPermissionCheck(this);
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    public void denyCamera() {
        ToastUtil.showShort("摄像头权限授予失败");
        this.dismiss();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    public void neverAskCamera() {
        PermissionDialogHelper.showToSettingDialog(getActivity());
        this.dismiss();
    }


    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void requestExternal() {
        if (!FeatureUtil.checkCameraHardware(getContext())) {
            ToastUtil.showShort("抱歉,您的设备上没有摄像头");
            return;
        }
        mOnEndCallback.onEnd();
        this.dismiss();
    }

    @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void denyExternal() {
        ToastUtil.showShort("读写存储权限获取失败");
        this.dismiss();
    }

    @OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    public void neverAskExternal() {
        PermissionDialogHelper.showToSettingDialog(getActivity());
        this.dismiss();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        RequestPhotoFragPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

}
