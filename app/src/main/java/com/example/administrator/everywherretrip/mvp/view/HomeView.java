package com.example.administrator.everywherretrip.mvp.view;

import com.example.administrator.everywherretrip.base.BaseMvpView;
import com.example.administrator.everywherretrip.bean.MainBean;

public interface HomeView extends BaseMvpView{
    void getData(MainBean mainBean);
}
