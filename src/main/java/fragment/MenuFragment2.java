package fragment;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.sunpeng.newsclient.R;
import com.sunpeng.newsclient.activity.HomeActivity;

import java.util.ArrayList;
import java.util.List;

import adapter.MyBaseAdapter;


public class MenuFragment2 extends BaseFragment {

    public static final int NEWCENTER =1;
    public static final int SERVICE =2;
    public static final int AFFAIRS =3;



    //新闻中心
    @ViewInject(R.id.lv_menu_news_center)
    private ListView lv_menu_news_center;
    //智慧服务
    @ViewInject(R.id.lv_menu_smart_service)
    private ListView lv_menu_smart_service;
    //政务指南
    @ViewInject(R.id.lv_menu_govaffairs)
    private ListView lv_menu_govaffairs;

    @Override
    public View initView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.layout_left_menu, null);
        ViewUtils.inject(this, view);
        return view;
    }



    //所有菜单的数据

    private List<String> mMenuLists  = new ArrayList<String>();

    private MenuAdapter mMenuAdapter;
    private MenuAdapter mServiceAdapter;
    private MenuAdapter mAffairsAdapter;
    public void initMenuCenterMenu(List<String> mTitleLists){
        mMenuLists.clear();
//        this.mMenuLists=mTitleLists;
        mMenuLists.addAll(mTitleLists);
       /* lv_menu_news_center.setAdapter(mMenuAdapter);
        lv_menu_govaffairs.setAdapter(mAffairsAdapter);
        lv_menu_smart_service.setAdapter(mServiceAdapter);*/


    }

    @Override
    protected void initData() {
        //新闻中心的点击事件
        lv_menu_news_center.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mMenuAdapter.setOnclickPosition(position);
                mSlidingMenu.toggle();
                ((HomeActivity)mContext).getHomeFragment().getmNewsCenterPage().switchFragment(position);

            }
        });

    }

 public void getType(int type) {
        lv_menu_news_center.setVisibility(View.GONE);
        lv_menu_smart_service.setVisibility(View.GONE);
        lv_menu_govaffairs.setVisibility(View.GONE);
        switch (type){
            case 1:
                lv_menu_news_center.setVisibility(View.VISIBLE);

                if (mMenuAdapter==null){
                    mMenuAdapter=new MenuAdapter(mMenuLists,mContext);
                    lv_menu_news_center.setAdapter(mMenuAdapter);
                }else{
                    mMenuAdapter.notifyDataSetChanged();
                }
                break;
            case 2:
                lv_menu_smart_service.setVisibility(View.VISIBLE);
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add("教育");
                arrayList.add("交通");
                arrayList.add("医疗");
                arrayList.add("生活");
                arrayList.add("住房");
                arrayList.add("旅游");
                arrayList.add("社保");
                if (mServiceAdapter==null){
                    mServiceAdapter= new MenuAdapter(arrayList,mContext);
                    lv_menu_smart_service.setAdapter(mServiceAdapter);
                }else{
                    mServiceAdapter.notifyDataSetChanged();
                }
                break;

            case 3:
                lv_menu_govaffairs.setVisibility(View.VISIBLE);
                ArrayList<String> arrayList2 = new ArrayList<>();
                arrayList2.add("公安行政");
                arrayList2.add("交通管理");
                arrayList2.add("社会保险");
                arrayList2.add("发展改革");
                if (mAffairsAdapter==null){
                    mAffairsAdapter= new MenuAdapter(arrayList2,mContext);
                    lv_menu_govaffairs.setAdapter(mAffairsAdapter);
                }else{
                    mAffairsAdapter.notifyDataSetChanged();
                }
                break;
        }
    }


    public class MenuAdapter extends MyBaseAdapter<String> {

        public MenuAdapter(List mData, Context mContext) {
            super(mData, mContext);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView =View.inflate(mContext, R.layout.layout_item_menu, null);
            ImageView iv_menu_item = (ImageView) convertView.findViewById(R.id.iv_menu_item);
            TextView tv_menu_item = (TextView) convertView.findViewById(R.id.tv_menu_item);
            String title = mData.get(position);
            tv_menu_item.setText(title);
            if (mClickPosition==position){
                iv_menu_item.setBackgroundResource(R.drawable.menu_arr_select);
                tv_menu_item.setTextColor(Color.RED);
                convertView.setBackgroundColor(R.drawable.menu_item_bg_select);
            }else{
                iv_menu_item.setBackgroundResource(R.drawable.menu_arr_normal);
                tv_menu_item.setTextColor(Color.WHITE);
                convertView.setBackgroundResource(R.drawable.transparent);
            }
            return convertView;
        }
        private int mClickPosition;
        public void setOnclickPosition(int position) {
            this.mClickPosition=position;
            notifyDataSetChanged();
//            notifyDataSetInvalidated();
        }
    }

/*
    private int mMenuType;
    public void setMenuType(int type){
        this.mMenuType=type;
        //选择不同的菜单类型
        switchMenuType(mMenuType);
    }*/

   /* private void switchMenuType(int mMenuType) {
        lv_menu_news_center.setVisibility(View.GONE);
        lv_menu_smart_service.setVisibility(View.GONE);
        lv_menu_govaffairs.setVisibility(View.GONE);

        switch (mMenuType){
            case 1:
                lv_menu_news_center.setVisibility(View.VISIBLE);

                if (mMenuAdapter==null){
                    mMenuAdapter=new MenuAdapter(mMenuLists,mContext);
                    lv_menu_news_center.setAdapter(mMenuAdapter);
                }else{
                    mMenuAdapter.notifyDataSetChanged();
                }
                break;
            case 2:
                lv_menu_smart_service.setVisibility(View.VISIBLE);
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add("1");
                arrayList.add("1");

                if (mServiceAdapter==null){
                    mServiceAdapter= new MenuAdapter(arrayList,mContext);
                    lv_menu_smart_service.setAdapter(mServiceAdapter);
                }else{
                    mServiceAdapter.notifyDataSetChanged();
                }
                break;

            case 3:
                lv_menu_govaffairs.setVisibility(View.VISIBLE);
                ArrayList<String> arrayList2 = new ArrayList<>();
                arrayList2.add("2");
                arrayList2.add("2");

                if (mAffairsAdapter==null){
                    mAffairsAdapter= new MenuAdapter(arrayList2,mContext);
                    lv_menu_govaffairs.setAdapter(mAffairsAdapter);
                }else{
                    mAffairsAdapter.notifyDataSetChanged();
                }
                break;
        }
    }*/
   /* public class MenuAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mMenuLists==null?0:mMenuLists.size();
        }

        @Override
        public Object getItem(int position) {
            return mMenuLists.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView =View.inflate(mContext, R.layout.layout_item_menu, null);
            ImageView iv_menu_item = (ImageView) convertView.findViewById(R.id.iv_menu_item);
            TextView tv_menu_item = (TextView) convertView.findViewById(R.id.tv_menu_item);
            String title = mMenuLists.get(position);
            tv_menu_item.setText(title);
            if (mClickPosition==position){
                iv_menu_item.setBackgroundResource(R.drawable.menu_arr_select);
                tv_menu_item.setTextColor(Color.RED);
                convertView.setBackgroundColor(R.drawable.menu_item_bg_select);
            }else{
                iv_menu_item.setBackgroundResource(R.drawable.menu_arr_normal);
                tv_menu_item.setTextColor(Color.WHITE);
                convertView.setBackgroundResource(R.drawable.transparent);
            }
            return convertView;
        }
        private int mClickPosition;
        public void setOnclickPosition(int position) {
            this.mClickPosition=position;
        }
    }*/

    }


