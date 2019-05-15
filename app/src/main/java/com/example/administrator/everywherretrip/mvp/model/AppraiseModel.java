package com.example.administrator.everywherretrip.mvp.model;

import com.example.administrator.everywherretrip.base.BaseModel;
import com.example.administrator.everywherretrip.bean.AppraiseBean;
import com.example.administrator.everywherretrip.net.BaseObserver;
import com.example.administrator.everywherretrip.net.HttpUtils;
import com.example.administrator.everywherretrip.net.ResultCallBack;
import com.example.administrator.everywherretrip.net.RxUtils;
import com.example.administrator.everywherretrip.ui.api.MyApi;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class AppraiseModel extends BaseModel{
    public  void getAppraise(int banmiId, int page, final ResultCallBack<AppraiseBean> callback){
        final MyApi apiserver = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<AppraiseBean> appraise = apiserver.getAppraise(MyApi.param, banmiId, page);
        appraise.compose(RxUtils.<AppraiseBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<AppraiseBean>() {
                    @Override
                    public void onNext(AppraiseBean appraiseBean) {
                        callback.onSuccess(appraiseBean);
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
