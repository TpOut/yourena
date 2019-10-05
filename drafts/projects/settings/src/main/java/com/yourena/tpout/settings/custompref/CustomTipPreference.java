package com.yourena.tpout.settings.custompref;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yourena.tpout.librarys.util.LogUtil;
import com.yourena.tpout.settings.MyApplication;
import com.yourena.tpout.settings.R;

/**
 * Created by shengjieli on 17-7-17.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class CustomTipPreference extends Preference {

    private String TAG = CustomTipPreference.class.getSimpleName();

    //这个不是要在xml里写吗？？？
    private static String DEFAULT_VALUE = MyApplication.sApp.getString(R.string.pref_custom_tip_summary);

    private String mCurrentValue;

    @Override
    protected View onCreateView(ViewGroup parent) {
        super.onCreateView(parent);
        LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return li.inflate(R.layout.pref_tip, parent, false);
    }

    public CustomTipPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public CustomTipPreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTipPreference(Context context) {
        this(context, null);
    }


    //系统添加preference到屏幕时调用
    //如果没有设置过值，会提供一个默认值

    @Override
    protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
        LogUtil.d(TAG, "onSetInitialValue");
        if (restorePersistedValue) {
            // Restore existing state
            mCurrentValue = this.getPersistedString(DEFAULT_VALUE);
        } else {
            // Set default state from the XML attribute
            mCurrentValue = (String) defaultValue;
            persistString(mCurrentValue);
        }
    }

    /**
     * @return 提供給 #onSetInitialValue
     */
    @Override
    protected Object onGetDefaultValue(TypedArray a, int index) {
//        return super.onGetDefaultValue(a, index);
        return a.getString(index);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        final Parcelable superState = super.onSaveInstanceState();
        // Check whether this Preference is persistent (continually saved)
        if (isPersistent()) {
            // No need to save instance state since it's persistent,
            // use superclass state
            return superState;
        }

        // Create instance of custom BaseSavedState
        final SavedState myState = new SavedState(superState);
        // Set the state's value with the class member that holds current
        // setting value
        myState.value = mCurrentValue;
        return myState;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        // Check whether we saved the state in onSaveInstanceState
        if (state == null || !state.getClass().equals(SavedState.class)) {
            // Didn't save the state, so call superclass
            super.onRestoreInstanceState(state);
            return;
        }

        // Cast state to custom BaseSavedState and pass to superclass
        SavedState myState = (SavedState) state;
        super.onRestoreInstanceState(myState.getSuperState());

        // Set this Preference's widget to reflect the restored state
        setValue(myState.value);

    }

    private void setValue(String value) {
        mCurrentValue = value;
        //doSomeThingElse
    }

    private static class SavedState extends BaseSavedState {
        // Member that holds the setting's value
        // Change this data type to match the type saved by your Preference
        String value;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        public SavedState(Parcel source) {
            super(source);
            // Get the current preference's value
            value = source.readString();  // Change this to read the appropriate data type
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            // Write the preference's value
            dest.writeString(value);  // Change this to write the appropriate data type
        }

        // Standard creator object using an instance of this class
        public static final Parcelable.Creator<SavedState> CREATOR =
                new Parcelable.Creator<SavedState>() {

                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }
}
