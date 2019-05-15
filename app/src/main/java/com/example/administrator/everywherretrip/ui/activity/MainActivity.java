package com.example.administrator.everywherretrip.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.base.BaseActivity;
import com.example.administrator.everywherretrip.base.BaseFragment;
import com.example.administrator.everywherretrip.base.Constants;
import com.example.administrator.everywherretrip.mvp.presenter.Presenter;
import com.example.administrator.everywherretrip.mvp.view.IView;
import com.example.administrator.everywherretrip.ui.adapter.FragmentAdapter;
import com.example.administrator.everywherretrip.ui.fragment.HomeFragment;
import com.example.administrator.everywherretrip.ui.fragment.StayFragment;
import com.example.administrator.everywherretrip.util.SpUtil;
import com.umeng.commonsdk.debug.D;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<IView, Presenter> implements IView {


    @BindView(R.id.mImg_tool)
    ImageView mImgTool;
    @BindView(R.id.mTool_main)
    Toolbar mToolMain;
    @BindView(R.id.mVp)
    ViewPager mVp;
    @BindView(R.id.mTab)
    TabLayout mTab;
    @BindView(R.id.mNv)
    NavigationView mNv;
    @BindView(R.id.mDl)
    DrawerLayout mDl;
    private ArrayList<BaseFragment> list;
    private static final String TAG = "MainActivity";
    private FragmentAdapter adapter;
    private String mName;
    private String mImg;
    private String mDesc;
    private TextView mMTv_name;
    private ImageView mMIv;
    private TextView mMTv_signature;
    private RelativeLayout mMTv_attention;
    @Override
    protected Presenter initPresenter() {
        return new Presenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    public static void startAct(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
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
        mToolMain.setTitle("");
        setSupportActionBar(mToolMain);
        list=new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new StayFragment());
        FragmentManager fm = getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, list);
        mVp.setAdapter(adapter);
        mTab.addTab(mTab.newTab().setText("首页").setIcon(R.drawable.photoback));
        mTab.addTab(mTab.newTab().setText("伴米").setIcon(R.drawable.photobacks));
        mVp.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTab));
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVp.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDl, mToolMain, R.string.app_name, R.string.app_name);
        mDl.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);
        toggle.syncState();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        set();
    }
    private  void set(){
        mName = (String) SpUtil.getParam(Constants.USERNAME, "");
        mImg = (String) SpUtil.getParam(Constants.PHOTO, "");
        mDesc = (String) SpUtil.getParam(Constants.DESC, "");
        mMTv_name.setText(mName);
        mMTv_signature.setText(mDesc);
        //Glide 圆形图片
        RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                .skipMemoryCache(true).placeholder(R.mipmap.zhanweitu_touxiang);//不做内存缓存

        Glide.with(this).load(mImg).apply(mRequestOptions).into(mMIv);
        Glide.with(this).load(mImg).apply(mRequestOptions).into(mImgTool);
    }

    @Override
    protected void initData() {
        mPresenter.getModel();
    }

    @Override
    protected void initListener() {
        View view = mNv.getHeaderView(0);
        RelativeLayout mRl = view.findViewById(R.id.rl_tit);
        RelativeLayout mRls = view.findViewById(R.id.collect);
        mMTv_name = view.findViewById(R.id.mTv_name);
        mMIv = view.findViewById(R.id.mIv);
        mMTv_signature = view.findViewById(R.id.mTv_signature);
        mMTv_attention = view.findViewById(R.id.attention);

        set();
        mRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MessageActivity.class));
            }
        });
        mMTv_attention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AttentionActivity.class));
            }
        });
        mRls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,CollectionActivity.class));
                mDl.closeDrawer(Gravity.LEFT);
            }
        });
    }
    @OnClick(R.id.mImg_tool)
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.mImg_tool:
                mDl.openDrawer(Gravity.LEFT);
                break;
        }
    }

}
