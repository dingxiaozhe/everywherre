package com.example.administrator.everywherretrip.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.base.BaseFragment;
import com.example.administrator.everywherretrip.bean.MainBean;
import com.example.administrator.everywherretrip.mvp.presenter.HomePresenter;
import com.example.administrator.everywherretrip.mvp.view.HomeView;
import com.example.administrator.everywherretrip.ui.activity.AboutActivity;
import com.example.administrator.everywherretrip.ui.activity.TourActivity;
import com.example.administrator.everywherretrip.ui.adapter.MyAdapterHome;
import com.umeng.commonsdk.debug.I;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView, MyAdapterHome.onItemListener, MyAdapterHome.onHomeClick {
    private static final String TAG = "HomeFragment";

    @BindView(R.id.mRv_home)
    RecyclerView mRvHome;
    private ArrayList<MainBean.ResultBean.BannersBean> bannerList;
    private ArrayList<MainBean.ResultBean.RoutesBean> dataList;
    private MyAdapterHome adapterHome;
    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void getData(MainBean mainBean) {
        List<MainBean.ResultBean.BannersBean> beanList = mainBean.getResult().getBanners();
        List<MainBean.ResultBean.RoutesBean> list = mainBean.getResult().getRoutes();
        bannerList.addAll(beanList);
        dataList.addAll(list);
        adapterHome.addBar(bannerList);
        adapterHome.addData(dataList);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initView() {
        mRvHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        bannerList = new ArrayList<>();
        dataList = new ArrayList<>();
        adapterHome = new MyAdapterHome(getActivity());
        mRvHome.setAdapter(adapterHome);
        adapterHome.setOnItemListener(this);
        adapterHome.setOnHomeClick(this);
    }

    @Override
    protected void initData() {

        mPresenter.getPre();
    }


    @Override
    public void onSend(int id) {
        Intent intent = new Intent(getContext(), AboutActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }


    @Override
    public void onHome(int position, MainBean.ResultBean.RoutesBean routesBean) {
        Intent intent = new Intent(getContext(), TourActivity.class);
        intent.putExtra("title",routesBean.getTitle());
        intent.putExtra("cardUrl",routesBean.getContentURL());
        startActivity(intent);
    }
}
