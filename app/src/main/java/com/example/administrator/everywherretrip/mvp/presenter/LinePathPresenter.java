package com.example.administrator.everywherretrip.mvp.presenter;

import com.example.administrator.everywherretrip.base.BasePresenter;
import com.example.administrator.everywherretrip.bean.WithPath;
import com.example.administrator.everywherretrip.bean.WithState;
import com.example.administrator.everywherretrip.mvp.model.LinePathModel;
import com.example.administrator.everywherretrip.mvp.view.LinePathView;
import com.example.administrator.everywherretrip.net.ResultCallBack;

public class LinePathPresenter extends BasePresenter<LinePathView>{

    private LinePathModel model;

    @Override
    protected void initModel() {
        model = new LinePathModel();
        mModels.add(model);
    }
  /*  public  void getData(int banmiId,int page){
        model.getData(banmiId, page, new ResultCallBack<WithState>() {
            @Override
            public void onSuccess(WithState bean) {
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
    }*/
    public  void getDataSt(int binId,int page){
        model.getDataStr(binId, page, new ResultCallBack<WithPath>() {
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
