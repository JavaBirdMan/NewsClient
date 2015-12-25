package com.sunpeng.newsclient.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;


import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.sunpeng.newsclient.R;
import fragment.HomeFragment;
import fragment.MenuFragment2;
import fragment.NewsFragment;

public class HomeActivity extends SlidingFragmentActivity {

    private SlidingMenu mSlidingMenu;
    private MenuFragment2 mMenuFragment;
    private HomeFragment mHomeFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBehindContentView(R.layout.activity_menu);
        setContentView(R.layout.activity_home);
        mSlidingMenu = getSlidingMenu();
        // 设置滑动菜单的模式
        mSlidingMenu.setMode(SlidingMenu.LEFT);
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        mSlidingMenu.setBehindOffsetRes(R.dimen.sm_width);
        mSlidingMenu.setShadowDrawable(R.drawable.shadow);
        mSlidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        mMenuFragment = new MenuFragment2();
        getSupportFragmentManager().beginTransaction().replace(R.id.menu, mMenuFragment,"MENU").commit();

        Fragment mNewsFragment = new NewsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content,mNewsFragment).commit();

        mHomeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content, mHomeFragment,"HOME").commit();
    }

    //返回菜单
    public MenuFragment2 getMenuFragment2(){
        mMenuFragment = (MenuFragment2) getSupportFragmentManager().findFragmentByTag("MENU");
        return mMenuFragment;
    }

    public void changeFragment(Fragment mFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content,mFragment).commit();
//        Toggle the SlidingMenu. If it is open, it will be closed, and vice versa.
        mSlidingMenu.toggle();
    }

    public HomeFragment getHomeFragment() {
        mHomeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag("HOME");
        return mHomeFragment;
    }
}
