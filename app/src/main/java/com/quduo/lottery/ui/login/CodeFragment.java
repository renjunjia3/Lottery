package com.quduo.lottery.ui.login;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hss01248.dialog.StyledDialog;
import com.quduo.lottery.R;
import com.quduo.lottery.mvp.BaseBackMvpFragment;
import com.quduo.lottery.ui.login.presenter.CodePresenter;
import com.quduo.lottery.ui.login.view.ICodeView;
import com.tuo.customview.VerificationCodeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 验证码
 * Created by scene on 2018/1/23.
 */

public class CodeFragment extends BaseBackMvpFragment<ICodeView, CodePresenter> implements ICodeView {
    private static final String ARG_PHONE = "arg_phone";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    Unbinder unbinder;
    @BindView(R.id.phone_number)
    TextView phoneNumber;
    @BindView(R.id.code)
    VerificationCodeView code;
    @BindView(R.id.resend)
    TextView resend;

    private String phone;

    public static CodeFragment newInstance(String phone) {
        Bundle args = new Bundle();
        args.putString(ARG_PHONE, phone);
        CodeFragment fragment = new CodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.phone = getArguments().getString(ARG_PHONE, "");
            if (StringUtils.isTrimEmpty(this.phone)) {
                onBackPressedSupport();
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_code, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        toolbarTitle.setText("填写验证码");
        initToolbarNav(toolbar);
    }

    @Override
    public void initView() {
        super.initView();
        phoneNumber.setText(phone);
        presenter.showCountDownTimer(getContext());
        code.setInputCompleteListener(new VerificationCodeView.InputCompleteListener() {
            @Override
            public void inputComplete() {
                String codeStr = code.getInputContent();
                if (codeStr.length() >= 6) {
                    ToastUtils.showShort("输入的是：" + code.getInputContent());
                }
            }

            @Override
            public void deleteContent() {

            }
        });
    }

    @Override
    public void showLoadingPage() {

    }

    @Override
    public void showContentPage() {

    }

    @Override
    public void showErrorPage() {

    }

    @Override
    public CodePresenter initPresenter() {
        return new CodePresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public String getPhoneNumber() {
        return phone;
    }

    @Override
    public void getCodeSuccess() {
        try {
            presenter.showCountDownTimer(getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getCodeFail(String msg) {
        try {
            ToastUtils.showShort(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showDialog() {
        try {
            StyledDialog.buildLoading("加载中...").show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void hideDialog() {
        try {
            StyledDialog.dismissLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateResendText(String text) {
        try {
            resend.setText(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateResendTextColor(@ColorInt int color) {
        try {
            resend.setTextColor(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateResendClickable(boolean clickable) {
        try {
            resend.setClickable(clickable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.resend)
    public void onClickResend() {
        presenter.getCodeData();
    }
}
