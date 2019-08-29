package com.tpout.jetpack.viewmodel;

/**
 * Created by shengjieli on 18-1-3.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class MyCall {

    private int type = -1;
    private int state = -1;
    private int time = -1;
    private boolean isCallBack = false;
    private int startCause = -1;

    public MyCall() {

    }

    public MyCall(int type, int state, int time, boolean isCallBack, int startCause) {
        this.type = type;
        this.state = state;
        this.time = time;
        this.isCallBack = isCallBack;
        this.startCause = startCause;
    }

    public static void refreshCall(MyCall myCall) {
        //doSomething to refresh
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isCallBack() {
        return isCallBack;
    }

    public void setCallBack(boolean callBack) {
        isCallBack = callBack;
    }

    public int getStartCause() {
        return startCause;
    }

    public void setStartCause(int startCause) {
        this.startCause = startCause;
    }
}
