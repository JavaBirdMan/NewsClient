package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import bean.NewItemData;

/**
 * Created by FBI on 2015/12/26.
 */
public class NewItemAdapter extends MyBaseAdapter<NewItemData.News> {
    public NewItemAdapter(List<NewItemData.News> mData, Context mContext) {
        super(mData, mContext);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
