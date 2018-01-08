package com.quduo.lottery.ui.mine.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.mine.model.IDCardModel;
import com.quduo.lottery.ui.mine.view.IBankCardView;

/**
 * 银行卡
 * Created by scene on 2018/1/8.
 */

public class BankCardPresenter extends BasePresenter<IBankCardView> {
    private IDCardModel model;

    public BankCardPresenter(IBankCardView view) {
        this.mView = view;
        this.model = new IDCardModel();
    }
}
