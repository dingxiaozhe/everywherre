package com.example.administrator.everywherretrip.mvp.presenter;

import com.example.administrator.everywherretrip.base.BasePresenter;
import com.example.administrator.everywherretrip.bean.MainBean;
import com.example.administrator.everywherretrip.mvp.model.HomeModel;
import com.example.administrator.everywherretrip.mvp.view.HomeView;
import com.example.administrator.everywherretrip.net.ResultCallBack;

public class HomePresenter extends BasePresenter<HomeView>{

    private HomeModel model;

    @Override
    protected void initModel() {
        model = new HomeModel();
        mModels.add(model);
    }
    public void getPre(){
        model.model(new ResultCallBack<MainBean>() {
            @Override
            public void onSuccess(MainBean bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.getData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
