package com.yourena.tpout.settings.processing;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.yourena.tpout.librarys.util.LogUtil;

/**
 * Created by shengjieli on 18-1-5.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 * 简单模拟功能类
 */

public class Functional {

    public static final int SWITCH_TIME = 2 * 1000;
    private String TAG = Functional.class.getSimpleName();

    private static Functional sInstance;

    public static Functional getInstance() {
        if (null == sInstance) {
            sInstance = new Functional();
        }
        return sInstance;
    }

    public interface OnStateChangeListener {
        void onStateChangeListener(int preState, int currentState);
    }

    //偷懒公用一下
    public static int state_on = 0;
    public static int state_switch = 1;
    public static int state_off = 2;

    private final int msg_to_state_on = 1;
    private final int msg_to_state_off = 2;

    private static int sSetNum = 0;

    private int mState = state_off;
    private OnStateChangeListener mOnStateChangeListener;

    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == msg_to_state_on) {
                changeState(state_on);
            } else if (msg.what == msg_to_state_off) {
                changeState(state_off);
            }
        }
    };

    /**
     * 如果是在切换状态下，点击无效（防止ui设置错误）<br>
     * 以三次为循环，第一次点击，功能设置成功，状态延时变化成功<br>
     * 第二次点击，功能设置成功，状态延时变化失败<br>
     * 第三次点击，功能设置失败<br>
     *
     * @return
     */
    public boolean switchState() {
        if (mState == state_switch) {
            LogUtil.d(TAG , "---switchState---no effect for switching ! ");
            return false;
        }
        sSetNum++;
        int msg_to_state = -1;
        if (sSetNum % 3 == 1) {
            if (mState == state_off) {
                msg_to_state = msg_to_state_on;
            } else if (mState == state_on) {
                msg_to_state = msg_to_state_off;
            }
            changeState(state_switch);
            mHandler.sendEmptyMessageDelayed(msg_to_state, SWITCH_TIME);
            return true;
        } else if (sSetNum % 3 == 2) {
            if (mState == state_off) {
                msg_to_state = msg_to_state_off;
            } else if (mState == state_on) {
                msg_to_state = msg_to_state_on;
            }
            changeState(state_switch);
            mHandler.sendEmptyMessageDelayed(msg_to_state, SWITCH_TIME);
            return true;
        }
        return false;
    }

    public int getState() {
        return mState;
    }

    private void changeState(int state) {
        if (null != mOnStateChangeListener) {
            mOnStateChangeListener.onStateChangeListener(mState, state);
        }
        mState = state;
    }

    public void setOnStateChangeListener(OnStateChangeListener listener) {
        mOnStateChangeListener = listener;
    }


}
