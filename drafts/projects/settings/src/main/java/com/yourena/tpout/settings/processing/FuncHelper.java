package com.yourena.tpout.settings.processing;

/**
 * Created by shengjieli on 18-1-5.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class FuncHelper {

    private static FuncHelper sInstance;

    public static FuncHelper getInstance() {
        if (null == sInstance) {
            sInstance = new FuncHelper();
        }
        return sInstance;
    }

    private Functional mFunctional;
    private Functional.OnStateChangeListener mOnStateChangeListener;

    public FuncHelper() {
        mFunctional = Functional.getInstance();
        ProcessingBean.sProcessingBean.state = mFunctional.getState();
        mFunctional.setOnStateChangeListener(new Functional.OnStateChangeListener() {
            @Override
            public void onStateChangeListener(int preState, int currentState) {
                ProcessingBean.sProcessingBean.state = currentState;
                if(null != mOnStateChangeListener){
                    mOnStateChangeListener.onStateChangeListener(preState, currentState);
                }
            }
        });
    }

    public void setOnStateChangeListener(Functional.OnStateChangeListener onStateChangeListener){
        mOnStateChangeListener = mOnStateChangeListener;
    }

    public void clearOnStateChangeListener(){
        mOnStateChangeListener = null;
    }

}
