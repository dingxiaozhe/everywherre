package com.example.administrator.everywherretrip.mvp.model;

import com.example.administrator.everywherretrip.base.BaseModel;
import com.example.administrator.everywherretrip.bean.ProjectlistBean;
import com.example.administrator.everywherretrip.net.BaseObserver;
import com.example.administrator.everywherretrip.net.HttpUtils;
import com.example.administrator.everywherretrip.net.ResultCallBack;
import com.example.administrator.everywherretrip.net.RxUtils;
import com.example.administrator.everywherretrip.ui.api.MyApi;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class ProjectlistModel extends BaseModel{
    public  void getData(final ResultCallBack<ProjectlistBean> callback){
        MyApi apiserver = HttpUtils.getInstance().getApiserver(MyApi.mainUrl, MyApi.class);
        Observable<ProjectlistBean> getproject = apiserver.getproject(MyApi.param);
        getproject.compose(RxUtils.<ProjectlistBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<ProjectlistBean>() {
                    @Override
                    public void onNext(ProjectlistBean projectlistBean) {
                        callback.onSuccess(projectlistBean);
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
