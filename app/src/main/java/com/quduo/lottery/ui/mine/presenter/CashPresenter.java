package com.quduo.lottery.ui.mine.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.mine.model.CashModel;
import com.quduo.lottery.ui.mine.view.ICashView;


/**
 * 提现
 * Created by scene on 2017/11/14.
 */

public class CashPresenter extends BasePresenter<ICashView> {
    private CashModel model;

    public CashPresenter(ICashView cashView) {
        this.mView = cashView;
        model = new CashModel();
    }


}
