package view;

import android.content.Context;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.BitmapUtils;
import com.sunpeng.newsclient.R;

import java.util.List;


public class RollViewPager extends ViewPager  {
	public Context mContext;
	private List<View> mDotLists;
	private int downX;
	private int downY;
	private long downTime;
	private ViewPagerOnTouchListener mViewPagerOnTouchListener;


	//给Viewpager设置一个点击事件
	public interface ViewPagerOnTouchListener{
		public void onViewPagerClickListener();
	}


	public RollViewPager(Context context) {
		super(context);

	}



	public RollViewPager(Context context, List<View> mDotLists,ViewPagerOnTouchListener viewPagerOnTouchListener) {
		super(context);
		this.mContext =context;
		this.mDotLists = mDotLists;
		this.mViewPagerOnTouchListener=viewPagerOnTouchListener;
		mBitmapUtils = new BitmapUtils(context);
		mBitmapUtils.configDefaultBitmapConfig(Config.ARGB_4444);
		mTask = new Task();
		RollViewPager.this.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
					//当手指按下时停止滚动
					case MotionEvent.ACTION_DOWN:
						handler.removeCallbacksAndMessages(null);
						//获取按下的时间
						downTime = System.currentTimeMillis();
						break;
					//当手指抬起时继续滚动
					case MotionEvent.ACTION_UP:
						//获取当前的时间
						long upTime = System.currentTimeMillis();
						//长按
						if ((upTime - downTime) > 500) {

						} else {
							Toast.makeText(mContext, "我被点击了", Toast.LENGTH_SHORT).show();
							mViewPagerOnTouchListener.onViewPagerClickListener();
						}
						start();
						break;
					case MotionEvent.ACTION_CANCEL:
						System.out.println("ACTION_CANCEL");
						break;

				}
				return false;
			}
		});
	}

	//处理viewpager时间分发的bug
	private boolean isMove = false;
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {

		switch (ev.getAction()){
			case MotionEvent.ACTION_DOWN:
				downX = (int) ev.getX();
				downY = (int) ev.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				int currentX = (int) ev.getX();
				int currentY = (int) ev.getY();
				if (Math.abs(currentX- downX)>Math.abs(currentY-downY)){
					isMove=false;
				}else{
					isMove=true;
				}

				break;

		}
		getParent().requestDisallowInterceptTouchEvent(!isMove);
			return super.dispatchTouchEvent(ev);
	}

	private boolean hasAdapter = false;
	private int oldPosition = 0;

	public void start() {
		if (!hasAdapter) {
			hasAdapter = true;
			RollViewPagerAdapter adapter = new RollViewPagerAdapter();
			RollViewPager.this.setAdapter(adapter);
			RollViewPager.this.setOnPageChangeListener(new OnPageChangeListener() {
				@Override
				public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

				}

				@Override
				public void onPageSelected(int position) {

					mCurrentItem = position;

					if (null != mTitleLists && mTitleLists.size() > 0
							&& null != mTopNewsTitle) {
						mTopNewsTitle.setText(mTitleLists.get(position));
					}

					if (null != mDotLists && mDotLists.size() > 0) {
						mDotLists.get(position).setBackgroundResource(R.drawable.dot_focus);
						mDotLists.get(oldPosition).setBackgroundResource(
								R.drawable.dot_normal);
					}
					oldPosition = position;


				}

				@Override
				public void onPageScrollStateChanged(int state) {

				}
			});
		}
		//因为发消息所以点才会动，如果不发消息，点会一直在第一个位置，不会动
		handler.postDelayed(mTask, 2000);
	}

	// 当前的位置
	private int mCurrentItem = 0;

	//让点停止
	public void stop() {
		handler.removeCallbacksAndMessages(null);
	}

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


}
