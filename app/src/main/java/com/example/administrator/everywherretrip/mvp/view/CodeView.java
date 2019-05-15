package com.example.administrator.everywherretrip.mvp.view;

import com.example.administrator.everywherretrip.base.BaseMvpView;

public interface CodeView extends BaseMvpView{
    void setCode(String code);
    void setErrors(String msg);
}
