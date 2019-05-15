package com.example.administrator.everywherretrip.mvp.view;

import com.example.administrator.everywherretrip.base.BaseMvpView;
import com.example.administrator.everywherretrip.bean.AttenList;

public interface AttenView extends BaseMvpView{
    void setAttenList(AttenList bean);
    void setToast(String str);
}
