package com.quduo.lottery.ui.mine.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.mine.model.ZHMXModel;
import com.quduo.lottery.ui.mine.view.IZHMXView;

/**
 * 账户明细
 * Created by scene on 2018/1/5.
 */

public class ZHMXPresenter extends BasePresenter<IZHMXView> {
    private ZHMXModel model;

    public ZHMXPresenter(IZHMXView view) {
        this.model = new ZHMXModel();
        this.mView = view;
    }

}
