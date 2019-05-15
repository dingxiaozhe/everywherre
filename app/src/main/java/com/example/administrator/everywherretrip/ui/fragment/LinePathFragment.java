package com.example.administrator.everywherretrip.ui.fragment;


import android.app.NotificationManager;
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
import com.example.administrator.everywherretrip.bean.WithPath;
import com.example.administrator.everywherretrip.bean.WithState;
import com.example.administrator.everywherretrip.mvp.presenter.LinePathPresenter;
import com.example.administrator.everywherretrip.mvp.view.LinePathView;
import com.example.administrator.everywherretrip.ui.adapter.LinePathAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class LinePathFragment extends BaseFragment<LinePathView, LinePathPresenter> implements LinePathView {

    private static final String TAG = "LinePathFragment";
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int page = 1;
    private int mBanmIId = 1;
    private ArrayList<WithPath.ResultBean.RoutesBean> mList;
    private LinePathAdapter adapter;
    public void setban(int banmIIds) {

        mBanmIId = banmIIds;
    }
    @Override
    protected LinePathPresenter initPresenter() {
        return new LinePathPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_line_path;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }




    @Override
    public void getDataStr(WithPath withPath) {
        mList.clear();
        mList.addAll(withPath.getResult().getRoutes());
        adapter.setmList(mList);
        Log.e(TAG, "getDataStr: " +mList.size());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initView() {
        mList=new ArrayList<>();
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new  LinePathAdapter(mList,getContext());
        recyclerview.setAdapter(adapter);

        mPresenter.getDataSt(mBanmIId,page);
    }


}
