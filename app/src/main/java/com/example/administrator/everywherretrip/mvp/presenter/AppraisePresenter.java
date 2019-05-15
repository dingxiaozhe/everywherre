package com.example.administrator.everywherretrip.mvp.presenter;

import com.example.administrator.everywherretrip.base.BasePresenter;
import com.example.administrator.everywherretrip.bean.AppraiseBean;
import com.example.administrator.everywherretrip.mvp.model.AppraiseModel;
import com.example.administrator.everywherretrip.mvp.view.AppraiseView;
import com.example.administrator.everywherretrip.net.ResultCallBack;

public class AppraisePresenter extends BasePresenter<AppraiseView>{

    private AppraiseModel appraiseModel;

    @Override
    protected void initModel() {
        appraiseModel = new AppraiseModel();
        mModels.add(appraiseModel);
    }
    public  void getData(int banmiId,int page){
        appraiseModel.getAppraise(banmiId, page, new ResultCallBack<AppraiseBean>() {
            @Override
            public void onSuccess(AppraiseBean bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setData(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
