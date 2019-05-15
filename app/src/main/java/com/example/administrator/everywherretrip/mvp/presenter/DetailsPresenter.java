package com.example.administrator.everywherretrip.mvp.presenter;

import com.example.administrator.everywherretrip.base.BasePresenter;
import com.example.administrator.everywherretrip.bean.DynamicBean;
import com.example.administrator.everywherretrip.bean.WithPath;
import com.example.administrator.everywherretrip.bean.WithState;
import com.example.administrator.everywherretrip.mvp.model.DetailsModel;
import com.example.administrator.everywherretrip.mvp.view.DetailsView;
import com.example.administrator.everywherretrip.net.ResultCallBack;

public  class DetailsPresenter extends BasePresenter<DetailsView>{

    private DetailsModel detailsModel;

    @Override
    protected void initModel() {
        detailsModel = new DetailsModel();
        mModels.add(detailsModel);
    }
    public  void getData(int banmiId,int page){
        detailsModel.getData(banmiId, page, new ResultCallBack<DynamicBean>() {
            @Override
            public void onSuccess(DynamicBean bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.getDataTitle(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
    public  void getDataSt(int binId,int page){
        detailsModel.getDataStr(binId, page, new ResultCallBack<WithPath>() {
            @Override
            public void onSuccess(WithPath bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.getDataStr(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
