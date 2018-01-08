package com.quduo.lottery.ui.mine.view;


import com.quduo.lottery.mvp.BaseView;

/**
 * 提现
 * Created by scene on 2017/11/14.
 */

public interface ICashView extends BaseView {
    void showLoadingPage();

    void showContentPage();

    void showFailPage();

    void showMessage(String message);

    int getMoney();

    void applyCashFail(String message);

    String getBankName();

    String getBankUser();

    String getBankAccount();

    String getAlipayUser();

    String getAlipayAccount();

    int getCashType();
}
