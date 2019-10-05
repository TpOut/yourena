
package com.yourena.tpout.settings.customview;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yourena.tpout.librarys.util.LogUtil;
import com.yourena.tpout.librarys.util.SPUtil;
import com.yourena.tpout.settings.R;

import static com.yourena.tpout.librarys.util.StringUtil.parseNull;

/**
 * Created by shengjieli on 17-10-27.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 * 功能类根据对应的key在SharedPreference中存储value即可
 */

public class SingleNormalView extends LinearLayout implements SharedPreferences.OnSharedPreferenceChangeListener {

    private String TAG = SingleNormalView.class.getSimpleName();

    //点击之后弹出的fragment
    private String mLinkFragName;
    private String mKey;
    private String mDefaultValue;

    private TextView mTvName;
    private TextView mTvValue;
    private OnLinkViewClickListener mOnLinkViewClickListener;

    public SingleNormalView(Context context) {
        this(context, null);
    }

    public SingleNormalView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SingleNormalView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(VERTICAL);
        inflate(context, R.layout.item_normal, this);

        mTvName = (TextView) findViewById(R.id.item_name);
        mTvValue = (TextView) findViewById(R.id.item_value);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.SingleNormalView,
                0, 0);

        try {
            setItemName(a.getString(R.styleable.SingleNormalView_nv_name));
            mLinkFragName = a.getString(R.styleable.SingleNormalView_nv_linkFrag);
            mKey = a.getString(R.styleable.SingleNormalView_nv_key);
            mDefaultValue = parseNull(a.getString(R.styleable.SingleNormalView_nv_default_value));

            setItemValue();
            // TODO: 17-10-29 以后会考虑增加value的有效性判断，比如开关只有两种或者三种状态。。
            SPUtil.addListener(this);

        } finally

        {
            a.recycle();
        }

        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnLinkViewClickListener != null) {
                    mOnLinkViewClickListener.onClick();
                }
            }
        });
    }

    // TODO: 17-10-29 考虑添加判断，在invisible的时候去掉监听 ，visible的时候重新获取和添加监听
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        lg("---onSharedPreferenceChanged---key : " + key + ": " + mKey);
        if (key.equals(mKey)) {
            setItemValue();
        }
    }

    private void setItemName(String str) {
        setTvText(mTvName, str);
    }

    private void setItemValue() {
        setTvText(mTvValue, SPUtil.getString(mKey, mDefaultValue));
    }

    private void setTvText(TextView tv, String str) {
        tv.setText(str);
    }

    public void setOnLinkViewClickListener(OnLinkViewClickListener onLinkViewClickListener) {
        mOnLinkViewClickListener = onLinkViewClickListener;
    }

    public String getLinkFragName() {
        return mLinkFragName;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        lg("---onAttachedToWindow---");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        lg("---onDetachedFromWindow---");
    }

    private void lg(String str) {
        if (!isInEditMode()) {
            LogUtil.d(TAG, str);
        }
    }
}
