package com.example.administrator.everywherretrip.mvp.view;

import com.example.administrator.everywherretrip.base.BaseMvpView;
import com.example.administrator.everywherretrip.bean.StayBean;

public interface StayView extends BaseMvpView{
    void setList(StayBean stayBean);
    void setToast(String str);
}
