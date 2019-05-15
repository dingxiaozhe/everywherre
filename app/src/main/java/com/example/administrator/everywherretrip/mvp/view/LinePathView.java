package com.example.administrator.everywherretrip.mvp.view;

import com.example.administrator.everywherretrip.base.BaseMvpView;
import com.example.administrator.everywherretrip.bean.DynamicBean;
import com.example.administrator.everywherretrip.bean.WithPath;
import com.example.administrator.everywherretrip.bean.WithState;

public interface LinePathView extends BaseMvpView{

    void getDataStr(WithPath withPath);
}
