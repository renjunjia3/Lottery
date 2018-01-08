package com.quduo.lottery.ui.mine.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.mine.model.UserInfomationModel;
import com.quduo.lottery.ui.mine.view.IUserInfomationView;

/**
 * 用户信息
 * Created by scene on 2018/1/8.
 */

public class UserInfomationPresenter extends BasePresenter<IUserInfomationView> {
    private UserInfomationModel model;

    public UserInfomationPresenter(IUserInfomationView view) {
        this.mView = view;
        this.model = new UserInfomationModel();
    }
}
