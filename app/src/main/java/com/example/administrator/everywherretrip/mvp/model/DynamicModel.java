package com.example.administrator.everywherretrip.mvp.model;


import com.example.administrator.everywherretrip.base.BaseModel;
import com.example.administrator.everywherretrip.bean.DynamicBean;
import com.example.administrator.everywherretrip.net.BaseObserver;
import com.example.administrator.everywherretrip.net.HttpUtils;
import com.example.administrator.everywherretrip.net.ResultCallBack;
import com.example.administrator.everywherretrip.net.RxUtils;
import com.example.administrator.everywherretrip.ui.api.MyApi;


import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class DynamicModel extends BaseModel{
    public void setData(int banmiId,int page,final ResultCallBack<DynamicBean> callback){
        MyApi apiserver = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<DynamicBean> withStateObservable = apiserver.getState(MyApi.param, banmiId, page);
        withStateObservable.compose(RxUtils.<DynamicBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<DynamicBean>() {
                    @Override
                    public void onNext(DynamicBean dynamicBean) {

                        callback.onSuccess(dynamicBean);
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
