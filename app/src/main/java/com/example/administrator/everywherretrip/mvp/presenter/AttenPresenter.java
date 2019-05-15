package com.example.administrator.everywherretrip.mvp.presenter;

import com.example.administrator.everywherretrip.base.BasePresenter;
import com.example.administrator.everywherretrip.bean.AttenBeanDelete;
import com.example.administrator.everywherretrip.bean.AttenBeanInsert;
import com.example.administrator.everywherretrip.bean.AttenList;
import com.example.administrator.everywherretrip.mvp.model.AttenModel;
import com.example.administrator.everywherretrip.mvp.view.AttenView;
import com.example.administrator.everywherretrip.net.ResultCallBack;

public class AttenPresenter extends BasePresenter<AttenView>{

    private AttenModel attenModel;

    @Override
    protected void initModel() {
        attenModel = new AttenModel();
        mModels.add(attenModel);
    }
    //关注
    public void getInsert(final int banmild) {
        attenModel.getPay(banmild, new ResultCallBack<AttenBeanInsert>() {
            @Override
            public void onSuccess(AttenBeanInsert bean) {
                if (bean != null) {
                    if (mMvpView != null) {
                        mMvpView.setToast(bean.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    //取消关注
    public void getDelete(int banmild) {
        attenModel.getOff(banmild, new ResultCallBack<AttenBeanDelete>() {
            @Override
            public void onSuccess(AttenBeanDelete bean) {
                if (bean != null) {
                    if (mMvpView != null) {
                        mMvpView.setToast(bean.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }


    //关注列表
    public void getDataAtten(int page) {
        attenModel.getList(page, new ResultCallBack<AttenList>() {
            @Override
            public void onSuccess(AttenList bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setAttenList(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

}
