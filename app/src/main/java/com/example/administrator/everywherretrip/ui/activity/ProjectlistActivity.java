package com.example.administrator.everywherretrip.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.base.BaseActivity;
import com.example.administrator.everywherretrip.bean.ProjectlistBean;
import com.example.administrator.everywherretrip.mvp.presenter.ProjectlistPresenter;
import com.example.administrator.everywherretrip.mvp.view.ProjectlistView;
import com.example.administrator.everywherretrip.ui.adapter.ProjectlistAdapter;
import com.umeng.commonsdk.debug.I;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectlistActivity extends BaseActivity<ProjectlistView, ProjectlistPresenter> implements ProjectlistView {


    @BindView(R.id.toolbar_te)
    TextView toolbarTe;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.img_back)
    ImageView imgBack;
    private ArrayList<ProjectlistBean.ResultBean.BundlesBean> list;
    private ProjectlistAdapter adapter;

    @Override
    protected ProjectlistPresenter initPresenter() {
        return new ProjectlistPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_projectlist;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setData(ProjectlistBean projectlistBean) {
        //list.clear();
        list.addAll(projectlistBean.getResult().getBundles());
        adapter.setList(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initView() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        list = new ArrayList<>();
        toolbarTe.setText(getResources().getString(R.string.projectlist));
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProjectlistAdapter(list, this);
        recyclerview.setAdapter(adapter);
        adapter.setOnClick(new ProjectlistAdapter.onClick() {
            @Override
            public void onSend(int position, ProjectlistBean.ResultBean.BundlesBean undlesBean) {
                Intent intent = new Intent(ProjectlistActivity.this, TourActivity.class);
                intent.putExtra("cardUrl",undlesBean.getContentURL());
                intent.putExtra("title",undlesBean.getTitle());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getProject();
    }

}
