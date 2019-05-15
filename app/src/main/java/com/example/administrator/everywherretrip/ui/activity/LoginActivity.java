package com.example.administrator.everywherretrip.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;

import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.base.BaseActivity;
import com.example.administrator.everywherretrip.base.Constants;
import com.example.administrator.everywherretrip.mvp.presenter.EmptyPresenter;
import com.example.administrator.everywherretrip.mvp.view.EmptyView;
import com.example.administrator.everywherretrip.ui.fragment.LoginFragment;
import com.example.administrator.everywherretrip.ui.fragment.VerifyFragment;
import com.example.administrator.everywherretrip.util.SpUtil;
import com.umeng.socialize.UMShareAPI;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {


    @BindView(R.id.mfl)
    FrameLayout mfl;
    private int TYPE_LOGIN = 0;
    public static String TAG = "Fragment_login";
    @Override
    protected EmptyPresenter initPresenter() {
        return mPresenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    /**
     * 启动当前Activiy
     *
     * @param context
     * @param type    如果是0:代表登录界面;1:代表要绑定手机
     */
    public static void startAct(Context context, int type) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(Constants.TYPE, type);
        context.startActivity(intent);
    }
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initView() {
        /*initPer();
        String param = (String) SpUtil.getParam(Constants.TOKEN, "");
        String params = (String) SpUtil.getParam(Constants.CODEMAIN, "");
        if (!(param.equals("")||params.equals(""))){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }*/
        int extra = getIntent().getIntExtra(Constants.TYPE, TYPE_LOGIN);
        LoginFragment newLogin = LoginFragment.newLogin(extra);
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.mfl, newLogin,TAG).commit();
    }

   /* private void initPer() {
        String [] per={Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this,per,100);
    }*/
   @Override
   public void onActivityResult(int requestCode, int resultCode, Intent data) {
       super.onActivityResult(requestCode, resultCode, data);
       UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
   }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //内存泄漏解决方案
        UMShareAPI.get(this).release();

    }
}
