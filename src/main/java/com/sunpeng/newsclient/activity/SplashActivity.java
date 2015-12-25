package com.sunpeng.newsclient.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.sunpeng.newsclient.R;
import utils.SharePrefencesUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isFirstEnter = SharePrefencesUtils.getBoolean(SplashActivity.this, "isFirstEnter", false);
                if (isFirstEnter){
                    Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    SharePrefencesUtils.saveBoolean(SplashActivity.this,"isFirstEnter",true);
                    Intent intent = new Intent(SplashActivity.this, GuideActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        },3000);
    }
}
