package com.tpout.uistruct.tabstruct.fragtabhost;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;


import com.tpout.uistruct.R;

import static com.tpout.uistruct.tabstruct.fragtabhost.FirstFrag.TAG_FIRST;
import static com.tpout.uistruct.tabstruct.fragtabhost.SecondFrag.TAG_SECOND;


/**
 * Created by shengjieli on 17-10-28.<br>
 * Email address: shengjieli@ecarx.com.cn<br>
 * 这种要创建四个页面也是醉了。以后可以封装一个adapter
 */

public class FragTabHostAct extends AppCompatActivity implements TabHost.OnTabChangeListener {

    private FragmentTabHost mTabHost;
    private TabWidget mTabWidget;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_fragtabhost);
        initTabHost();

    }

    private void initTabHost() {
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabWidget = (TabWidget) findViewById(android.R.id.tabs);

        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        ViewGroup indicator_first = (ViewGroup) getLayoutInflater().inflate(R.layout.tab_main_indicator, mTabWidget, false);
        ViewGroup indicator_second = (ViewGroup) getLayoutInflater().inflate(R.layout.tab_main_indicator, mTabWidget, false);

        mTabHost.addTab(mTabHost.newTabSpec(TAG_FIRST).setIndicator(indicator_first), FirstFrag.class, null);
        mTabHost.addTab(mTabHost.newTabSpec(TAG_SECOND).setIndicator(indicator_second), SecondFrag.class, null);

        mTabHost.setOnTabChangedListener(this);

        ImageView ivConnect = (ImageView) indicator_first.findViewById(R.id.tab_icon);
//        Drawable ivConnectDrawable = AppCompatResources.getDrawable(this, R.drawable.arrow_title);
////        setIconTintList(ivConnectDrawable);
//        ivConnect.setImageDrawable(ivConnectDrawable);
        TextView tvConnect = (TextView) indicator_first.findViewById(R.id.tab_name);
        tvConnect.setText("first");

        ImageView ivDisplay = (ImageView) indicator_second.findViewById(R.id.tab_icon);
//        Drawable ivDisplayDrawable = AppCompatResources.getDrawable(this, R.drawable.arrow_title);
//        setIconTintList(ivDisplayDrawable);
//        ivDisplay.setImageDrawable(ivDisplayDrawable);
        TextView tvDisplay = (TextView) indicator_second.findViewById(R.id.tab_name);
        tvDisplay.setText("second");

        mTabHost.setCurrentTab(0);
    }

    @Override
    public void onTabChanged(String tabId) {

    }
}
