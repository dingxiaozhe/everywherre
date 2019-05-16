package com.example.administrator.everywherretrip.mvp.model;

import android.text.TextUtils;
import android.util.Log;

import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.base.BaseModel;
import com.example.administrator.everywherretrip.bean.AttenBeanDelete;
import com.example.administrator.everywherretrip.bean.AttenBeanInsert;
import com.example.administrator.everywherretrip.bean.StayBean;
import com.example.administrator.everywherretrip.net.BaseObserver;
import com.example.administrator.everywherretrip.net.HttpUtils;
import com.example.administrator.everywherretrip.net.ResultCallBack;
import com.example.administrator.everywherretrip.net.RxUtils;
import com.example.administrator.everywherretrip.ui.api.MyApi;
import com.example.administrator.everywherretrip.util.ToastUtil;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class StayModel extends BaseModel{
    private static final String TAG = "StayModel";
    public void getModel(int page, final ResultCallBack<StayBean> callBack) {
            MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
            Observable<StayBean> data = myApi.getData(MyApi.param,page);
            data.compose(RxUtils.<StayBean>rxObserableSchedulerHelper())
                    .subscribe(new BaseObserver<StayBean>() {
                        @Override
                        public void onNext(StayBean stayBean) {
                            callBack.onSuccess(stayBean);
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

        /*
        * 关注
         */

        public  void  getPay(int banmiId,final ResultCallBack<AttenBeanInsert> callback){
            MyApi apiserver = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
            Observable<AttenBeanInsert> attenBeanInsertObservable = apiserver.setInsert(MyApi.param, banmiId);
            attenBeanInsertObservable.compose(RxUtils.<AttenBeanInsert>rxObserableSchedulerHelper())
                    .subscribe(new BaseObserver<AttenBeanInsert>() {
                        @Override
                        public void onNext(AttenBeanInsert attenBeanInsert) {
                            callback.onSuccess(attenBeanInsert);
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

        /*
        * 取消关注
         */
        public  void  getSta(int banmiId, final ResultCallBack<AttenBeanDelete> callback){
            final MyApi apiserver = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
            Observable<AttenBeanDelete> attenBeanDeleteObservable = apiserver.setDelete(MyApi.param, banmiId);
            attenBeanDeleteObservable.compose(RxUtils.<AttenBeanDelete>rxObserableSchedulerHelper())
                    .subscribe(new BaseObserver<AttenBeanDelete>() {
                        @Override
                        public void onNext(AttenBeanDelete attenBeanDelete) {
                            callback.onSuccess(attenBeanDelete);
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
