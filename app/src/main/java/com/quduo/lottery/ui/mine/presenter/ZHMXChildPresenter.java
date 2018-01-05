package com.quduo.lottery.ui.mine.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.mine.model.ZHMXChildModel;
import com.quduo.lottery.ui.mine.model.ZHMXModel;
import com.quduo.lottery.ui.mine.view.IZHMXChildView;
import com.quduo.lottery.ui.mine.view.IZHMXView;

/**
 * 账户明细
 * Created by scene on 2018/1/5.
 */

public class ZHMXChildPresenter extends BasePresenter<IZHMXChildView> {
    private ZHMXChildModel model;

    public ZHMXChildPresenter(IZHMXChildView view) {
        this.model = new ZHMXChildModel();
        this.mView = view;
    }
}
