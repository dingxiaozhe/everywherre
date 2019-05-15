package com.example.administrator.everywherretrip.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.base.BaseActivity;
import com.example.administrator.everywherretrip.mvp.presenter.EmptyPresenter;
import com.example.administrator.everywherretrip.mvp.view.EmptyView;
import com.jaeger.library.StatusBarUtil;
import com.just.agentweb.AgentWeb;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {


    @BindView(R.id.mTv_Tool)
    TextView mTvTool;
    @BindView(R.id.mTool_Web)
    Toolbar mToolWeb;
    @BindView(R.id.mLl_Web)
    LinearLayout mLlWeb;
    @BindView(R.id.toolbar_img)
    ImageView toolbarImg;
    private AgentWeb web;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view_activity;
    }

    public static void startAct(Context context) {
        Intent intent = new Intent(context, WebViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        mToolWeb.setTitle("");
       // mToolWeb.setNavigationIcon(toolbarImg);
        setSupportActionBar(mToolWeb);
        toolbarImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        web = AgentWeb.with(this)
                .setAgentWebParent(mLlWeb, new LinearLayout.LayoutParams(-1, -1))
                .closeIndicator()
                .createAgentWeb()
                .ready()
                .go("https://api.banmi.com/app2017/agreement.html");

        /*new WebView(this).setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                //获取网页标题
                super.onReceivedTitle(view, title);
            }
        });*/

        web.getWebCreator().getWebView().setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (!TextUtils.isEmpty(title)) {
                    mTvTool.setText(title);
                }
                super.onReceivedTitle(view, title);
            }
        });

    }

    @Override
    protected void onPause() {
        web.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        web.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        web.getWebLifeCycle().onDestroy();
        super.onDestroy();
    }



}
