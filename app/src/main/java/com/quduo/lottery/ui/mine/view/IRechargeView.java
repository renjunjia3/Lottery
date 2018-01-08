package com.quduo.lottery.ui.mine.view;

import android.support.annotation.StringRes;

import com.quduo.lottery.mvp.BaseView;


/**
 * 充值
 * Created by scene on 17-8-16.
 */

public interface IRechargeView extends BaseView {
    void showMessage(String message);

    void showMessage(@StringRes int resId);

}
