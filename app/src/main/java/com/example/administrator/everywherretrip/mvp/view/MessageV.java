package com.example.administrator.everywherretrip.mvp.view;

import com.example.administrator.everywherretrip.base.BaseMvpView;
import com.example.administrator.everywherretrip.bean.MessageBean;

public interface MessageV extends BaseMvpView{
    void setMessage(MessageBean message);
    void setError(String msg);
}
