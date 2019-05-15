package com.example.administrator.everywherretrip.mvp.view;

import com.example.administrator.everywherretrip.base.BaseMvpView;
import com.example.administrator.everywherretrip.bean.AboutBean;
import com.example.administrator.everywherretrip.bean.MainBean;
import com.umeng.socialize.media.Base;

public interface AboutView extends BaseMvpView{
    void  getData(AboutBean aboutBean);
    void setToast(String string);
}
