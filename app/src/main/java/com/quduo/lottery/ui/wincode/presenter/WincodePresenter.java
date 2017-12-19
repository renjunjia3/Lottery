package com.quduo.lottery.ui.wincode.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.wincode.model.WincodeModel;
import com.quduo.lottery.ui.wincode.view.IWincodeView;

/**
 * 开奖结果
 * Created by scene on 2017/12/18.
 */

public class WincodePresenter extends BasePresenter<IWincodeView> {
    private WincodeModel model;

    public WincodePresenter(IWincodeView view) {
        this.mView = view;
        this.model = new WincodeModel();
    }
}
