package fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sunpeng.newsclient.R;
import com.sunpeng.newsclient.activity.HomeActivity;

import java.util.ArrayList;
import java.util.List;


public class MenuFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private Context mContext;
    private Fragment mFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化工作
        mContext = getActivity();
    }

    /**
     * 初始化view，相当于Activity的setContentView
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = View.inflate(mContext,R.layout.fragment_item,null);
        mListView = (ListView) view.findViewById(R.id.listview);
        mListView.setOnItemClickListener(this);
        return view;
    }

    //初始化数据
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(mContext,android.R.layout.test_list_item,
                android.R.id.text1, initData());

        mListView.setAdapter(mAdapter);
    }

    /**
     * 初始化数据的操作
     * @return
     */
    private List<String> initData() {
        List<String> mDatas = new ArrayList<String>();
        mDatas.add("News");
        mDatas.add("Entertainment");
        mDatas.add("Sports");
        return mDatas;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                mFragment = new NewsFragment();
                break;
            case 1:
                mFragment=new EntertainmentFragment();
                break;
            case 2:
                mFragment =new SportsFragment();
                break;
        }
        switchFragment(mFragment);
    }

    private void switchFragment(Fragment mFragment) {
        if (getActivity() instanceof HomeActivity){
            ( (HomeActivity)getActivity()).changeFragment(mFragment);
        }
    }
}
