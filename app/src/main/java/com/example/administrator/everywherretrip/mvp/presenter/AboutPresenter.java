package com.example.administrator.everywherretrip.mvp.presenter;

import com.example.administrator.everywherretrip.base.BasePresenter;
import com.example.administrator.everywherretrip.bean.AboutBean;
import com.example.administrator.everywherretrip.bean.CollectionNo;
import com.example.administrator.everywherretrip.bean.CollectionOk;
import com.example.administrator.everywherretrip.bean.MainBean;
import com.example.administrator.everywherretrip.mvp.model.AboutModel;
import com.example.administrator.everywherretrip.mvp.view.AboutView;
import com.example.administrator.everywherretrip.net.ResultCallBack;


public class AboutPresenter extends BasePresenter<AboutView>{

    private AboutModel aboutModel;

    @Override
    protected void initModel() {
        aboutModel = new AboutModel();
        mModels.add(aboutModel);
    }


public  void getPer(int routeId){
        aboutModel.model(routeId,new ResultCallBack<AboutBean>() {
            @Override
            public void onSuccess(AboutBean bean) {
                if(bean!=null){
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
    //收藏
    public void getOk(int id){
        aboutModel.getCollectionOk(id, new ResultCallBack<CollectionOk>() {
            @Override
            public void onSuccess(CollectionOk bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setToast(bean.getDesc());
                    }
                }
            }

            @Override
            public void onFail(String msg) {


            }
        });
    }

    //取消收藏
    public void getNo(int id){
        aboutModel.getCollectionNo(id, new ResultCallBack<CollectionNo>() {
            @Override
            public void onSuccess(CollectionNo bean) {
                if (bean!=null){
                    if (mMvpView!=null){
                        mMvpView.setToast(bean.getDesc());
                    }
                }
            }

            @Override
            public void onFail(String msg) {


            }
        });
    }
}
