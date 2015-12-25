package view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by FBI on 2015/12/19.
 */
public class ViewPagerTransFormer extends ViewPager {

    private Map<Integer, ImageView> viewMaps = new HashMap<>();
    private static float MIN_SCALE = 0.85f;
    private static float MIN_SCALE1 = 1.0f;

    public ViewPagerTransFormer(Context context) {
        super(context);
    }

    private View leftViewPager = null;
    private View rightViewPager = null;

    public ViewPagerTransFormer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public void addChildView(ImageView view, int position) {
        viewMaps.put(position, view);
    }

    /**
     * @param position     表示位置 0-1
     * @param offset       表示比例 0-1     -0
     * @param offsetPixels 表示像素 0-480        -0
     */
    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        super.onPageScrolled(position, offset, offsetPixels);
//        System.out.println("yjj    position:  "+position);
//        System.out.println("yjj    offset:  "+offset);
//        System.out.println("yjj    offsetPixels:  "+offsetPixels);
        leftViewPager = viewMaps.get(position);
        rightViewPager = viewMaps.get(position + 1);
//        System.out.println("position>>>"+position);
        startAnimation(leftViewPager, rightViewPager, position, offset, offsetPixels);
    }

    private void startAnimation(View leftViewPager, View rightViewPager, int position, float offset, int offsetPixels) {

        if (rightViewPager != null) {
            float pageWidth = rightViewPager.getWidth();
//            float pageWidth = -getWidth() + offsetPixels;

//            float pageWidth = rightViewPager.getWidth();
//            rightViewPager.setTranslationX(pageWidth);
//            ScaleAnimation scaleAnimation = new ScaleAnimation(0,1,0,1);
//            RotateAnimation rotateAnimation = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,getWidth()/2,
//                    Animation.RELATIVE_TO_SELF,getHeight()/2);
//            AnimationSet animationSet = new AnimationSet(false);
//            animationSet.addAnimation(scaleAnimation);
//            animationSet.addAnimation(rotateAnimation);
//            rightViewPager.startAnimation(animationSet);

//            float scaleX = (1 - MIN_SCALE) * offset + MIN_SCALE;
//            rightViewPager.setScaleX(scaleX);
//            rightViewPager.setScaleY(scaleX);


//            * @param offset       表示比例 0-1     -0
//            * @param offsetPixels 表示像素 0-480        -0

//            System.out.println("==============================");
            rightViewPager.setAlpha(offset);    //0 ~ 1

            // Counteract the default slide transition
            rightViewPager.setTranslationX(pageWidth * -(1-offset));

            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (offset);  // 0 ~ 1
            rightViewPager.setScaleX(scaleFactor);
            rightViewPager.setScaleY(scaleFactor);



        }
        if (leftViewPager != null) {
            leftViewPager.bringToFront();
//            leftViewPager.setRotation(MIN_SCALE1*offset);
//            leftViewPager.setAnimation(new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,leftViewPager.getMeasuredWidth()/2,
//
//                            Animation.RELATIVE_TO_SELF,leftViewPager.getMeasuredHeight()/2 )
//                    );
//            leftViewPager.setPivotX(leftViewPager.getMeasuredWidth()/2);
//            leftViewPager.setPivotY(leftViewPager.getMeasuredHeight()/2);
/*
            ScaleAnimation scaleAnimation = new ScaleAnimation(0,1,0,1);
            RotateAnimation rotateAnimation = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,getWidth()/2,
                    Animation.RELATIVE_TO_SELF,getHeight()/2);
            AnimationSet animationSet = new AnimationSet(false);
            animationSet.addAnimation(scaleAnimation);
            animationSet.addAnimation(rotateAnimation);
            leftViewPager.startAnimation(animationSet);*/

        }
    }




}
