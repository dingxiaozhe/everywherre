package com.example.administrator.everywherretrip.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.base.BaseActivity;
import com.example.administrator.everywherretrip.base.Constants;
import com.example.administrator.everywherretrip.bean.MessageBean;
import com.example.administrator.everywherretrip.mvp.presenter.MessagePre;
import com.example.administrator.everywherretrip.mvp.presenter.MessagePresenter;
import com.example.administrator.everywherretrip.mvp.view.MessageV;
import com.example.administrator.everywherretrip.mvp.view.MessageView;
import com.example.administrator.everywherretrip.util.SpUtil;
import com.example.administrator.everywherretrip.widget.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageActivity extends BaseActivity<MessageV, MessagePre> implements MessageV {


    @BindView(R.id.img_replace)
    ImageView imgReplace;
    @BindView(R.id.mTool_mess)
    Toolbar mToolMess;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.coupon)
    RelativeLayout coupon;
    @BindView(R.id.mTv_user)
    TextView mTvUser;
    @BindView(R.id.journey)
    RelativeLayout journey;
    @BindView(R.id.mTv_sex)
    TextView mTvSex;
    @BindView(R.id.collect)
    RelativeLayout collect;
    @BindView(R.id.mTv_sig)
    TextView mTvSig;
    @BindView(R.id.attention)
    RelativeLayout attention;
    @BindView(R.id.out_psw)
    RelativeLayout outPsw;
    @BindView(R.id.out_phone)
    RelativeLayout outPhone;
    @BindView(R.id.mBut_exit)
    Button mButExit;
    private PopupWindow mWindow;
    private TextView mTv_nos;
    private TextView mTv_man;
    private TextView mTv_lady;
    private TextView mTv_secrecy;
    private String mImg;
    @Override
    protected MessagePre initPresenter() {
        return new MessagePre();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initView() {
        mImg = (String) SpUtil.getParam(Constants.PHOTO, "s");
        //RequestOptions options = new RequestOptions().placeholder(R.mipmap.zhanweitu_touxiang);
        //Glide 圆形图片
        RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                .skipMemoryCache(true).placeholder(R.mipmap.zhanweitu_touxiang);//不做内存缓存
        GlideApp.with(this).load(mImg).apply(mRequestOptions).into(imgHead);

        String name = (String) SpUtil.getParam(Constants.USERNAME, "name");
        String desc = (String) SpUtil.getParam(Constants.DESC, "desc");
        String gender = (String) SpUtil.getParam(Constants.GENDER, "gender");

        mTvUser.setText(name);
        mTvSex.setText(gender);
        mTvSig.setText(desc);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initView();
    }
    @OnClick({R.id.img_replace, R.id.attention, R.id.out_psw, R.id.out_phone, R.id.mBut_exit, R.id.img_head, R.id.journey, R.id.collect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_replace:
                //返回
                startActivity(new Intent(MessageActivity.this,MainActivity.class));
                finish();
                break;
            case R.id.out_psw:
                //修改密码
                break;
            case R.id.out_phone:
                //修改手机号
                break;
            case R.id.mBut_exit:
                //退出登录
                break;
            case R.id.img_head:
                //设置头像
                Intent intentImg = new Intent(MessageActivity.this, HeadActivity.class);
                intentImg.putExtra("img",mImg);
                startActivity(intentImg);
                break;
            case R.id.journey:
                //设置昵称
                Intent intentUser = new Intent(MessageActivity.this, Personage_DataActivity.class);
                intentUser.putExtra("data", mTvUser.getText().toString().trim());
                intentUser.putExtra("i", 1);
                startActivity(intentUser);
                break;
            case R.id.collect:
                //设置性别
                initpopupSex();
                break;
            case R.id.attention:
                //设置个性签名
                Intent intentPer = new Intent(MessageActivity.this, Personage_DataActivity.class);
                intentPer.putExtra("data", mTvSig.getText().toString().trim());
                intentPer.putExtra("i", 2);
                startActivity(intentPer);
                break;
        }
    }

    private void initpopupSex() {
        View view = LayoutInflater.from(this).inflate(R.layout.item_popup_sex, null);
        mWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        mTv_nos = view.findViewById(R.id.mTv_nos);
        mTv_man = view.findViewById(R.id.mTv_man);
        mTv_lady = view.findViewById(R.id.mTv_lady);
        mTv_secrecy = view.findViewById(R.id.mTv_secrecy);


       /* mWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.c_60)));*/
        mWindow.setOutsideTouchable(true);
        //设置除布局外的点击事件
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭popop
                mWindow.dismiss();
            }
        });
        mWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

        mTv_nos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWindow.dismiss();
            }
        });
        mTv_man.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvSex.setText(mTv_man.getText());
                mWindow.dismiss();
            }
        });
        mTv_lady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvSex.setText(mTv_lady.getText());
                mWindow.dismiss();
            }
        });
        mTv_secrecy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWindow.dismiss();
            }
        });

        SpUtil.setParam(Constants.GENDER,mTvSex.getText().toString().trim());

    }

    @Override
    protected void initData() {

        mPresenter.newData();
    }

    @Override
    public void setMessage(MessageBean message) {
        MessageBean.ResultBean bean = message.getResult();
        mTvUser.setText(bean.getUserName());
        mTvSig.setText(bean.getDescription());

        SpUtil.setParam(Constants.USERNAME,bean.getUserName());
        SpUtil.setParam(Constants.DESC,bean.getDescription());
    }

    @Override
    public void setError(String msg) {
    }
}
