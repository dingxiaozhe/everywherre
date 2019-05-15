package com.example.administrator.everywherretrip.mvp.presenter;

import com.example.administrator.everywherretrip.base.BasePresenter;
import com.example.administrator.everywherretrip.bean.DynamicBean;
import com.example.administrator.everywherretrip.mvp.model.DynamicModel;
import com.example.administrator.everywherretrip.mvp.view.DynamicView;
import com.example.administrator.everywherretrip.net.ResultCallBack;

public class DynamicPresenter extends BasePresenter<DynamicView>{

    private DynamicModel dynamicModel;

    @Override
    protected void initModel() {
        dynamicModel = new DynamicModel();
        mModels.add(dynamicModel);
    }
    public  void getState(int banmiId,int page){
        dynamicModel.setData(banmiId, page, new ResultCallBack<DynamicBean>() {
            @Override
            public void onSuccess(DynamicBean bean) {
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
