package com.example.administrator.everywherretrip.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.base.BaseFragment;
import com.example.administrator.everywherretrip.bean.DynamicBean;
import com.example.administrator.everywherretrip.bean.WithState;
import com.example.administrator.everywherretrip.mvp.presenter.DynamicPresenter;
import com.example.administrator.everywherretrip.mvp.view.DynamicView;
import com.example.administrator.everywherretrip.ui.adapter.DynamicAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DynamicFragment extends BaseFragment<DynamicView, DynamicPresenter> implements DynamicView {

    private static final String TAG = "DynamicFragment";
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private int page = 1;
    private int mBanmIId = 1;
    private DynamicAdapter adapter;
    private  ArrayList<DynamicBean.ResultBean.ActivitiesBean> list;

    public void setban(int banmIIds) {

        mBanmIId = banmIIds;
    }

    
    @Override
    protected DynamicPresenter initPresenter() {

        return new DynamicPresenter();
    }

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_dynamic;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @Override
    public void setData(DynamicBean dynamicBean) {
            list.clear();
            list.addAll(dynamicBean.getResult().getActivities());
            adapter.setList(list);
            Log.e(TAG, "setData: "+list.size() );
            adapter.notifyDataSetChanged();
    }

    @Override
    protected void initView() {
       list=new ArrayList<>();
       recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new DynamicAdapter(getContext(), list);
        recyclerview.setAdapter(adapter);

        mPresenter.getState(mBanmIId,page);

    }

   
}
