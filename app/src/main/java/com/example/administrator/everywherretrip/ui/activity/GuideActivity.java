package com.example.administrator.everywherretrip.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.everywherretrip.R;
import com.example.administrator.everywherretrip.base.BaseActivity;
import com.example.administrator.everywherretrip.base.BaseFragment;
import com.example.administrator.everywherretrip.base.Constants;
import com.example.administrator.everywherretrip.mvp.presenter.EmptyPresenter;
import com.example.administrator.everywherretrip.mvp.view.EmptyView;
import com.example.administrator.everywherretrip.ui.fragment.FragmentGuide;
import com.example.administrator.everywherretrip.util.SpUtil;
import com.example.administrator.everywherretrip.widget.PreviewIndicator;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {


    @BindView(R.id.mTool_guide)
    Toolbar mToolGuide;
    @BindView(R.id.mVp_guide)
    ViewPager mVpGuide;
    @BindView(R.id.mTv_guide)
    TextView mTvGuide;

    @BindView(R.id.mBtn_guide)
    Button mBtnGuide;
    private static final String TAG = "GuideActivity";
    @BindView(R.id.mPir)
    PreviewIndicator mPir;

    private ArrayList<BaseFragment> mList;
    //private PreviewIndicator mpreviewIndicator;

    @Override
    protected EmptyPresenter initPresenter() {
        return mPresenter;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guide;
    }


    @Override
    protected void initView() {

        boolean param = (boolean) SpUtil.getParam(Constants.BOOLEAN, false);
        if (param) {
            startActivity(new Intent(GuideActivity.this, LoginActivity.class));
            finish();
        }

        mToolGuide.setTitle("");
        setSupportActionBar(mToolGuide);
        initTextColor();
        mList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            FragmentGuide guide = new FragmentGuide();
            guide.setImg(i);
            mList.add(guide);
        }
        //设置
        mPir.setNumbers(mList.size());
        FragmentManager fm = getSupportFragmentManager();
        mVpGuide.setAdapter(new FragmentPagerAdapter(fm) {
            @Override
            public Fragment getItem(int i) {
                return mList.get(i);
            }

            @Override
            public int getCount() {
                return mList.size();
            }
        });
        //监听viewPage页面的切换
        mVpGuide.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mPir.setSelected(i);
                if (i == 0) {
                    mTvGuide.setText(R.string.pager_te);
                    initTextColor();
                } else if (i == 1) {
                    mPir.setVisibility(View.VISIBLE);
                    mBtnGuide.setVisibility(View.INVISIBLE);
                    mTvGuide.setText(R.string.pager_tv2);
                    initTextColor();
                } else {
                    mPir.setVisibility(View.INVISIBLE);
                    mBtnGuide.setVisibility(View.VISIBLE);
                    mTvGuide.setText(R.string.pager_tv3);
                    initTextColor();
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


    }

    //设置图文混排
    private void initTextColor() {

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(mTvGuide.getText());
        //设置前景色
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.c_fa6a13));
        stringBuilder.setSpan(colorSpan, (mTvGuide.length() - 4), mTvGuide.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        mTvGuide.setText(stringBuilder);

    }

    @OnClick(R.id.mBtn_guide)
    public void onViewClicked() {
        SpUtil.setParam(Constants.BOOLEAN, true);
        startActivity(new Intent(GuideActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
