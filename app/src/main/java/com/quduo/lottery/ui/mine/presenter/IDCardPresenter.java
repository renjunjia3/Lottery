package com.quduo.lottery.ui.mine.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.mine.model.IDCardModel;
import com.quduo.lottery.ui.mine.view.IIDCardView;

/**
 * 身份证
 * Created by scene on 2018/1/8.
 */

public class IDCardPresenter extends BasePresenter<IIDCardView> {
    private IDCardModel model;

    public IDCardPresenter(IIDCardView view) {
        this.mView = view;
        this.model = new IDCardModel();
    }
}
