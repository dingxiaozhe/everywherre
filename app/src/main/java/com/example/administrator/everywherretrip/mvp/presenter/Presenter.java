package com.example.administrator.everywherretrip.mvp.presenter;

import com.example.administrator.everywherretrip.base.BasePresenter;
import com.example.administrator.everywherretrip.bean.DemoBean;
import com.example.administrator.everywherretrip.mvp.model.Model;
import com.example.administrator.everywherretrip.mvp.view.IView;
import com.example.administrator.everywherretrip.net.ResultCallBack;

public class Presenter extends BasePresenter<IView>{
Model model;
    @Override
    protected void initModel() {
        this.model=new Model();
        mModels.add(model);
    }
    public void getModel(){
        model.getVerifyCode(new ResultCallBack<DemoBean>() {
            @Override
            public void onSuccess(DemoBean bean) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
