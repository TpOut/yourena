package com.yourena.tpout.wxworkautosign;

import android.accessibilityservice.AccessibilityButtonController;
import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.accessibilityservice.FingerprintGestureController;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import static android.accessibilityservice.FingerprintGestureController.FINGERPRINT_GESTURE_SWIPE_DOWN;
import static android.accessibilityservice.FingerprintGestureController.FINGERPRINT_GESTURE_SWIPE_LEFT;
import static android.accessibilityservice.FingerprintGestureController.FINGERPRINT_GESTURE_SWIPE_RIGHT;
import static android.accessibilityservice.FingerprintGestureController.FINGERPRINT_GESTURE_SWIPE_UP;
import static android.media.AudioManager.ADJUST_RAISE;
import static android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.ACTION_ACCESSIBILITY_FOCUS;
import static android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.FOCUS_ACCESSIBILITY;
import static android.view.View.FOCUS_FORWARD;
import static android.view.accessibility.AccessibilityNodeInfo.EXTRA_DATA_TEXT_CHARACTER_LOCATION_KEY;

/**
 * Created by shengjieli on 18-10-19.<br>
 * Email address: 416756910@qq.com<br>
 */
public class MyAccessibilityService extends AccessibilityService {

    private static final String TAG = MyAccessibilityService.class.getSimpleName();

    private AudioManager mAudioManager;

    /**
     * 可用性按钮
     */
    private AccessibilityButtonController                             mAccessibilityButtonController;
    private AccessibilityButtonController.AccessibilityButtonCallback mAccessibilityButtonCallback;
    private boolean                                                   mIsAccessibilityButtonAvailable;

