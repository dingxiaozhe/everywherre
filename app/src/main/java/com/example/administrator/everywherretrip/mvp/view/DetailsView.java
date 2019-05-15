package com.example.administrator.everywherretrip.mvp.view;

import com.example.administrator.everywherretrip.base.BaseMvpView;
import com.example.administrator.everywherretrip.bean.DynamicBean;
import com.example.administrator.everywherretrip.bean.WithPath;
import com.example.administrator.everywherretrip.bean.WithState;

public interface DetailsView extends BaseMvpView{
    void getDataTitle(DynamicBean dynamicBean);
    void getDataStr(WithPath withPath);
}
