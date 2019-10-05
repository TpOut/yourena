package com.yourena.tpout.jizhi.act.edit;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import com.yourena.tpout.jizhi.R;
import com.yourena.tpout.jizhi.app.util.LogUtil;

/**
 * Created by 41675 on 2018/2/24.
 */

public class LineDividerEditView extends AppCompatEditText {

    public static final String TAG = LineDividerTextView.class.getSimpleName();

    private Rect mRect;

    private Drawable lineDivider;
    private int lineDividerHeight;

    public LineDividerEditView(Context context) {
        super(context);
    }

    public LineDividerEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public LineDividerEditView(Context context, AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mRect = new Rect();

        try {
            TypedArray array = context.obtainStyledAttributes(attrs, R
                    .styleable
                    .LineDividerTextView, defStyleAttr, 0);
            lineDivider = array.getDrawable(R.styleable
                    .LineDividerTextView_line_divider);
            lineDividerHeight = array.getDimensionPixelSize(R.styleable
                    .LineDividerTextView_line_divider_height, 0);
            array.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int count = getLineCount();

        for (int i = 0; i < count; i++) {
            // last line not draw
            if (i == count - 1) {
                break;
            }
            getLineBounds(i, mRect);

            LogUtil.d(TAG, "---onDraw---" + mRect);

            lineDivider.setBounds(
                    mRect.left,
                    (int) (mRect.bottom - getLineSpacingExtra() / 2 -
                            lineDividerHeight / 2),
                    mRect.right,
                    (int) (mRect.bottom - getLineSpacingExtra() / 2 +
                            lineDividerHeight / 2));
            lineDivider.draw(canvas);
        }

    }
}
