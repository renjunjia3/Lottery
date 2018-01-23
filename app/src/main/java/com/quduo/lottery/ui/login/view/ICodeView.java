package com.quduo.lottery.ui.login.view;

import android.support.annotation.ColorInt;

import com.quduo.lottery.mvp.BaseView;

/**
 * 验证码
 * Created by scene on 2018/1/23.
 */

public interface ICodeView extends BaseView {
    String getPhoneNumber();

    void getCodeSuccess();

    void getCodeFail(String msg);

    void showDialog();

    void hideDialog();

    void updateResendText(String text);

    void updateResendTextColor(@ColorInt int color);

    void updateResendClickable(boolean clickable);
}
