package com.quduo.lottery.ui.mine.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.mine.model.IDCardModel;
import com.quduo.lottery.ui.mine.view.IPhoneView;

/**
 * 手机号
 * Created by scene on 2018/1/8.
 */

public class PhonePresenter extends BasePresenter<IPhoneView> {
    private IDCardModel model;

    public PhonePresenter(IPhoneView view) {
        this.mView = view;
        this.model = new IDCardModel();
    }
}
