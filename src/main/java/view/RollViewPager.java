package view;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.sunpeng.newsclient.R;

public class RollViewPager extends ViewPager implements OnPageChangeListener {
	public Context mContext;
	private List<View> mDotLists;

	public RollViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}



	public RollViewPager(Context context, List<View> mDotLists) {
		super(context);
		this.mContext =context;
		this.mDotLists = mDotLists;
		mBitmapUtils = new BitmapUtils(mContext);
		mBitmapUtils.configDefaultBitmapConfig(Config.ARGB_4444);
		mTask = new Task();
	}

	private boolean hasAdapter = false;
	private int oldPosition = 0;

	public void start() {
		if (!hasAdapter) {
			hasAdapter = true;
			RollViewPagerAdapter adapter = new RollViewPagerAdapter();
			RollViewPager.this.setAdapter(adapter);
			RollViewPager.this.setOnPageChangeListener(this);
		}
		handler.postDelayed(mTask, 2000);
	}

	// 当前的位置
	private int mCurrentItem = 0;

	private class Task implements Runnable {

		@Override
		public void run() {
			mCurrentItem = (mCurrentItem + 1) % mImageLists.size();
			handler.obtainMessage().sendToTarget();
		}

	}

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			RollViewPager.this.setCurrentItem(mCurrentItem, false);
			start();
		};
	};

	private class RollViewPagerAdapter extends PagerAdapter {

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			View view = View.inflate(mContext,R.layout.viewpager_item, null);
			ImageView image = (ImageView) view.findViewById(R.id.image);
			mBitmapUtils.display(image, mImageLists.get(position));
			container.addView(view);
			return view;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mImageLists.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return arg0 == arg1;
		}

	}

	private TextView mTopNewsTitle;
	private List<String> mTitleLists;

	/**
	 * 设置轮播图上面的标题
	 * 
	 * @param mTopNewsTitle
	 * @param mTitleLists
	 */
	public void setTextTitle(TextView mTopNewsTitle, List<String> mTitleLists) {
		if (null != mTopNewsTitle && null != mTitleLists
				&& mTitleLists.size() > 0) {
			this.mTopNewsTitle = mTopNewsTitle;
			this.mTitleLists = mTitleLists;
			mTopNewsTitle.setText(mTitleLists.get(0));
		}

	}

	private List<String> mImageLists;
	private BitmapUtils mBitmapUtils;
	private Task mTask;

	/**
	 * 设置背景图片
	 * 
	 * @param mImageLists
	 */
	public void setImageRes(List<String> mImageLists) {
		this.mImageLists = mImageLists;

	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		mCurrentItem = arg0;

		if (null != mTitleLists && mTitleLists.size() > 0
				&& null != mTopNewsTitle) {
			mTopNewsTitle.setText(mTitleLists.get(arg0));
		}

		if (null != mDotLists && mDotLists.size() > 0) {
			mDotLists.get(arg0).setBackgroundResource(R.drawable.dot_focus);
			mDotLists.get(oldPosition).setBackgroundResource(
					R.drawable.dot_normal);
		}
		oldPosition = arg0;

	}

	@Override
	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {

	}

}
