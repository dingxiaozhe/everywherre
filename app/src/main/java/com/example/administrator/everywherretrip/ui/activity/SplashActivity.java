package com.example.administrator.everywherretrip.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.base.BaseActivity;
import com.example.administrator.everywherretrip.base.Constants;
import com.example.administrator.everywherretrip.mvp.presenter.EmptyPresenter;
import com.example.administrator.everywherretrip.mvp.view.EmptyView;
import com.example.administrator.everywherretrip.util.SpUtil;
import com.umeng.commonsdk.debug.E;

public class SplashActivity extends BaseActivity<EmptyView,EmptyPresenter> implements EmptyView{

    private static final String TAG = "SplashActivity";
    @Override
    protected EmptyPresenter initPresenter() {

        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {

        return R.layout.activity_splash;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
    @Override
    protected void initView() {

        Boolean param = (Boolean) SpUtil.getParam(Constants.BOOLEAN, false);
        if (param){
            String param1 = (String) SpUtil.getParam(Constants.TOKEN, "");
            String param2 = (String) SpUtil.getParam(Constants.CODEMAIN, "");
            if (!(param1.equals(""))||(!param2.equals(""))){
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }else {
                startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                finish();
            }
        }else {
            startActivity(new Intent(SplashActivity.this,GuideActivity.class));
            Log.e(TAG, "initView: splash");
            finish();
        }
    }
}
