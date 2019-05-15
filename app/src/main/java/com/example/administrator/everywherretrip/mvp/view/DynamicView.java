package com.example.administrator.everywherretrip.mvp.view;

import com.example.administrator.everywherretrip.base.BaseMvpView;
import com.example.administrator.everywherretrip.bean.DynamicBean;
import com.example.administrator.everywherretrip.bean.WithState;
import com.umeng.commonsdk.debug.D;

public interface DynamicView extends BaseMvpView{
    void setData(DynamicBean dynamicBean);
}
