package view;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by FBI on 2015/12/19.
 */
public class RotatePagerTransFormer implements ViewPager.PageTransformer {

    private static final float MIN_VALUE = 25f;

    @Override
    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        if (position<-1){
            view.setRotation(0);
        }else if (position<=0){ //[0,-1)
//            System.out.println("position<=0-------"+position);
            view.setRotation(MIN_VALUE*position);
            view.setPivotX(pageWidth / 2);
            view.setPivotY(view.getMeasuredHeight());
//            System.out.println("高度>>>>>>>>" + view.getHeight());
//            System.out.println("getMe高度>>>>"+view.getMeasuredHeight());

        }else if (position<=1){//(0,1]
//            System.out.println("position<=1-------"+position);

            view.setRotation(MIN_VALUE*position);
            view.setPivotX(pageWidth / 2);
            view.setPivotY(view.getMeasuredHeight());

        }else {
            view.setRotation(0);
        }
    }
}
