package com.example.administrator.everywherretrip.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.base.BaseApp;
import com.example.administrator.everywherretrip.base.BaseFragment;
import com.example.administrator.everywherretrip.base.Constants;
import com.example.administrator.everywherretrip.mvp.presenter.CodePresenter;
import com.example.administrator.everywherretrip.mvp.view.CodeView;
import com.example.administrator.everywherretrip.ui.activity.LoginActivity;
import com.example.administrator.everywherretrip.ui.activity.MainActivity;
import com.example.administrator.everywherretrip.util.SpUtil;
import com.example.administrator.everywherretrip.widget.IdentifyingCodeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class VerifyFragment extends BaseFragment<CodeView, CodePresenter> implements CodeView {
    private static final String TAG = "VerifyFragment";
    @BindView(R.id.img_start)
    ImageView imgStart;
    @BindView(R.id.tv_send_again)
    TextView tvSendAgain;
    @BindView(R.id.tv_input_code)
    TextView tvInputCode;

    @BindView(R.id.tv_wait)
    TextView tvWait;
    @BindView(R.id.icv)
    IdentifyingCodeView icv;
    Unbinder unbinder;

    private int mTime;

    public static VerifyFragment getNewCode(String code) {
        VerifyFragment codes = new VerifyFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.VERIFY_CODE, code);
        codes.setArguments(bundle);
        return codes;
    }

    @Override
    protected CodePresenter initPresenter() {
        return new CodePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_verify;
    }

    @Override
    public void setCode(String code) {
        if (!TextUtils.isEmpty(code) && tvWait != null) {
            tvWait.setText("验证码:" + code);
        }
    }

    @Override
    public void setErrors(String msg) {
        Log.e(TAG, "setErrors: " + msg);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void initView() {
        //获取传过来的验证码
        String string = getArguments().getString(Constants.VERIFY_CODE);
        setCode(string);
    }

    public void autoLogin(int time) {
        mTime = time;
        if (tvSendAgain != null) {
            if (time != 0) {
                String format = String.format(getResources().getString(R.string.send_newAgain) + "(%ss)", mTime);
                tvSendAgain.setText(format);
                tvSendAgain.setTextColor(getResources().getColor(R.color.c_999));
            } else {
                tvSendAgain.setText(getResources().getString(R.string.send_newAgain));
                tvSendAgain.setTextColor(getResources().getColor(R.color.c_fa6a13));
            }
        }
    }

    @OnClick({R.id.img_start, R.id.tv_send_again})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            case R.id.img_start:
                pop();
                break;
            case R.id.tv_send_again:
                if (mTime == 0) {
                    mPresenter.getCode();
                    mPresenter.getCode();
                    //重新发起倒计时
                    LoginFragment fragment = (LoginFragment) getActivity().getSupportFragmentManager().findFragmentByTag(LoginActivity.TAG);
                    //调用倒计时
                    fragment.countDown();
                }
                break;
        }
    }

    /**
     * 碎片手动弹栈
     */
    private void pop() {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        //弹栈
        manager.popBackStack();
    }

    @Override
    protected void initListener() {
        icv.setOnEditorActionListener(new IdentifyingCodeView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return false;
            }

            @Override
            public void onTextChanged(String s) {
                autoLogin();
            }

        });
    }

    private void autoLogin() {
        if (icv.getTextContent().length() >= 4) {
            //自动登录
            icv.setBackgroundEnter(false);
            tvWait.setText(BaseApp.getRes().getString(R.string.wait_please));

            SpUtil.setParam(Constants.CODEMAIN, tvWait.getText().toString());

            showLoading();
            new Thread() {
                @Override
                public void run() {
                    super.run();

                    try {
                        sleep(3000);

                        startActivity(new Intent(getActivity(), MainActivity.class));
                        getActivity().finish();
                        hideLoading();

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
