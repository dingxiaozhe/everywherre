package com.example.administrator.everywherretrip.mvp.model;

import android.service.carrier.CarrierMessagingService;

import com.example.administrator.everywherretrip.base.BaseModel;
import com.example.administrator.everywherretrip.base.BaseMvpView;
import com.example.administrator.everywherretrip.bean.DemoBean;
import com.example.administrator.everywherretrip.net.BaseObserver;
import com.example.administrator.everywherretrip.net.HttpUtils;
import com.example.administrator.everywherretrip.net.ResultCallBack;
import com.example.administrator.everywherretrip.net.RxUtils;
import com.example.administrator.everywherretrip.ui.api.MyApi;
import com.umeng.commonsdk.debug.D;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class Model extends BaseModel {
    public void getVerifyCode(final ResultCallBack<DemoBean> callback){
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.sBaseUrl, MyApi.class);
        Observable<DemoBean> code = myApi.getVerifyCode();
        code.compose(RxUtils.<DemoBean>rxObserableSchedulerHelper())
               .subscribe(new BaseObserver<DemoBean>() {
                   @Override
                   public void onNext(DemoBean demoBean) {
                        callback.onSuccess(demoBean);
                   }

                   @Override
                   public void error(String msg) {

                   }

                   @Override
                   protected void subscribe(Disposable d) {
                        addDisposable(d);
                   }
               });
    }
}
