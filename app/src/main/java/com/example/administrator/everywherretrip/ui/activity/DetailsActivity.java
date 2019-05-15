package com.example.administrator.everywherretrip.ui.activity;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.base.BaseActivity;
import com.example.administrator.everywherretrip.base.BaseFragment;
import com.example.administrator.everywherretrip.bean.DynamicBean;
import com.example.administrator.everywherretrip.bean.WithPath;
import com.example.administrator.everywherretrip.bean.WithState;
import com.example.administrator.everywherretrip.mvp.presenter.DetailsPresenter;
import com.example.administrator.everywherretrip.mvp.view.DetailsView;
import com.example.administrator.everywherretrip.ui.adapter.FragmentAdapter;
import com.example.administrator.everywherretrip.ui.fragment.DynamicFragment;
import com.example.administrator.everywherretrip.ui.fragment.LinePathFragment;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailsActivity extends BaseActivity<DetailsView, DetailsPresenter> implements DetailsView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.userPhoto)
    ImageView userPhoto;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.tv_about)
    TextView tvAbout;
    @BindView(R.id.img_follow)
    ImageView imgFollow;
    @BindView(R.id.img_en)
    ImageView imgEn;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.img_zhan)
    ImageView imgZhan;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.tv_intro)
    TextView tvIntro;
    @BindView(R.id.tv_introduction)
    TextView tvIntroduction;
    @BindView(R.id.attention)
    TextView attention;
    private ArrayList<BaseFragment> list;
    private FragmentAdapter adapter;
    private int id;
    private int page = 1;
    private String mPhoto;
    private static final String TAG = "DetailsActivity";
    @Override
    protected DetailsPresenter initPresenter() {
        return new DetailsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
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
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        id = getIntent().getIntExtra("id", 0);
        mPresenter.getData(page,id);
        mPresenter.getDataSt(page,id);

        list = new ArrayList<>();
        DynamicFragment dynamic = new DynamicFragment();
        dynamic.setban(id);
        list.add(dynamic);

        LinePathFragment linePathFragment = new LinePathFragment();
        linePathFragment.setban(id);
       list.add(linePathFragment);

        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, list);
        viewpager.setAdapter(adapter);
        tablayout.addTab(tablayout.newTab().setText(getResources().getString(R.string.dynamic)));
        tablayout.addTab(tablayout.newTab().setText(getResources().getString(R.string.line_path)));
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

    }

    @Override
    protected void initData() {

        mPresenter.getData(id, page);
        mPresenter.getDataSt(id,page);
    }

    @Override
    public void getDataTitle(DynamicBean dynamicBean) {
        DynamicBean.ResultBean.BanmiBean banmi = dynamicBean.getResult().getBanmi();
        mPhoto = banmi.getPhoto();
        Glide.with(this).load(mPhoto).into(userPhoto);
        userName.setText(banmi.getName());
        Log.e(TAG, "getDataTitle: "+userName );
        tvAbout.setText(banmi.getFollowing() + "人关注");
        tvCity.setText(banmi.getLocation());
        tvIntro.setText(banmi.getOccupation());
        tvIntroduction.setText(banmi.getIntroduction());
        boolean followed = banmi.isIsFollowed();
        if (followed) {
            Glide.with(this).load(R.mipmap.follow).into(imgFollow);
            attention.setText(getResources().getString(R.string.atten));
        }else {
            Glide.with(this).load(R.mipmap.follow_unselected).into(imgFollow);
            attention.setText(getResources().getString(R.string.not_attention));
        }
    }

    @Override
    public void getDataStr(WithPath withPath) {

    }

}
