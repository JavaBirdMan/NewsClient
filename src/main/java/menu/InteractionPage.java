package menu;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import home.BasePage;

/**
 * Created by FBI on 2015/12/23.
 */
public class InteractionPage extends BasePage{
    public InteractionPage(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(mContext);
        textView.setText("INTERACTION");
        textView.setTextColor(Color.GREEN);
        return null;
    }

    @Override
    public void initData() {

    }
}
