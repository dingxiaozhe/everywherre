package com.example.administrator.everywherretrip.mvp.model;

import com.example.administrator.everywherretrip.base.BaseModel;
import com.example.administrator.everywherretrip.bean.DynamicBean;
import com.example.administrator.everywherretrip.bean.WithPath;
import com.example.administrator.everywherretrip.bean.WithState;
import com.example.administrator.everywherretrip.net.BaseObserver;
import com.example.administrator.everywherretrip.net.HttpUtils;
import com.example.administrator.everywherretrip.net.ResultCallBack;
import com.example.administrator.everywherretrip.net.RxUtils;
import com.example.administrator.everywherretrip.ui.api.MyApi;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class LinePathModel extends BaseModel{
  /*  public  void  getData(int banmiId,int page,final ResultCallBack<WithState> callBack){
        MyApi apiserver = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<WithState> path = apiserver.setState(MyApi.param, banmiId, page);
        path.compose(RxUtils.<WithState>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WithState>() {
                    @Override
                    public void onNext(WithState withState) {

                        callBack.onSuccess(withState);
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
    }*/

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
