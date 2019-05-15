package com.example.administrator.everywherretrip.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.base.BaseActivity;
import com.example.administrator.everywherretrip.base.Constants;
import com.example.administrator.everywherretrip.bean.MessageBean;
import com.example.administrator.everywherretrip.mvp.presenter.EmptyPresenter;
import com.example.administrator.everywherretrip.mvp.presenter.MessagePre;
import com.example.administrator.everywherretrip.mvp.view.EmptyView;
import com.example.administrator.everywherretrip.mvp.view.MessageV;
import com.example.administrator.everywherretrip.util.SpUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class Personage_DataActivity extends BaseActivity<MessageV,MessagePre> implements MessageV {

    @BindView(R.id.img_replace)
    ImageView imgReplace;
    @BindView(R.id.mTv_pers)
    TextView mTvPers;
    @BindView(R.id.mTv_outs)
    TextView mTvOuts;
    @BindView(R.id.mTool_data)
    Toolbar mToolData;
    @BindView(R.id.mEt_per)
    EditText mEtPer;
    @BindView(R.id.mTv_perSize)
    TextView mTvPerSize;
    private int mI;
    @Override
    protected MessagePre initPresenter() {
        return new MessagePre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_personage__data;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        mI = intent.getIntExtra("i", 0);
        mEtPer.setText(data);
        mTvPerSize.setText((30-data.length())+"/30");


    }

    @Override
    protected void initListener() {
        mEtPer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTvPerSize.setText(30-s.length()+"/30");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.img_replace, R.id.mTv_outs})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_replace:
                finish();
                break;
            case R.id.mTv_outs:
                showLoading();
                String trim = mEtPer.getText().toString().trim();
                if (mI == 1){
                    SpUtil.setParam(Constants.USERNAME,trim);
                }else {
                    SpUtil.setParam(Constants.DESC,trim);
                }
                mPresenter.outData();
                finish();
                break;
        }
    }

    @Override
    public void setMessage(MessageBean message) {

    }

    @Override
    public void setError(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
