package menu;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import home.BasePage;

/**
 * Created by FBI on 2015/12/22.
 */
public class TopicPage extends BasePage {
    public TopicPage(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(mContext);
        textView.setText("TOPICPAGE");
        textView.setTextColor(Color.GREEN);
        return textView;
    }

    @Override
    public void initData() {

    }
}
