package com.yourena.tpout.settings.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;

import com.yourena.tpout.librarys.util.LogUtil;
import com.yourena.tpout.settings.R;

/**
 * Created by shengjieli on 18-1-5.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 */

public class BaseFrag extends Fragment {

    private String TAG = BaseFrag.class.getSimpleName();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setNormalViewLinkFrag(view);
    }

    //对布局中的所有normalView关联其下级页面
    private void setNormalViewLinkFrag(View rootView) {
        ConstraintLayout layout = (ConstraintLayout) rootView.findViewById(R.id.ll_frag);
        if (null == layout) {
            LogUtil.w(TAG, "if you add a itemNormalView in fragment, pls name the layout id : ll_frag");
        } else {
            int length = layout.getChildCount();
            for (int i = 0; i < length; i++) {
                View child = layout.getChildAt(i);
                if (child instanceof SingleNormalView) {
                    final SingleNormalView itemNormalView = (SingleNormalView) child;
                    itemNormalView.setOnLinkViewClickListener(new OnLinkViewClickListener() {
                        @Override
                        public void onClick() {
                            replaceFragWithName(itemNormalView);
                        }
                    });
                }
            }
        }
    }

    //其实内心是拒绝使用反射的
    private void replaceFragWithName(SingleNormalView itemNormalView) {
        try {
            BaseFrag frag = (BaseFrag) Class.forName(itemNormalView.getLinkFragName())
                    .newInstance();
            LogUtil.d(TAG, "---replaceFragWithName---frag : " + frag);
            replaceFragAndAddToBackStack(frag);
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 此处是单层frag的替换方法，根据需要可以直接改成自己的替换。
     *
     * @param newFragment
     */
    protected void replaceFragAndAddToBackStack(BaseFrag newFragment) {
        LogUtil.d(TAG, " newFragment will be shown is : " + newFragment);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        LogUtil.d(TAG, "the childFragmentManager is : " + fm);
        Fragment currentFragment = fm.findFragmentById(R.id.frag_container);
        LogUtil.d(TAG, "currentFragment will be replaced is : " + currentFragment);
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        if (null != currentFragment) {
            fragmentTransaction.hide(currentFragment);
        }
        fragmentTransaction
                .add(R.id.frag_container, newFragment)
                .addToBackStack(null)
                .commit();
    }

    /**
     * 此处是嵌套frag的替换方法，根据需要可以直接改成自己的替换。
     *
     * @param newFragment
     */
//    protected void replaceFragAndAddToBackStack(BaseFrag newFragment) {
//        LogUtil.d(TAG, " newFragment will be shown is : " + newFragment);
//        FragmentManager childFragmentManager = this.getParentFragment().getChildFragmentManager();
//        LogUtil.d(TAG, "the childFragmentManager is : " + childFragmentManager);
//        Fragment currentFragment = childFragmentManager.findFragmentById(R.id.frag_container);
//        LogUtil.d(TAG, "currentFragment will be replaced is : " + currentFragment);
//        FragmentTransaction fragmentTransaction = this.getParentFragment().getChildFragmentManager().beginTransaction();
//        if (null != currentFragment) {
//            fragmentTransaction.hide(currentFragment);
//        }
//        fragmentTransaction
//                .add(R.id.frag_container, newFragment)
//                .addToBackStack(null)
//                .commit();
//    }

}
