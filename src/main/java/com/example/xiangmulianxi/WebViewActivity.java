package com.example.xiangmulianxi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    private WebView mWv;
    private String detailUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        //接收地址
        Intent intent = getIntent();
        detailUrl = intent.getStringExtra("detailUrl");
        initView();
        mWv.loadUrl(detailUrl);
    }

    private void initView() {
        mWv = (WebView) findViewById(R.id.wv);
        WebSettings settings = mWv.getSettings();

        settings.setJavaScriptEnabled(true);
    }
}
