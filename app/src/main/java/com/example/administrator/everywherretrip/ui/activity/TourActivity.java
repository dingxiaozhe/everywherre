package com.example.administrator.everywherretrip.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.net.AndroidJs;

public class TourActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private WebView webview;

    private String title;
    private TextView toolbar_te;
    private String cardUrl;
    private ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);
        Intent intent = getIntent();
        cardUrl = intent.getStringExtra("cardUrl");
        title = intent.getStringExtra("title");
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar_te = (TextView) findViewById(R.id.toolbar_te);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbar_te.setText(title);


        webview = (WebView) findViewById(R.id.webview);
        WebSettings settings = webview.getSettings();
        //支持js交互
        settings.setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());
        webview.loadUrl(cardUrl + "?os=android");
        webview.addJavascriptInterface(new AndroidJs(this), "android");


        img_back = (ImageView) findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
