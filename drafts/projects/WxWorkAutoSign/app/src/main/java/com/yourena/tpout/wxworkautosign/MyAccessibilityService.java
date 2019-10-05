package com.yourena.tpout.wxworkautosign;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

import static com.yourena.tpout.wxworkautosign.MainActivity.PACKAGE_WECHAT_WORK;

/**
 * Created by shengjieli on 18-10-19.<br>
 * Email address: 416756910@qq.com<br>
 */
public class MyAccessibilityService extends AccessibilityService {

    private static final String TAG = MyAccessibilityService.class.getSimpleName();

    public static boolean NEED_OPERATE = false;

    private static final String BASE_ID_PATH = PACKAGE_WECHAT_WORK + ":id/";

    private Handler mHandler = new WeakHandler(this);
    private int     step     = 0;

    public void doMessage(Message msg) {
        if (msg.what >= 0 && msg.what < targetTexts.length - 1) {
            if (findTextTargetAndPerform((AccessibilityNodeInfo) msg.obj, targetTexts[msg.what]) || findIdTargetAndPerform((AccessibilityNodeInfo) msg.obj, targetIds[msg.what])) {

            }
            step++;
            senHandlerMsg((AccessibilityNodeInfo) msg.obj);
        }
        if (msg.what == targetTexts.length - 1) {
            step = 0;
            ((AccessibilityNodeInfo) msg.obj).recycle();
        }
    }

    /**
     * 这里以View的结构来看
     * clickable
     * 工作台，false,  父，true
     * 打卡，false, 父，false，父-父 ，true
     * 更新下班卡，true
     * 拍照打卡，false，父，true
     * 拍照按钮
     * 拍照确认
     */
    private String[] targetTexts = new String[]{
            "工作台", "打卡",
            "更新下班卡", "更新",
            "拍照打卡", ""};
    private String[] targetIds   = new String[]{
            BASE_ID_PATH + "alo", BASE_ID_PATH + "md",
            BASE_ID_PATH + "bnx", BASE_ID_PATH + "bo0",
            BASE_ID_PATH + "bn6", BASE_ID_PATH + "pr", BASE_ID_PATH + "pr"
    };
    private int[]    delays      = new int[]{0, 0, 0, 0, 200, 500};

    @Override
    public void onCreate() {
        super.onCreate();
        Lg.d(TAG, "---onCreate--- ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Lg.d(TAG, "---onStartCommand--- ");
        return super.onStartCommand(intent, flags, startId);
    }

    private void gotoMainAct() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * 执行一次性的初始化操作
     */
    @Override
    protected void onServiceConnected() {
        gotoMainAct();
        super.onServiceConnected();
        Lg.d(TAG, "---onServiceConnected--- ");
    }

    /**
     * 事件回调
     *
     * @param event 符合过滤的事件
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        final int eventType = event.getEventType();
        Lg.d(TAG, "---onAccessibilityEvent---eventType : " + eventType);

        switch (eventType) {
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                if (NEED_OPERATE) {
                    findTargetAndTryNext(event.getSource());
                }
                break;
            case AccessibilityEvent.TYPE_VIEW_CLICKED:

                break;
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:

                break;
        }

    }

    private void findTargetAndTryNext(final AccessibilityNodeInfo info) {
        if (null == info) {
            return;
        }
//        int length = targetTexts.length;
//        for (int i = 0; i < length; i++) {
//            if (findTextTargetAndPerform(info, targetTexts[i]) || findIdTargetAndPerform(info, targetIds[i])) {
//
//            }
//        }
        senHandlerMsg(info);
        NEED_OPERATE = false;

    }

    private void senHandlerMsg(AccessibilityNodeInfo info) {
        Lg.d(TAG, "第 " + step + " 步");
        Message message = new Message();
        message.obj = info;
        message.what = step;
        mHandler.sendMessageDelayed(message, delays[step]);
    }

    private boolean findTextTargetAndPerform(AccessibilityNodeInfo info, String text) {
        Log.d(TAG, "---findTextTargetAndPerform---text : " + text);
        if (TextUtils.isEmpty(text)) {
            return false;
        }

        /**
         *         不知道什么情况下会报
         *            System.err:  java.lang.IllegalStateException: Cannot perform this action on a not sealed instance.
         *           at android.view.accessibility.AccessibilityNodeInfo.enforceSealed(AccessibilityNodeInfo.java:2918)
         *            ...
         *         调试的下午是一直不报错的，到了傍晚就一定报错。。。
         */

        try {
            List<AccessibilityNodeInfo> nodeInfosByText = info.findAccessibilityNodeInfosByText(text);
            if (null != nodeInfosByText && nodeInfosByText.size() > 0) {
                return perform(nodeInfosByText.get(0), info);
            }else {
                Lg.d(TAG, "文字获取失败");
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
            Lg.d(TAG, "文字获取失败");
        }

        return false;
    }

    private boolean findIdTargetAndPerform(AccessibilityNodeInfo info, String id) {
        Log.d(TAG, "---findIdTargetAndPerform---id : " + id);
        if (TextUtils.isEmpty(id)) {
            return false;
        }
        /**
         *         不知道什么情况下会报
         *           System.err:  java.lang.IllegalStateException: Cannot perform this action on a not sealed instance.
         *           at android.view.accessibility.AccessibilityNodeInfo.enforceSealed(AccessibilityNodeInfo.java:2918)
         *            ...
         *         调试的下午是一直不报错的，到了傍晚就一定报错。。。
         */

        try {
            List<AccessibilityNodeInfo> nodeInfosByText = info.findAccessibilityNodeInfosByViewId(id);
            if (null != nodeInfosByText && nodeInfosByText.size() > 0) {
                return perform(nodeInfosByText.get(0), info);
            }else {
                Lg.d(TAG, "id获取失败");
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
            Lg.d(TAG, "id获取失败");
        }
        return false;
    }

    private boolean perform(AccessibilityNodeInfo nodeInfo, AccessibilityNodeInfo sourceNodeInfo) {
        boolean result = nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        Log.d(TAG, "---perform---result : " + result);
        if (!result) {
            return performParent(nodeInfo, sourceNodeInfo);
        }
        return true;
    }

    private boolean performParent(AccessibilityNodeInfo nodeInfo, AccessibilityNodeInfo sourceNodeInfo) {
        if (nodeInfo == sourceNodeInfo) {
            return false;
        }
        AccessibilityNodeInfo parent = nodeInfo.getParent();
        if (null == parent) {
            return false;
        }
        boolean b = parent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        Log.d(TAG, "---performParent---result : " + b);
        if (b) {
            return true;
        }
        return performParent(parent, sourceNodeInfo);
    }


    /**
     * 在系统打断我们服务提供的反馈时被回调
     * （目前理解成，onAccessibilityEvent中的代码会在执行到一半时被终止）
     */
    @Override
    public void onInterrupt() {

    }

    @Override
    protected boolean onGesture(int gestureId) {
        return super.onGesture(gestureId);
    }

    /**
     * 释放资源
     *
     * @param intent
     * @return
     */
    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

}
