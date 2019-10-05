package com.yourena.tpout.jizhi.act.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.yourena.tpout.jizhi.R;
import com.yourena.tpout.jizhi.act.edit.EditAct;
import com.yourena.tpout.jizhi.app.BookBean;
import com.yourena.tpout.jizhi.app.base.BaseAct;
import com.yourena.tpout.jizhi.app.util.ResUtil;
import com.yourena.tpout.jizhi.app.util.SpUtil;
import com.yourena.tpout.jizhi.frag.UserFrag;

import org.litepal.crud.DataSupport;

import java.util.List;

public class MainAct extends BaseAct
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String TAG_DIALOG_USER = "TAG_DIALOG_USER";

    private List<BookBean> mList;
    private MainActAdapter mainActAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = DataSupport.findAll(BookBean.class);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(staggeredGridLayoutManager);
        rv.addItemDecoration(new GridSpacingItemDecoration(3, (int) ResUtil.getDimen(R.dimen.space_normal_act_layout), true));
        mainActAdapter = new MainActAdapter(mList);
        rv.setAdapter(mainActAdapter);
        rv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        final SwipeRefreshLayout srl = (SwipeRefreshLayout) findViewById(R.id.srl);
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                List<BookBean> all = DataSupport.findAll(BookBean.class);
                mList.clear();
                mList.addAll(all);
                mainActAdapter.notifyDataSetChanged();
                srl.setRefreshing(false);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id
                .fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SpUtil.getInstance().getString(SpUtil.KEY_USER_NAME)
                        .equals("")) {
                    UserFrag.newInstance("").show(getSupportFragmentManager
                            (), TAG_DIALOG_USER);
                } else {
                    Intent intent = new Intent(MainAct.this, EditAct.class);
                    startActivity(intent);
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R
                .string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id
                .nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LinearLayout drawerHeaderView = (LinearLayout) navigationView
                .getHeaderView(0);
        drawerHeaderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserFrag.newInstance(((UserNameTv) findViewById(R.id
                        .tv_user_name)).getText().toString())
                        .show(getSupportFragmentManager(), TAG_DIALOG_USER);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is
        // present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
