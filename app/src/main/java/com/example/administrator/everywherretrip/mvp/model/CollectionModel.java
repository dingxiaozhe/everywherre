package com.example.administrator.everywherretrip.mvp.model;

import com.example.administrator.everywherretrip.base.BaseModel;
import com.example.administrator.everywherretrip.bean.CollectionList;
import com.example.administrator.everywherretrip.net.BaseObserver;
import com.example.administrator.everywherretrip.net.HttpUtils;
import com.example.administrator.everywherretrip.net.ResultCallBack;
import com.example.administrator.everywherretrip.net.RxUtils;
import com.example.administrator.everywherretrip.ui.api.MyApi;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class CollectionModel extends BaseModel{
    //获取已收藏的数据
    public void getCollectionList(int page, final ResultCallBack<CollectionList> callBack){
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<CollectionList> collectionList = myApi.setCollectionList(MyApi.param , page);
        collectionList.compose(RxUtils.<CollectionList>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CollectionList>() {
                    @Override
                    public void onNext(CollectionList collectionList) {
                        callBack.onSuccess(collectionList);
                    }

                    @Override
                    public void error(String msg) {
                        callBack.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }
}
