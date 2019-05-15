package com.example.administrator.everywherretrip.mvp.model;

import com.example.administrator.everywherretrip.base.BaseModel;
import com.example.administrator.everywherretrip.base.Constants;
import com.example.administrator.everywherretrip.bean.MessageBean;
import com.example.administrator.everywherretrip.bean.OutData;
import com.example.administrator.everywherretrip.net.BaseObserver;
import com.example.administrator.everywherretrip.net.HttpUtils;
import com.example.administrator.everywherretrip.net.ResultCallBack;
import com.example.administrator.everywherretrip.net.RxUtils;
import com.example.administrator.everywherretrip.ui.api.MyApi;
import com.example.administrator.everywherretrip.util.SpUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

public class MessageM extends BaseModel{
    //修改数据
    public void outMessage(final ResultCallBack<OutData> callBack) {

        String name = (String) SpUtil.getParam(Constants.USERNAME, "name");
        String desc = (String) SpUtil.getParam(Constants.DESC, "desc");
        String gender = (String) SpUtil.getParam(Constants.GENDER, "gender");

        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("userName", name);
        hashMap.put("description", desc);
        hashMap.put("gender", gender);

        Observable<ResponseBody> upDateInfo = myApi.upDateInfo(hashMap, MyApi.param);
        upDateInfo.compose(RxUtils.<ResponseBody>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            OutData data = new Gson().fromJson(string, OutData.class);
                            callBack.onSuccess(data);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
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

    //获取修改的数据
    public void newMessage(final ResultCallBack<MessageBean> callBack) {
        MyApi myApi = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<MessageBean> newDateInfo = myApi.newDateInfo(MyApi.param);
        newDateInfo.compose(RxUtils.<MessageBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<MessageBean>() {
                    @Override
                    public void onNext(MessageBean messageBean) {
                        callBack.onSuccess(messageBean);
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
