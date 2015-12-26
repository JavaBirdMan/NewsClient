package fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnRadioGroupCheckedChange;
import com.sunpeng.newsclient.R;
import com.sunpeng.newsclient.activity.HomeActivity;
import home.BasePage;
import home.FunctionPage;
import home.GovAffirsPage;
import home.NewsCenterPage;
import home.SettingPage;
import home.SmartServicePage;
import view.CustomViewPager;
import view.LazyViewPager;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment implements LazyViewPager.OnPageChangeListener {


    private List<BasePage> mDataList;
    @ViewInject(R.id.main_radio)
    private RadioGroup mRadioGroup;
    @ViewInject(R.id.view_page)
    private CustomViewPager mViewPager;
    private MenuFragment2 mMenuFragment2;
    public int type;


    //初始化工作
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @OnRadioGroupCheckedChange(R.id.main_radio)

    public void onCheckedChanged(RadioGroup group, int checkedId) {
        mMenuFragment2 = (MenuFragment2) ((HomeActivity) getActivity()).getSupportFragmentManager().findFragmentByTag("MENU");
        switch (checkedId) {
            //首页
            case R.id.rb_function:
                mViewPager.setCurrentItem(0);
                        ((HomeActivity)getActivity()).getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
//                 mMenuFragment2 = ((HomeActivity) getActivity()).getMenuFragment2();
                if (null!=mDataList&&mDataList.size()>0){
                    mDataList.get(0).onResume();
                }
                break;
            //新闻中心
            case R.id.rb_news_center:
                mViewPager.setCurrentItem(1);
                ((HomeActivity) getActivity()).getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                if (null!=mDataList&&mDataList.size()>0){
                    mDataList.get(1).onResume();
                }
              /*  if (null!=mMenuFragment2){
                    mMenuFragment2.setMenuType(MenuFragment2.NEWCENTER);
                }*/
                type=1;
                mMenuFragment2.getType(type);
                break;
            //智慧服务
            case R.id.rb_smart_service:
                mViewPager.setCurrentItem(2);
                ((HomeActivity) getActivity()).getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                if (null!=mDataList&&mDataList.size()>0){
                    mDataList.get(2).onResume();
                }


               /* if (null!=mMenuFragment2){
                    mMenuFragment2.setMenuType(MenuFragment2.SERVICE);
                }*/
                type=2;
               mMenuFragment2.getType(type);
                break;
            //政务
            case R.id.rb_gov_affairs:
                mViewPager.setCurrentItem(3);
                ((HomeActivity) getActivity()).getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                if (null!=mDataList&&mDataList.size()>0){
                    mDataList.get(3).onResume();
                }
             /*   if (null!=mMenuFragment2){
                    mMenuFragment2.setMenuType(MenuFragment2.AFFAIRS);
                }*/
                type=3;
              mMenuFragment2.getType(type);

                break;
            //设置
            case R.id.rb_setting:
                mViewPager.setCurrentItem(4);
                ((HomeActivity) getActivity()).getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                if (null!=mDataList&&mDataList.size()>0){
                    mDataList.get(4).onResume();
                }
                break;

        }
    }



    //初始化view

    @Override
    public View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ViewUtils.inject(this,view);
        mRadioGroup.check(R.id.rb_function);

        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


    }

    @Override
    public void onPageSelected(int position) {
        BasePage basePage = mDataList.get(position);
        if (!basePage.isLoading){

            basePage.initData();
        }
    }



    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class HomeAdapter extends PagerAdapter{

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mDataList.get(position).getRootView());
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mDataList.get(position).getRootView());
            return mDataList.get(position).getRootView();
        }

        @Override
        public int getCount() {
            return mDataList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }

    //初始化数据


    @Override
    protected void initData() {

        mDataList = new ArrayList<BasePage>();
        mDataList.add(new FunctionPage(getActivity()));
        mDataList.add(new NewsCenterPage(getActivity()));
        mDataList.add(new SmartServicePage(getActivity()));
        mDataList.add(new GovAffirsPage(getActivity()));
        mDataList.add(new SettingPage(getActivity()));


        HomeAdapter mHomeAdapter = new HomeAdapter();
        mViewPager.setAdapter(mHomeAdapter);
        mViewPager.setOnPageChangeListener(this);

    }

    public NewsCenterPage getmNewsCenterPage() {
        return (NewsCenterPage) mDataList.get(1);
    }
}
