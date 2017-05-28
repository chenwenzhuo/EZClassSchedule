package com.levent_j.timetable.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.levent_j.timetable.base.BaseActivity;
import com.levent_j.timetable.R;
import com.levent_j.timetable.base.BaseFragment;
import com.levent_j.timetable.fragment.AboutFragment;
import com.levent_j.timetable.fragment.ExamListFragment;
import com.levent_j.timetable.fragment.TimeTableFragment;

import butterknife.Bind;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.drawer_layout) DrawerLayout drawer;//侧滑菜单
    //导航菜单，常被置于DrawerLayout中
    @Bind(R.id.nav_view) NavigationView navigationView;

    private Fragment[] fragments;
    private FragmentManager fragmentManager;

    private int currentFragmentPosition=0;

    @Override
    protected void initialize() {
        //为当前Activity设置ToolBar作为ActionBar
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        //为左上角的图标设置点击事件，以唤出侧滑菜单
        drawer.setDrawerListener(toggle);
        toggle.syncState();//将toggle的状态与drawer同步

        //为侧滑菜单的菜单项设置监听器
        navigationView.setNavigationItemSelectedListener(this);
        //设置在侧滑菜单中默认选中的选项
        navigationView.setCheckedItem(R.id.nav_time_table);

        fragmentManager = getSupportFragmentManager();
        fragments = new BaseFragment[3];
        //初始状态：课程表主界面
        Fragment fragment = TimeTableFragment.newInstance();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .commit();
        fragments[0] = fragment;
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    //单击返回键时的回调方法
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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

    //侧滑菜单项的点击回调方法
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int clickFragmentPosition = item.getItemId();

        if (currentFragmentPosition == clickFragmentPosition) {
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

        currentFragmentPosition = clickFragmentPosition;

        Fragment fragment;
        switch (clickFragmentPosition){
            case R.id.nav_time_table:
                if (fragments[0] == null) {
                    fragments[0] = TimeTableFragment.newInstance();
                }
                fragment = fragments[0];
                break;
            case R.id.nav_exam_list:
                if (fragments[1] == null) {
                    fragments[1] = ExamListFragment.newInstance();
                }
                fragment = fragments[1];
                break;
            case R.id.nav_about:
                if (fragments[2] == null) {
                    fragments[2] = AboutFragment.newInstance();
                }
                fragment = fragments[2];
                break;
            default:
                drawer.closeDrawer(GravityCompat.START);
                return true;
        }
        fragmentManager.popBackStack();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
