package com.sunpeng.newsclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.sunpeng.newsclient.R;
import utils.DensityUtil;

import view.ViewPagerTransFormer;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private ViewPagerTransFormer mViewPager;
    private int[] ICONS = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
    private List<ImageView> mImageViewList;
    private LinearLayout.LayoutParams params;
    private LinearLayout ll_contanier;
    private Button btn_start;
    private ImageView iv_point;
    //两点之间的距离
    private int mWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        mViewPager = (ViewPagerTransFormer) findViewById(R.id.view_pager);
        btn_start = (Button) findViewById(R.id.btn_start);
        mViewPager.setOnPageChangeListener(this);
        btn_start.setOnClickListener(this);
        ll_contanier = (LinearLayout) findViewById(R.id.ll_contanier);
        iv_point = (ImageView) findViewById(R.id.iv_point);

        initData();
        mViewPager.setAdapter(new MyViewPagerAdapter());
//        mViewPager.setPageTransformer(true, new DepthPageTransformer());
//        mViewPager.setPageTransformer(true,new ZoomOutPagerTransFormer());

//        mViewPager.setPageTransformer(true,new RotatePagerTransFormer());
        mViewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                mWidth = ll_contanier.getChildAt(1).getLeft() - ll_contanier.getChildAt(0).getLeft();
            }
        }, 20);


    }

    private void initData() {
        mImageViewList = new ArrayList<ImageView>();
        for (int i = 0; i < ICONS.length; i++) {
            ImageView mImageView = new ImageView(GuideActivity.this);
            mImageView.setBackgroundResource(ICONS[i]);
            mImageViewList.add(mImageView);
            View view = new View(this);

            params = new LinearLayout.LayoutParams(DensityUtil.dip2px(getApplicationContext(), 6),
                    DensityUtil.dip2px(getApplicationContext(), 6));

            if (i != 0) {
                params.leftMargin = DensityUtil.dip2px(getApplicationContext(), 6);
            }
            view.setLayoutParams(params);
            view.setBackgroundResource(R.drawable.dot_normal);
            ll_contanier.addView(view);
        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // TODO: 2015/12/20
        float newPosition = mWidth*(position+positionOffset);
        iv_point.setTranslationX(newPosition);

    }

    @Override
    public void onPageSelected(int position) {
        if (position == mImageViewList.size() - 1) {
            btn_start.setVisibility(View.VISIBLE);
        } else {
            btn_start.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private class MyViewPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mImageViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mImageViewList.get(position));
            mViewPager.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = mImageViewList.get(position);
            container.addView(view);
            mViewPager.addChildView(view, position);
            return view;
        }

    }


}
