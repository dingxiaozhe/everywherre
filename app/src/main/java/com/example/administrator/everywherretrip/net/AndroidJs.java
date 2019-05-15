package com.example.administrator.everywherretrip.net;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.example.administrator.everywherretrip.ui.activity.AboutActivity;
import com.example.administrator.everywherretrip.ui.activity.ProjectlistActivity;

public class AndroidJs extends Object{
    Context mContext;

    public AndroidJs(Context context) {
        mContext = context;
    }

    // 定义JS需要调用的方法
// 被JS调用的方法必须加入@JavascriptInterface注解
    @JavascriptInterface
    public void callAndroid(int id) {
        System.out.println(id);
        Intent intent = new Intent(mContext, ProjectlistActivity.class);
        intent.putExtra("id",id);
        mContext.startActivity(intent);
    }

    @JavascriptInterface
    public void callAndroid(String msg,int id) {
        System.out.println(id);
        Intent intent = new Intent(mContext, AboutActivity.class);
        intent.putExtra("id",id);
        mContext.startActivity(intent);
    }
}
