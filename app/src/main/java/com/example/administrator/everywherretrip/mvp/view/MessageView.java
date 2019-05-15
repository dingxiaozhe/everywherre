package com.example.administrator.everywherretrip.mvp.view;

import android.app.Activity;

import com.example.administrator.everywherretrip.base.BaseMvpView;

public interface MessageView extends BaseMvpView{
    String getPhone();
    Activity getAct();
    void goMainActivity();
    void toastShort(String string);
    void setCode(String code);
}
