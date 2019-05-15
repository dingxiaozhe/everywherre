package com.example.administrator.everywherretrip.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.base.BaseActivity;
import com.example.administrator.everywherretrip.bean.AppraiseBean;
import com.example.administrator.everywherretrip.mvp.presenter.AppraisePresenter;
import com.example.administrator.everywherretrip.mvp.view.AppraiseView;
import com.example.administrator.everywherretrip.ui.adapter.AppraiseAdapter;
import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppraiseActivity extends BaseActivity<AppraiseView, AppraisePresenter> implements AppraiseView {

    private static final String TAG = "AppraiseActivity";
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.toolbar_te)
    TextView toolbarTe;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refresh)
    SmartRefreshLayout refresh;
    private ArrayList<AppraiseBean.ResultBean.ReviewsBean> list;
    private AppraiseAdapter adapter;
    private int id;
    private int page ;

    @Override
    protected AppraisePresenter initPresenter() {
        return new AppraisePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_appraise;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setData(AppraiseBean appraiseBean) {
        list.clear();
        list.addAll(appraiseBean.getResult().getReviews());
        adapter.setList(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
        Log.e(TAG, "initView: " + id);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        toolbarTe.setText(getResources().getString(R.string.appraise));
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        list = new ArrayList<>();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AppraiseAdapter(list, this);
        recyclerview.setAdapter(adapter);


        refresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=1;
                list.clear();
                adapter.notifyDataSetChanged();
                refresh.finishRefresh();
            }
        });
        refresh.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page++;
                adapter.notifyDataSetChanged();
                refresh.finishLoadmore();
            }
        });
        refresh.setEnableRefresh(true);
        refresh.setEnableLoadmore(true);
        refresh.finishRefresh();
        refresh.finishLoadmore();

        mPresenter.getData(id, page);


    }


}
