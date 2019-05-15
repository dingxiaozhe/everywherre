package com.example.administrator.everywherretrip.mvp.presenter;

import com.example.administrator.everywherretrip.base.BasePresenter;
import com.example.administrator.everywherretrip.bean.AttenBeanDelete;
import com.example.administrator.everywherretrip.bean.AttenBeanInsert;
import com.example.administrator.everywherretrip.bean.StayBean;
import com.example.administrator.everywherretrip.mvp.model.StayModel;
import com.example.administrator.everywherretrip.mvp.view.StayView;
import com.example.administrator.everywherretrip.net.ResultCallBack;
import com.example.administrator.everywherretrip.util.ToastUtil;

public class StayPresenter extends BasePresenter<StayView>{

    private StayModel model;

    @Override
    protected void initModel() {
        model = new StayModel();
        mModels.add(model);
    }
    public void stayData(int page) {
        model.getModel(page, new ResultCallBack<StayBean>() {
            @Override
            public void onSuccess(StayBean bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setList(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                ToastUtil.showShort(msg);
            }
        });
    }

    /*
    * 关注
    * */
    public  void PayData(int banmiId){
        model.getPay(banmiId, new ResultCallBack<AttenBeanInsert>() {
            @Override
            public void onSuccess(AttenBeanInsert bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setToast(bean.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    /*
    * 取消关注
    * */
    public  void StaData(int banmiId){
        model.getSta(banmiId, new ResultCallBack<AttenBeanDelete>() {
            @Override
            public void onSuccess(AttenBeanDelete bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setToast(bean.getResult().getMessage());
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
