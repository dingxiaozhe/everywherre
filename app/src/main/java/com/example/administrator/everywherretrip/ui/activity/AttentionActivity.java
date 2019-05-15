package com.example.administrator.everywherretrip.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.base.BaseActivity;
import com.example.administrator.everywherretrip.bean.AttenBeanInsert;
import com.example.administrator.everywherretrip.bean.AttenList;
import com.example.administrator.everywherretrip.mvp.presenter.AttenPresenter;
import com.example.administrator.everywherretrip.mvp.presenter.EmptyPresenter;
import com.example.administrator.everywherretrip.mvp.view.AttenView;
import com.example.administrator.everywherretrip.mvp.view.EmptyView;
import com.example.administrator.everywherretrip.ui.adapter.MyAdapterAtten;
import com.example.administrator.everywherretrip.ui.adapter.MyAdapterStay;
import com.example.administrator.everywherretrip.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class AttentionActivity extends BaseActivity<AttenView, AttenPresenter> implements AttenView {


    @BindView(R.id.mTool_attention)
    Toolbar mToolAttention;
    @BindView(R.id.mRv_stay)
    RecyclerView mRvStay;
    private MyAdapterStay mAdapterStay;
    private ArrayList<AttenList.ResultBean.BanmiBean> mList;
    private MyAdapterAtten adapter;

    @Override
    protected AttenPresenter initPresenter() {
        return new AttenPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_attention;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initView() {
        mToolAttention.setVisibility(View.VISIBLE);
        mToolAttention.setTitle("");
        setSupportActionBar(mToolAttention);

        mRvStay.setLayoutManager(new LinearLayoutManager(this));
        mAdapterStay = new MyAdapterStay(this, false);
        mRvStay.setAdapter(mAdapterStay);
        mList=new ArrayList<>();
        adapter = new MyAdapterAtten(this, true);
        mRvStay.setAdapter(adapter);
        adapter.setItemOnFollow(new MyAdapterStay.itemOnFollow() {
            @Override
            public void onSend(int positionn) {
                boolean isFollowed = mList.get(positionn).isIsFollowed();
                int id = mList.get(positionn).getId();
                if (isFollowed) {
                    mPresenter.getDelete(id);

                } else {
                    mPresenter.getInsert(id);
                }
                mPresenter.getDataAtten(1);
            }
        });
        mPresenter.getDataAtten(1);
        }

    @Override
    public void setAttenList(AttenList bean) {
        if (bean != null) {
            mList.clear();
            mList.addAll(bean.getResult().getBanmi());
            adapter.addList(mList);
        }

    }

    @Override
    public void setToast(String str) {
        ToastUtil.showShort(str);

    }

}
