package com.quduo.lottery.ui.login.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.login.model.LoginModel;
import com.quduo.lottery.ui.login.view.ILoginView;

/**
 * 登录
 * Created by scene on 2018/1/22.
 */

public class LoginPresenter extends BasePresenter<ILoginView> {
    private LoginModel model;

    public LoginPresenter(ILoginView view) {
        this.mView = view;
        this.model = new LoginModel();
    }
}
