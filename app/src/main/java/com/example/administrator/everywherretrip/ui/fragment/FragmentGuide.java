package com.example.administrator.everywherretrip.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.base.BaseFragment;
import com.example.administrator.everywherretrip.mvp.presenter.EmptyPresenter;
import com.example.administrator.everywherretrip.mvp.view.EmptyView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class FragmentGuide extends BaseFragment<EmptyView, EmptyPresenter> implements EmptyView {

    @BindView(R.id.mImg_Frag)
    ImageView mImgFrag;
    private int mInt;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_fragment_guide;
    }
    public void setImg(int i) {
        mInt=i;
    }
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }




    @Override
    protected void initView() {
        if (mInt==0){
            mImgFrag.setImageResource(R.mipmap.guide_01);
        }else if (mInt==1){
            mImgFrag.setImageResource(R.mipmap.guide_02);
        }else {
            mImgFrag.setImageResource(R.mipmap.guide_03);
        }
    }
}
