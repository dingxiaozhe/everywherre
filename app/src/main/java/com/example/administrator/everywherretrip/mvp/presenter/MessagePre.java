package com.example.administrator.everywherretrip.mvp.presenter;

import com.example.administrator.everywherretrip.base.BasePresenter;
import com.example.administrator.everywherretrip.bean.MessageBean;
import com.example.administrator.everywherretrip.bean.OutData;
import com.example.administrator.everywherretrip.mvp.model.MessageM;
import com.example.administrator.everywherretrip.mvp.view.MessageV;
import com.example.administrator.everywherretrip.net.ResultCallBack;
import com.example.administrator.everywherretrip.util.ToastUtil;

public class MessagePre extends BasePresenter<MessageV>{

    private MessageM model;

    @Override
    protected void initModel() {
        model = new MessageM();
        mModels.add(model);
    }
    public void outData() {
        model.outMessage(new ResultCallBack<OutData>() {
            @Override
            public void onSuccess(OutData bean) {
                ToastUtil.showShort("修改成功,请等待");
                mMvpView.hideLoading();
            }

            @Override
            public void onFail(String msg) {

            }
        });
    }

    public void newData() {
        model.newMessage(new ResultCallBack<MessageBean>() {
            @Override
            public void onSuccess(MessageBean bean) {
                if (bean != null) {
                    if (mMvpView != null) {
                        mMvpView.setMessage(bean);
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                mMvpView.setError(msg);
            }
        });
    }
}
