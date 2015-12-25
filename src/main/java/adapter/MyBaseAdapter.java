package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by FBI on 2015/12/22.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {

    public List<T> mData;
    public Context mContext;

    public MyBaseAdapter(List<T> mData,Context mContext){
        super();
        this.mData=mData;
        this.mContext=mContext;
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


}
