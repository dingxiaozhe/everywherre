package com.example.administrator.everywherretrip.ui.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.base.BaseFragment;
import com.example.administrator.everywherretrip.base.Constants;
import com.example.administrator.everywherretrip.mvp.presenter.MessagePresenter;
import com.example.administrator.everywherretrip.mvp.view.MessageView;
import com.example.administrator.everywherretrip.ui.activity.MainActivity;
import com.example.administrator.everywherretrip.ui.activity.WebViewActivity;
import com.example.administrator.everywherretrip.util.ToastUtil;
import com.example.administrator.everywherretrip.util.Tools;
import com.umeng.socialize.bean.SHARE_MEDIA;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment<MessageView, MessagePresenter> implements MessageView {
    @BindView(R.id.tv_hello)
    TextView tvHello;
    @BindView(R.id.tv_isLogin)
    TextView tvIsLogin;
    @BindView(R.id.iv_phone)
    ImageView ivPhone;
    @BindView(R.id.tv_86)
    TextView tv86;
    @BindView(R.id.iv_select)
    ImageView ivSelect;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.btn_Verification)
    Button btnVerification;
    @BindView(R.id.ll_phone)
    LinearLayout llPhone;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.ll_or)
    LinearLayout llOr;
    @BindView(R.id.imgQQ)
    ImageButton imgQQ;
    @BindView(R.id.imgWeChat)
    ImageView imgWeChat;
    @BindView(R.id.imgWB)
    ImageButton imgWB;
    @BindView(R.id.tv_agreement)
    TextView tvAgreement;
    Unbinder unbinder;

