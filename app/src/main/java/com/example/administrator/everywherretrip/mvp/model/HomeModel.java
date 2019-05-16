package com.example.administrator.everywherretrip.mvp.model;

import android.text.TextUtils;

import com.example.administrator.everywherretrip.base.BaseModel;
import com.example.administrator.everywherretrip.bean.MainBean;
import com.example.administrator.everywherretrip.net.BaseObserver;
import com.example.administrator.everywherretrip.net.HttpUtils;
import com.example.administrator.everywherretrip.net.ResultCallBack;
import com.example.administrator.everywherretrip.net.RxUtils;
import com.example.administrator.everywherretrip.ui.api.MyApi;
import com.example.administrator.everywherretrip.util.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class HomeModel extends BaseModel{
    public void model(final ResultCallBack<MainBean> callBack) {
        String param = MyApi.param;
            MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
            Observable<MainBean> apiMain = myApi.getMain(MyApi.param);
            apiMain.compose(RxUtils.<MainBean>rxObserableSchedulerHelper())
                    .subscribe(new BaseObserver<MainBean>() {
                        @Override
                        public void onNext(MainBean mainBean) {
                            callBack.onSuccess(mainBean);
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
