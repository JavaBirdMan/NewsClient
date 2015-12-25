package home;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

/**
 * Created by FBI on 2015/12/21.
 */
public class SettingPage extends BasePage {
    public SettingPage(Context context) {

        super(context);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(mContext);
        textView.setText("SettingPage");
        textView.setTextColor(Color.BLACK);

        return textView;
    }

    @Override
    public void initData() {

    }
}
