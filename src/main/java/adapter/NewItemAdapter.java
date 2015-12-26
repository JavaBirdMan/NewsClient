package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.sunpeng.newsclient.R;

import java.util.List;

import bean.NewItemData;

/**
 * Created by FBI on 2015/12/26.
 */
public class NewItemAdapter extends MyBaseAdapter<NewItemData.News> {

    private final BitmapUtils mBitmapUtils;

    public NewItemAdapter(List<NewItemData.News> mData, Context mContext) {
        super(mData, mContext);
        mBitmapUtils = new BitmapUtils(mContext);
        mBitmapUtils.configDefaultBitmapConfig(Bitmap.Config.ARGB_4444);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){

            convertView = View.inflate(mContext, R.layout.layout_news_item, null);
        }
        TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView tv_pub_date = (TextView) convertView.findViewById(R.id.tv_pub_date);
        ImageView iv_img = (ImageView) convertView.findViewById(R.id.iv_img);
        tv_title.setText(mData.get(position).title);
        tv_pub_date.setText(mData.get(position).pubdate);
        mBitmapUtils.display(iv_img,mData.get(position).listimage);
        return convertView;
    }
}
