//package com.tpout.baselib.permission;
//
//import android.Manifest;
//
//import android.annotation.SuppressLint;
//import android.app.Dialog;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.text.TextUtils;
//
//
//import com.haotunet.app.goldtreasure.base.BaseDialogFrag;
//import com.haotunet.app.goldtreasure.utils.PermissionDialogHelper;
//import com.haotunet.app.goldtreasure.utils.ToastUtils;
//
//import permissions.dispatcher.NeedsPermission;
//import permissions.dispatcher.OnNeverAskAgain;
//import permissions.dispatcher.OnPermissionDenied;
//import permissions.dispatcher.RuntimePermissions;
//
///**
// * Created by shengjieli on 2018/5/11.<br>
// * Email address: 416756910@qq.com<br>
// * 由于很多地方使用类似的功能，需要进行抽取<br>
// * 而permissionDispatcher支持activity和fragment注解<br>
// * 所以写一个中间类来过渡
// */
//@RuntimePermissions
//public class RequestCallFragment extends BaseDialogFrag {
//
//    private static final String EXTRA_PHONE_NUMBER = "EXTRA_PHONE_NUMBER";
//
//    private String mNumber;
//
//    public static RequestCallFragment newInstance(String number) {
//        RequestCallFragment fragment = new RequestCallFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString(EXTRA_PHONE_NUMBER, number);
//        fragment.setArguments(bundle);
//        return fragment;
//    }
//
//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        if (null != getArguments()) {
//            mNumber = getArguments().getString(EXTRA_PHONE_NUMBER);
//        }
//        RequestCallFragmentPermissionsDispatcher.requestCallWithPermissionCheck(this);
//        return super.onCreateDialog(savedInstanceState);
//    }
//
//
//    @SuppressLint("MissingPermission")
//    @NeedsPermission(Manifest.permission.CALL_PHONE)
//    public void requestCall() {
//        if (!TextUtils.isEmpty(mNumber)) {
//            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + mNumber));
//            getContext().startActivity(intent);
//        }
//        this.dismiss();
//    }
//
//    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
//    public void denyCall() {
//        ToastUtils.showShort("权限授予失败");
//        this.dismiss();
//    }
//
//    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
//    public void neverAskCall() {
//        PermissionDialogHelper.showSettingDialog(getActivity(), "需要电话权限");
//        this.dismiss();
//    }
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        RequestCallFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
//    }
//}
