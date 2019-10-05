package com.tpout.basicusage.searchview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tpout.basicusage.R;

/**
 * Created by TpOut on 19-3-13.<br>
 * Email address: 416756910@qq.com<br>
 */
public class SearchDemoAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_search_demo);
        findViewById(R.id.main_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSearchRequested();
            }
        });
        //设置type-to-search
        setDefaultKeyMode(DEFAULT_KEYS_SEARCH_LOCAL);
    }

    @Override
    public boolean onSearchRequested() {
        pauseSomeStuff();
        Bundle appData = new Bundle();
//        appData.putBoolean(SearchableActivity.JARGON, true);
        //不要在onSearchRequested()外部调用此方法
        startSearch(null, false, appData, false);
        //表示已经处理startSearch，否则调用super即可
        return true;
    }

    //在此处处理相关的操作
    private void pauseSomeStuff(){

    }
}
