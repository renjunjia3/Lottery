package com.quduo.lottery.ui.mine.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.mine.model.RechargeModel;
import com.quduo.lottery.ui.mine.view.IRechargeView;


/**
 * 充值
 * Created by scene on 17-8-16.
 */

public class RechargePresenter extends BasePresenter<IRechargeView> {
    private IRechargeView rechargeView;
    private RechargeModel model;

    public RechargePresenter(IRechargeView rechargeView) {
        this.mView = rechargeView;
        this.model = new RechargeModel();
    }

}
