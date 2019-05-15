package com.example.administrator.everywherretrip.mvp.presenter;

import com.example.administrator.everywherretrip.base.BasePresenter;
import com.example.administrator.everywherretrip.bean.DemoBean;
import com.example.administrator.everywherretrip.mvp.model.Model;
import com.example.administrator.everywherretrip.mvp.view.CodeView;
import com.example.administrator.everywherretrip.net.ResultCallBack;

public class CodePresenter extends BasePresenter<CodeView>{

    private Model model;

    @Override
    protected void initModel() {
        model = new Model();
        mModels.add(model);
    }
    public void getCode(){
        model.getVerifyCode(new ResultCallBack<DemoBean>() {
            @Override
            public void onSuccess(DemoBean bean){
                mMvpView.setCode(bean.getData());
            }

            @Override
            public void onFail(String msg) {
                mMvpView.setErrors(msg);
            }
        });
    }
}
