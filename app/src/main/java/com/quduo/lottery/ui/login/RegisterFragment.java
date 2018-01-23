package com.quduo.lottery.ui.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.hss01248.dialog.StyledDialog;
import com.quduo.lottery.R;
import com.quduo.lottery.mvp.BaseBackMvpFragment;
import com.quduo.lottery.ui.login.presenter.RegisterPresenter;
import com.quduo.lottery.ui.login.view.IRegisterView;
import com.quduo.lottery.widgets.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 注册
 * Created by scene on 2018/1/23.
 */

public class RegisterFragment extends BaseBackMvpFragment<IRegisterView, RegisterPresenter> implements IRegisterView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.phone_number)
    ClearEditText phoneNumber;
    @BindView(R.id.next)
    TextView next;
    Unbinder unbinder;

    public static RegisterFragment newInstance() {
        Bundle args = new Bundle();
        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        toolbarTitle.setText("注册");
        initToolbarNav(toolbar);
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
    public RegisterPresenter initPresenter() {
        return new RegisterPresenter(this);
    }

    @OnClick(R.id.next)
    public void onClickNext() {
        String phoneNumberStr = getPhoneNumber();
        if (RegexUtils.isMobileSimple(phoneNumberStr)) {
            presenter.getCodeData();
        } else {
            ToastUtils.showShort("请输入正确的手机号");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber.getText().toString().trim();
    }

    @Override
    public void getCodeSuccess() {
        try {
            start(CodeFragment.newInstance(getPhoneNumber()));
            hideSoftInput();
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
}
