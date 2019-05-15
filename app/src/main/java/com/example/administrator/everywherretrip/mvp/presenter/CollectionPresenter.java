package com.example.administrator.everywherretrip.mvp.presenter;

import com.example.administrator.everywherretrip.base.BasePresenter;
import com.example.administrator.everywherretrip.bean.CollectionList;
import com.example.administrator.everywherretrip.mvp.model.CollectionModel;
import com.example.administrator.everywherretrip.mvp.view.CollectionView;
import com.example.administrator.everywherretrip.net.ResultCallBack;
import com.umeng.socialize.media.Base;

public class CollectionPresenter extends BasePresenter<CollectionView>{

    private CollectionModel model;

    @Override
    protected void initModel() {
        model = new CollectionModel();
        mModels.add(model);
    }
    //收藏列表
    public void setLists(int page){
        model.getCollectionList(page, new ResultCallBack<CollectionList>() {
            @Override
            public void onSuccess(CollectionList bean) {
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
