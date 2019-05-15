package com.example.administrator.everywherretrip.mvp.model;

import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.base.BaseModel;
import com.example.administrator.everywherretrip.bean.DemoBean;
import com.example.administrator.everywherretrip.bean.LoginInfo;
import com.example.administrator.everywherretrip.net.BaseObserver;
import com.example.administrator.everywherretrip.net.HttpUtils;
import com.example.administrator.everywherretrip.net.ResultCallBack;
import com.example.administrator.everywherretrip.net.RxUtils;
import com.example.administrator.everywherretrip.ui.api.MyApi;
import com.umeng.commonsdk.debug.D;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class MessageModel extends BaseModel{
    public void loginSina(String uid, final ResultCallBack<LoginInfo> callBack){
        MyApi apiserver = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        apiserver.postWeiboLogin(uid)
                .compose(RxUtils.<LoginInfo>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginInfo>() {
                    @Override
                    public void onNext(LoginInfo loginInfo) {
                        if (loginInfo!=null){
                            if (loginInfo.getCode()==MyApi.SUCCESS_CODE){
                                callBack.onSuccess(loginInfo);
                            }else {
                                callBack.onFail(loginInfo.getDesc());
                            }
                        }

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
    public void getVerifyCode(final ResultCallBack<DemoBean> callBack){
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.sBaseUrl, MyApi.class);
        Observable<DemoBean> code = myApi.getVerifyCode();
        code.compose(RxUtils.<DemoBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<DemoBean>() {
                    @Override
                    public void onNext(DemoBean demoBean) {
                        callBack.onSuccess(demoBean);
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
