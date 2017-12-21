package com.quduo.lottery.ui.index.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.index.model.SSCModel;
import com.quduo.lottery.ui.index.view.ISSCView;

/**
 * 时时彩
 * Created by scene on 2017/12/20.
 */

public class SSCPresenter extends BasePresenter<ISSCView> {
    private SSCModel model;

    public SSCPresenter(ISSCView view) {
        this.mView = view;
        model = new SSCModel();
    }

    //机选
    public void clickMachine(){

    }
}
