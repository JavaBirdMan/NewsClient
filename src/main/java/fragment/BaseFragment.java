package fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.sunpeng.newsclient.R;
import com.sunpeng.newsclient.activity.HomeActivity;


public abstract class BaseFragment extends Fragment {


    public Context mContext;
    public  SlidingMenu mSlidingMenu;
    private View view;

    //初始化工作
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mSlidingMenu = ((HomeActivity) getActivity()).getSlidingMenu();
    }

    //初始化view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = initView(inflater);
        return view;
    }

    public View getRootView(){
        return view;
    }

    public abstract View initView(LayoutInflater inflater);

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void initData();
}
