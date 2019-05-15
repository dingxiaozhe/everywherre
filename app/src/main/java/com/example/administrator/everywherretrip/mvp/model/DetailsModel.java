package com.example.administrator.everywherretrip.mvp.model;

import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.base.BaseModel;
import com.example.administrator.everywherretrip.bean.DynamicBean;
import com.example.administrator.everywherretrip.bean.WithPath;
import com.example.administrator.everywherretrip.bean.WithState;
import com.example.administrator.everywherretrip.net.BaseObserver;
import com.example.administrator.everywherretrip.net.HttpUtils;
import com.example.administrator.everywherretrip.net.ResultCallBack;
import com.example.administrator.everywherretrip.net.RxUtils;
import com.example.administrator.everywherretrip.ui.api.MyApi;
import com.umeng.commonsdk.debug.E;
import com.umeng.commonsdk.debug.W;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class DetailsModel extends BaseModel{
    public  void  getData(int banmiId,int page,final ResultCallBack<DynamicBean> callBack){
        MyApi apiserver = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<DynamicBean> path = apiserver.getState(MyApi.param, banmiId, page);
        path.compose(RxUtils.<DynamicBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<DynamicBean>() {
                    @Override
                    public void onNext(DynamicBean dynamicBean) {
                        callBack.onSuccess(dynamicBean);
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

    public  void  getDataStr(int baniId, int page, final ResultCallBack<WithPath> callback){
        MyApi apiserver = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<WithPath> withPathObservable = apiserver.setPath(MyApi.param, baniId, page);
        withPathObservable.compose(RxUtils.<WithPath>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WithPath>() {
                    @Override
                    public void onNext(WithPath withPath) {
                        callback.onSuccess(withPath);
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
}
