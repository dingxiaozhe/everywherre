package com.example.administrator.everywherretrip.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.base.BaseActivity;
import com.example.administrator.everywherretrip.bean.CollectionList;
import com.example.administrator.everywherretrip.mvp.presenter.CollectionPresenter;
import com.example.administrator.everywherretrip.mvp.view.CollectionView;
import com.example.administrator.everywherretrip.ui.adapter.MyAdapterCollection;
import com.jaeger.library.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionActivity extends BaseActivity<CollectionView, CollectionPresenter> implements CollectionView {


    @BindView(R.id.tv_tool)
    TextView tvTool;
    @BindView(R.id.mTool_attention)
    Toolbar mToolAttention;
    @BindView(R.id.mRv_stay)
    RecyclerView mRvStay;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private ArrayList<CollectionList.ResultBean.CollectedRoutesBean> mList;
    private MyAdapterCollection mCollection;
    @Override
    protected CollectionPresenter initPresenter() {
        return new CollectionPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setData(CollectionList collectionList) {
        mList.addAll(collectionList.getResult().getCollectedRoutes());
        mCollection.addList(mList);
    }

    @Override
    protected void initView() {
        StatusBarUtil.setLightMode(this);
        mToolAttention.setVisibility(View.VISIBLE);
        mToolAttention.setTitle("");
        setSupportActionBar(mToolAttention);
        tvTool.setText("我的收藏");

        mRvStay.setLayoutManager(new LinearLayoutManager(this));
        mList = new ArrayList<>();
        mCollection = new MyAdapterCollection(this);
        mRvStay.setAdapter(mCollection);
    }

    @Override
    protected void initData() {

        mPresenter.setLists(0);
    }
}
