package com.quduo.lottery.ui.mine.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.mine.model.IDCardModel;
import com.quduo.lottery.ui.mine.view.IPasswordView;

/**
 * 密码
 * Created by scene on 2018/1/8.
 */

public class PasswordPresenter extends BasePresenter<IPasswordView> {
    private IDCardModel model;

    public PasswordPresenter(IPasswordView view) {
        this.mView = view;
        this.model = new IDCardModel();
    }
}
