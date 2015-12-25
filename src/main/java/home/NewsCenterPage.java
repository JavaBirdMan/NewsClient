package home;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.sunpeng.newsclient.R;
import com.sunpeng.newsclient.activity.HomeActivity;

import java.util.ArrayList;
import java.util.List;

import bean.NewCenter;
import menu.NewPage;
import menu.PicPage;
import menu.TopicPage;
import utils.HMAPI;
import utils.SharePrefencesUtils;

/**
 * Created by FBI on 2015/12/21.
 */
public class NewsCenterPage extends BasePage{

    public NewsCenterPage(Context context) {

        super(context);
    }
    @ViewInject(R.id.news_center_fl)
    private FrameLayout news_center_fl;

    @Override
    public View initView() {
      /*  TextView textView = new TextView(mContext);
        textView.setText("NewsCenterPage");
        textView.setTextColor(Color.BLACK);*/
        View view = View.inflate(mContext, R.layout.news_center_frame, null);
        ViewUtils.inject(this, view);
        initTitleBar(view);
        return view;
    }

    private void getNewData(){
        HttpUtils httpUtils = new HttpUtils();
        httpUtils.send(HttpRequest.HttpMethod.GET, HMAPI.NEW_CENTER, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                ProgressData(responseInfo.result);
                SharePrefencesUtils.saveString(mContext,HMAPI.NEW_CENTER,responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(mContext, "请检查网路链接是否正常", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void initData() {
        //获取到缓存的数据
        String result = SharePrefencesUtils.getString(mContext, HMAPI.NEW_CENTER, "");
        if (!TextUtils.isEmpty(result)){
            ProgressData(result);
        }
        getNewData();


    }
    //封装所有title的数据
    private List<String> mMenuTitles = new ArrayList<String>();
    //封装侧滑菜单里面的数据
    private List<BasePage> mMenuLists = new ArrayList<BasePage>();

    private void ProgressData(String result) {
        Gson gson = new Gson();
        NewCenter newCenter = gson.fromJson(result, NewCenter.class);

        mMenuTitles.clear();
        isLoading=true;
//        NewCenter newCenter = GsonTools.changeGsonToBean(result, NewCenter.class);
        for (NewCenter.NewItem item:newCenter.data){
            //添加所有的title
            mMenuTitles.add(item.title);
//            System.out.println("newcenterpage...."+item.title);
        }
        ((HomeActivity)mContext).getMenuFragment2().initMenuCenterMenu(mMenuTitles);
        mMenuLists.clear();
        // 添加新闻
        mMenuLists.add(new NewPage(mContext,newCenter.data.get(0)));
//        System.out.println("newcenterPage---"+newCenter.data.size());
        //添加专题
        mMenuLists.add(new TopicPage(mContext));
        //添加图片
        mMenuLists.add(new PicPage(mContext));
        //添加互动
        mMenuLists.add(new PicPage(mContext));
        switchFragment(0);
    }
    private int index=0;
    public void switchFragment(int position){
        BasePage basePage = mMenuLists.get(position);
        switch (position){
            case 0:
            txt_title.setText("NEWS");
                txt_title.setTextColor(Color.WHITE);

                break;
            case 1:
                txt_title.setText("TOPICS");
                txt_title.setTextColor(Color.WHITE);

                break;
            case 2:
                txt_title.setText("PICTURES");
                txt_title.setTextColor(Color.WHITE);

                break;
            case 3:
                txt_title.setText("INTERACTION");
                break;
        }
        news_center_fl.removeAllViews();
        news_center_fl.addView(basePage.getRootView());
        basePage.initData();
        this.index = position;
    }


}