//5cd441130cafb21273000a8d

    private int type;
    private static int COUNT_DOWN_TIME = 10;
    private int time = COUNT_DOWN_TIME;
    private String mVerifyCode;
    private Handler mHandler;
    private VerifyFragment mCodes;

    //因为登录和绑定手机号码是用的一个碎片,所以需要使用type隐藏和显示某一些view
    //如果是0:代表登录界面;1:代表要绑定手机
    public static LoginFragment newLogin(int type) {
        LoginFragment login = new LoginFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.TYPE, type);
        login.setArguments(bundle);
        return login;
    }

    @Override
    protected MessagePresenter initPresenter() {

        return new MessagePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }



    @Override
    protected void initView() {
        getArgumentsData();
        protocol();
        initI();
    }

    private void getArgumentsData() {
        Bundle bundle = getArguments();
        type = bundle.getInt(Constants.TYPE);
    }

    //设置图文混排
    private void protocol() {
        //SpannableString ss = new SpannableString(getResources().getString(R.string.agree_protocol));
        //通过设置不同的span达到不同的显示效果
         /*  what：对SpannableString进行润色的各种Span；
            start：需要润色文字段开始的下标,包含；
            end：需要润色文字段结束的下标,结束应用指定Span的位置，不包含；
            flags：Spannable.SPAN_EXCLUSIVE_EXCLUSIVE：前后都不包括（
            在标志位【start，end）前后添加文字，新添加的文字不会有任何设置的属性）
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE ：前面不包括，后面包括。
            （在标志位【start，end）前添加文字，新添加的文字不会有任何设置的属性，后边的添加的文字会带有设置的what属性）
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE ：前面包括，后面不包括。
            （在标志位【start，end）后添加文字，新添加的文字不会有任何设置的属性，前边边的添加的文字会带有设置的what属性）
            Spannable.SPAN_INCLUSIVE_INCLUSIVE ：前后都包括。前后都不包括
            （在标志位【start，end）前后添加文字，新添加的文字会有设置的属性）
          */

            /* ss.setSpan(what,0,16, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            mTvProtocol.setText(ss);*/

        //17个字,0-16
         /*   SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(getResources().getString(R.string.agree_protocol));
            //SPAN_INCLUSIVE_EXCLUSIVE 1-16,插入的位置1-15 可以渲染,16就不会被渲染
            //SPAN_INCLUSIVE_INCLUSIVE 1-16,插入的位置1-16 都可以渲染
            //SPAN_EXCLUSIVE_INCLUSIVE 1-16,插入的位置2-16 都可以渲染
            spannableStringBuilder.setSpan(what,1,16, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            spannableStringBuilder.insert(2,"12");
            mTvProtocol.setText(spannableStringBuilder);*/

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(getResources().getString(R.string.agree_protocol));
        //设置文字的点击事件
        ClickableSpan span = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                //跳转页面,webview展示协议
                //webView有很多坑,所以我们不直接用webView
                WebViewActivity.startAct(getActivity());
            }
        };
        stringBuilder.setSpan(span, 13, 17, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //设置下划线
        UnderlineSpan underlineSpan = new UnderlineSpan();
        stringBuilder.setSpan(underlineSpan, 13, 17, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //设置前景色
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.c_fa6a13));
        stringBuilder.setSpan(colorSpan, 13, 17, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        //设置这个ClickableSpan才能启动
        tvAgreement.setMovementMethod(LinkMovementMethod.getInstance());
        tvAgreement.setText(stringBuilder);
    }

    //设置验证码按钮
    private void initI() {
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                switchBtnState(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void switchBtnState(CharSequence s) {
        if (!TextUtils.isEmpty(s)) {
            btnVerification.setBackgroundResource(R.drawable.shape_ok);
        } else {
            btnVerification.setBackgroundResource(R.drawable.shape_no);
        }
    }

    @OnClick({R.id.imgQQ, R.id.imgWeChat, R.id.imgWB, R.id.btn_Verification})
    public void okClick(View view) {
        switch (view.getId()) {
            case R.id.imgQQ:
                mPresenter.oauthLogin(SHARE_MEDIA.QQ);
                break;
            case R.id.imgWeChat:
                mPresenter.oauthLogin(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.imgWB:
                mPresenter.oauthLogin(SHARE_MEDIA.SINA);
                break;
            case R.id.btn_Verification:
                getCode();
                AddVerifyFragment();
                timer();
                break;
        }
    }

    //避免被多次执行倒计时
    private void timer() {
        if (time > 0 && time < COUNT_DOWN_TIME) {
            return;
        } else {
            countDown();
        }
    }

    //倒计时,如果倒计时已在运行中,就不走
    public void countDown() {
        if (mHandler == null) {
            mHandler = new Handler();
        }
        //倒计时重新获取验证码
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (time <= 0) {
                    time = COUNT_DOWN_TIME;
                    return;
                }

                time--;
                if (mCodes != null) {
                    mCodes.autoLogin(time);
                }
                countDown();
            }
        }, 1000);
    }

    //判断是否重新获取验证码
    private void getCode() {
        if (time > 0 && time < COUNT_DOWN_TIME - 1) {
            //正在处于倒计时中
            return;
        } else {
            mVerifyCode = "";
            mPresenter.getCode();
        }
    }

    //添加回退栈并传值
    private void AddVerifyFragment() {
        String phone = getPhone();
        if (TextUtils.isEmpty(phone)) {
            return;
        } else {
            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            //添加回退栈
            transaction.addToBackStack(null);
            mCodes = VerifyFragment.getNewCode(mVerifyCode);
            transaction.add(R.id.mfl, mCodes).commit();
            //关闭软键盘
            Tools.closeKeyBoard(getActivity());
        }
    }


    @Override
    public Activity getAct() {
        return getActivity();
    }

    @Override
    public void goMainActivity() {
        MainActivity.startAct(getContext());
        getActivity().finish();
    }

    @Override
    public void toastShort(String string) {
        ToastUtil.showShort(string);
    }

    @Override
    public void setCode(String c) {
        this.mVerifyCode = c;
        if (mVerifyCode != null) {
            mCodes.setCode(c);
        }
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }




    @Override
    public String getPhone() {

        return etPhone.getText().toString().trim();
    }
}
