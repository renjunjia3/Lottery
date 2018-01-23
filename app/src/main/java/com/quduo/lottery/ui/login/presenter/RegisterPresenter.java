package com.quduo.lottery.ui.login.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.login.model.RegisterModel;
import com.quduo.lottery.ui.login.view.IRegisterView;

/**
 * 注册
 * Created by scene on 2018/1/23.
 */

public class RegisterPresenter extends BasePresenter<IRegisterView> {
    private RegisterModel model;

    public RegisterPresenter(IRegisterView view) {
        this.mView = view;
        this.model = new RegisterModel();
    }
}
