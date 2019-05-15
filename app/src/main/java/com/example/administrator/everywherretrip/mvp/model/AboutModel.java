package com.example.administrator.everywherretrip.mvp.model;

import com.example.administrator.everywherretrip.base.BaseModel;
import com.example.administrator.everywherretrip.bean.AboutBean;
import com.example.administrator.everywherretrip.bean.CollectionNo;
import com.example.administrator.everywherretrip.bean.CollectionOk;
import com.example.administrator.everywherretrip.bean.MainBean;
import com.example.administrator.everywherretrip.net.BaseObserver;
import com.example.administrator.everywherretrip.net.HttpUtils;
import com.example.administrator.everywherretrip.net.ResultCallBack;
import com.example.administrator.everywherretrip.net.RxUtils;
import com.example.administrator.everywherretrip.ui.api.MyApi;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class AboutModel extends BaseModel{
    public void model( int routeId ,final ResultCallBack<AboutBean> callback){
        MyApi apiserver = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<AboutBean> main = apiserver.getAbout(routeId,MyApi.param);
        main.compose(RxUtils.<AboutBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<AboutBean>() {

                    @Override
                    public void onNext(AboutBean aboutBean) {

                        callback.onSuccess(aboutBean);
                    }

                    @Override
                    public void error(String msg) {
                        callback.onFail(msg);
                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });
    }
    //收藏数据
    public void getCollectionOk(int id, final ResultCallBack<CollectionOk> callBack) {
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<CollectionOk> collectionOk = myApi.setCollectionOk(MyApi.param, id);
        collectionOk.compose(RxUtils.<CollectionOk>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CollectionOk>() {
                    @Override
                    public void onNext(CollectionOk collectionOk) {
                        callBack.onSuccess(collectionOk);
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

    //取消收藏
    public void getCollectionNo(int id, final ResultCallBack<CollectionNo> callBack) {
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<CollectionNo> collectionNo = myApi.setCollectionNo(MyApi.param, id);
        collectionNo.compose(RxUtils.<CollectionNo>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<CollectionNo>() {
                    @Override
                    public void onNext(CollectionNo collectionNo) {
                        callBack.onSuccess(collectionNo);
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
