package home;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.sunpeng.newsclient.R;
import com.sunpeng.newsclient.activity.HomeActivity;

/**
 * Created by FBI on 2015/12/21.
 */
public abstract class BasePage implements View.OnClickListener {

    public Context mContext;
    public View view;
    public boolean isLoading = false;
    public TextView txt_title;
    public SlidingMenu mSlidingMenu;

    public BasePage(Context context){
        this.mContext=context;
       mSlidingMenu = ((HomeActivity) mContext).getSlidingMenu();
        view = initView();
    }

    public View getRootView(){
        return view;
    }


    public abstract View initView();


    public abstract void initData();

    //初始化标题
    public void initTitleBar(View view){
        //左边的按钮
        Button btn_left = (Button) view.findViewById(R.id.btn_left);
        btn_left.setVisibility(View.GONE);
        ImageButton imgbtn_left = (ImageButton) view.findViewById(R.id.imgbtn_left);
        imgbtn_left.setImageResource(R.drawable.img_menu);
        if (null!=imgbtn_left){
            imgbtn_left.setOnClickListener(this);
        }
        //文本标题
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        ImageButton imgbtn_right = (ImageButton) view.findViewById(R.id.imgbtn_right);
        imgbtn_right.setVisibility(View.GONE);

    }

    @Override
    public void onClick(View v) {
        mSlidingMenu.toggle();
    }

    public void onResume(){

    }
}