    /**
     * 指纹手势
     */
    private FingerprintGestureController                            mGestureController;
    private FingerprintGestureController.FingerprintGestureCallback mFingerprintGestureCallback;
    private boolean                                                 mIsGestureDetectionAvailable;

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         *也可以把一部分判断放这边来
         */
    }

    /**
     * 执行一次性的初始化操作
     */
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
//        initConfigByCode();
        initAccessibilityButton();
        initFingerprintGesture();
    }

    private void initConfigByCode() {
        AccessibilityServiceInfo serviceInfo = getServiceInfo();
        serviceInfo.eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED |
                AccessibilityEvent.TYPE_VIEW_FOCUSED;

        // If you only want this service to work with specific applications, set their
        // package names here.  Otherwise, when the service is activated, it will listen
        // to events from all applications.
        serviceInfo.packageNames = new String[]
                {"com.example.android.myFirstApp", "com.example.android.mySecondApp"};

        // Set the type of feedback your service will provide.
        serviceInfo.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;

        // Default services are invoked only if no package-specific ones are present
        // for the type of AccessibilityEvent generated.  This service *is*
        // application-specific, so the flag isn't necessary.  If this was a
        // general-purpose service, it would be worth considering setting the
        // DEFAULT flag.

        // info.flags = AccessibilityServiceInfo.DEFAULT;

        serviceInfo.notificationTimeout = 100;

        this.setServiceInfo(serviceInfo);
    }

    /**
     * 需求canRequestFingerprintGestures="true"
     * flagRequestFingerprintGestures
     */
    private void initFingerprintGesture() {
        if (Build.VERSION.SDK_INT >= 26) {
            mGestureController = getFingerprintGestureController();
            mIsGestureDetectionAvailable =
                    mGestureController.isGestureDetectionAvailable();
            if (mFingerprintGestureCallback != null
                    || !mIsGestureDetectionAvailable) {
                return;
            }

            mFingerprintGestureCallback = new FingerprintGestureController.FingerprintGestureCallback() {
                @Override
                public void onGestureDetected(int gesture) {
                    switch (gesture) {
                        case FINGERPRINT_GESTURE_SWIPE_DOWN:
//                                    moveGameCursorDown();
                            break;
                        case FINGERPRINT_GESTURE_SWIPE_LEFT:
//                                    moveGameCursorLeft();
                            break;
                        case FINGERPRINT_GESTURE_SWIPE_RIGHT:
//                                    moveGameCursorRight();
                            break;
                        case FINGERPRINT_GESTURE_SWIPE_UP:
//                                    moveGameCursorUp();
                            break;
                        default:
                            Log.e(TAG, "Error: Unknown gesture type detected!");
                            break;
                    }
                }

                @Override
                public void onGestureDetectionAvailabilityChanged(boolean available) {
                    mIsGestureDetectionAvailable = available;
                }
            };

            if (mFingerprintGestureCallback != null) {
                mGestureController.registerFingerprintGestureCallback(
                        mFingerprintGestureCallback, null);
            }
        }
    }

    /**
     * 需求flagRequestAccessibilityButton
     */
    private void initAccessibilityButton() {
        if (Build.VERSION.SDK_INT >= 26) {
            mAccessibilityButtonController = getAccessibilityButtonController();
            mIsAccessibilityButtonAvailable =
                    mAccessibilityButtonController.isAccessibilityButtonAvailable();
            if (!mIsAccessibilityButtonAvailable) {
                return;
            }
            //xml中配置过了
//            AccessibilityServiceInfo serviceInfo = getServiceInfo();
//            serviceInfo.flags
//                    |= AccessibilityServiceInfo.FLAG_REQUEST_ACCESSIBILITY_BUTTON;
//            setServiceInfo(serviceInfo);

            mAccessibilityButtonCallback =
                    new AccessibilityButtonController.AccessibilityButtonCallback() {
                        @Override
                        public void onClicked(AccessibilityButtonController controller) {
                            Log.d("MY_APP_TAG", "Accessibility button pressed!");

                        }

                        @Override
                        public void onAvailabilityChanged(
                                AccessibilityButtonController controller, boolean available) {
                            if (controller.equals(mAccessibilityButtonController)) {
                                mIsAccessibilityButtonAvailable = available;
                            }
                        }
                    };

            if (mAccessibilityButtonCallback != null) {
                mAccessibilityButtonController.registerAccessibilityButtonCallback(
                        mAccessibilityButtonCallback, null);
            }
        }
    }

    /**
     * 事件回调
     *
     * @param event 符合过滤的事件
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        final int eventType = event.getEventType();
        String eventText = null;
        switch(eventType) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                eventText = "Clicked: ";
                break;
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                eventText = "Focused: ";
                break;
        }

        eventText = eventText + event.getContentDescription();


        //信息相关
        event.getRecordCount();
//            event.getRecord()
        AccessibilityNodeInfo interactedNodeInfo = event.getSource();//需求canRetrieveWindowContent="true"
//        interactedNodeInfo.getParent(); //需求FLAG_INCLUDE_NOT_IMPORTANT_VIEWS

        if(null == interactedNodeInfo){
            return;
        }

        if (Build.VERSION.SDK_INT >= 26) {
            //专有音频
            if (interactedNodeInfo.getText().equals("Increase volume")) {
                if (null == mAudioManager) {
                    mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
                }
                if (null != (AudioManager) getSystemService(AUDIO_SERVICE)) {
                    mAudioManager.adjustStreamVolume(AudioManager.STREAM_ACCESSIBILITY, ADJUST_RAISE, 0);
                }
            }

            //文本信息
            interactedNodeInfo.isShowingHintText();
//            interactedNodeInfo.setShowingHintText();
//            interactedNodeInfo.getHintText();
//            interactedNodeInfo.refreshWithExtraData(EXTRA_DATA_TEXT_CHARACTER_LOCATION_KEY,)

            //部分info具有范围信息, max不存在为Float.POSITIVE_INFINITY， min不存在为Float.NEGATIVE_INFINITY
            interactedNodeInfo.getRangeInfo();
//            AccessibilityNodeInfo.RangeInfo.obtain()

        }


            if (Build.VERSION.SDK_INT >= 16) {
                interactedNodeInfo.performAction(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD);
                performGlobalAction(AccessibilityService.GLOBAL_ACTION_BACK);

                //焦点相关
                interactedNodeInfo.findFocus(FOCUS_ACCESSIBILITY);
                interactedNodeInfo.focusSearch(FOCUS_FORWARD);
                interactedNodeInfo.performAction(ACTION_ACCESSIBILITY_FOCUS);

            }


            interactedNodeInfo.recycle();
        }

        /**
         * 在系统打断我们服务提供的反馈时被回调
         * （目前理解成，onAccessibilityEvent中的代码会在执行到一半时被终止）
         */
        @Override
        public void onInterrupt () {

        }

        @Override
        protected boolean onGesture ( int gestureId){
            return super.onGesture(gestureId);
        }

        /**
         * 释放资源
         *
         * @param intent
         * @return
         */
        @Override
        public boolean onUnbind (Intent intent){
            return super.onUnbind(intent);
        }

    }
