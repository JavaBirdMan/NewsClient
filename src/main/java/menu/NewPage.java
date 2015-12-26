package menu;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.sunpeng.newsclient.R;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import bean.NewCenter;
import home.BasePage;

/**
 * Created by FBI on 2015/12/22.
 */
public class NewPage extends BasePage {

    @ViewInject(R.id.pager)
    private ViewPager mViewPager;
    @ViewInject(R.id.indicator)
    private TabPageIndicator mTabPageIndicator;
    private NewCenter.NewItem mNewItem;
    public NewPage(Context context){
        super(context);
    }
    public NewPage(Context context, NewCenter.NewItem newItem) {
        super(context);
        this.mNewItem=newItem;
    }

    @Override
    public View initView() {
//        TextView textView = new TextView(mContext);
//        textView.setText("NEWS");
//        textView.setTextColor(Color.GREEN);
        View view = View.inflate(mContext, R.layout.simple_tabs,null);
        ViewUtils.inject(this, view);
        return view;
    }


    //新闻所有的数据
    private List<NewItemPage> mDatas = new ArrayList<NewItemPage>();
    //当前的位置
    private int mcurrentItem = 0;
    @Override
    public void initData() {
        for (NewCenter.Children children:mNewItem.children){
            mDatas.add(new NewItemPage(mContext, children.url));

        }
        NewPageAdapter newPageAdapter = new NewPageAdapter();
        mViewPager.setAdapter(newPageAdapter);
        mTabPageIndicator.setViewPager(mViewPager);

        // TODO: 2015/12/24 设置监听器
        mTabPageIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mcurrentItem = position;
                //
                onResume();
                NewItemPage newItemPage = mDatas.get(position);
                if (!newItemPage.isLoading) {
                    newItemPage.initData();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置当前的viewpager的位置
        mTabPageIndicator.setCurrentItem(mcurrentItem);
        //调用北京的数据
        mDatas.get(0).initData();
        mDatas.get(0).isLoading=true;

    }

    // TODO: 2015/12/24 设置适配器
    private class NewPageAdapter extends PagerAdapter{

        @Override
        public CharSequence getPageTitle(int position) {
            String title = mNewItem.children.get(position).title;
//            System.out.println("title..."+title);

            return mNewItem.children.get(position).title ;

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mDatas.get(position).getRootView());
            return mDatas.get(position).getRootView();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mDatas.get(position).getRootView());
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }

    @Override
    public void onResume() {
        if (mcurrentItem == 0) {
            //出现侧滑菜单，全屏滑动
            mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        } else {
            //不出现侧滑菜单
            mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        }

        super.onResume();
    }
}
