package com.example.administrator.everywherretrip.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.base.BaseFragment;
import com.example.administrator.everywherretrip.bean.StayBean;
import com.example.administrator.everywherretrip.mvp.presenter.StayPresenter;
import com.example.administrator.everywherretrip.mvp.view.StayView;
import com.example.administrator.everywherretrip.ui.activity.DetailsActivity;
import com.example.administrator.everywherretrip.ui.adapter.MyAdapterStay;
import com.example.administrator.everywherretrip.util.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class StayFragment extends BaseFragment<StayView, StayPresenter> implements StayView {

    private static final String TAG = "StayFragment";
    @BindView(R.id.mTool_attention)
    Toolbar mToolAttention;
    @BindView(R.id.mRv_stay)
    RecyclerView mRvStay;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mrefreshLayout;
    private ArrayList<StayBean.ResultBean.BanmiBean> list;
    private MyAdapterStay adapter;
    private int page = 1;

    @Override
    protected StayPresenter initPresenter() {
        return new StayPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_stay;
    }

    @Override
    public void setList(StayBean stayBean) {
            list.clear();
            list.addAll(stayBean.getResult().getBanmi());
            adapter.addList(list);

    }

    @Override
    public void setToast(String str) {
        ToastUtil.showShort(str);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initView() {
        mRvStay.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new MyAdapterStay(getActivity(), true);
        mRvStay.setAdapter(adapter);

        //mPresenter.stayData(page);

        adapter.setItemOnFollow(new MyAdapterStay.itemOnFollow() {
            @Override
            public void onSend(int positionn) {
                boolean followed = list.get(positionn).isIsFollowed();
                int id = list.get(positionn).getId();
                if (followed) {
                    mPresenter.StaData(id);
                } else {
                    mPresenter.PayData(id);
                }
//                mPresenter.StaData(page);
            }
        });
        adapter.setOnClick(new MyAdapterStay.OnClick() {
            @Override
            public void onItemClick(StayBean.ResultBean.BanmiBean banmiBean, int position) {
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("id", banmiBean.getId());
                startActivity(intent);
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.stayData(page);
    }

    


}
