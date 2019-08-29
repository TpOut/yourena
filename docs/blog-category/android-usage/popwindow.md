

```
public class FloatWindowHelper {

    private static final String TAG = "FloatWindowHelper";

    private boolean isShown = false;
    private boolean isFirstAdd = true;

    private View mView = null;
    private TextView mTextView = null;
    private WindowManager mWindowManager = null;
    private Context mAppContext = null;
    private WindowManager.LayoutParams params;

    private static String defaultString = "";

    public static FloatWindowHelper sInstance;

    public static FloatWindowHelper getInstance(Context appContext) {
        if (sInstance == null) {
            sInstance = new FloatWindowHelper(appContext);
        }
        return sInstance;
    }

    private FloatWindowHelper(Context appContext) {
        mAppContext = appContext;
        // 获取WindowManager
        mWindowManager = (WindowManager) mAppContext
                .getSystemService(Context.WINDOW_SERVICE);

        setUpView();
        initParams();
    }

    public void showPopupWindow(String message) {
        LogUtil.d(TAG, "current message is :" + message);
        if (isShown) {
            LogUtil.d(TAG, "return because already shown");
            String display = defaultString + message;
            mTextView.setText(display);
            mWindowManager.updateViewLayout(mView, params);
            return;
        }
        isShown = true;
        if (isFirstAdd) {
            mTextView.setText(defaultString);
            mWindowManager.addView(mView, params);
            isFirstAdd = false;
        }
        if (null != mView) {
            mView.setVisibility(View.VISIBLE);
        }
        LogUtil.d(TAG, "showPopupWindow");

    }

    /**
     * 隐藏弹出框
     */
    public void hidePopupWindow() {
        LogUtil.d(TAG, "hide " + isShown + ", " + mView);
        if (isShown && null != mView) {
            LogUtil.d(TAG, "hidePopupWindow");
            mView.setVisibility(View.GONE);
            isShown = false;
        }
    }

    //非系统销毁通话界面时调用。
    public void destroy() {
        if (!isFirstAdd) {
            mWindowManager.removeView(mView);
            isFirstAdd = true;
        }
        isShown = false;
        defaultString = "";
    }

    public static void refreshDefaultString(int callType) {
//        defaultString = ResourceUtil.getIntance(mAppContext).getString(R.string.title_float_window);
        LogUtil.d(TAG, "after refreshDefaultString : " + defaultString);
    }

    private void setUpView() {
        LogUtil.d(TAG, "setUp view");
        mView = LayoutInflater.from(mAppContext).inflate(R.layout.layout_float_bar, null);
        mTextView = (TextView) mView.findViewById(R.id.tv_title);
        mTextView.setText(defaultString);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //doSomething
            }
        });
    }

    private void initParams() {
        params = new WindowManager.LayoutParams();
        // 类型,如果只是设置成TYPE_SYSTEM_ALERT并不能覆盖status bar
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        // 设置flag
        int flags = FLAG_LAYOUT_IN_SCREEN | FLAG_NOT_FOCUSABLE;
        params.flags = flags;
        // 不设置这个弹出框的透明遮罩显示为黑色
        params.format = PixelFormat.TRANSLUCENT;
        params.width = WRAP_CONTENT;
        params.height = WRAP_CONTENT;

        params.gravity = Gravity.TOP;
    }
}
```
