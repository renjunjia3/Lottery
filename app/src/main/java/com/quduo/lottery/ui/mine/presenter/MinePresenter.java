package com.quduo.lottery.ui.mine.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.mine.model.MineModel;
import com.quduo.lottery.ui.mine.view.IMineView;

/**
 * 我的
 * Created by scene on 2017/12/18.
 */

public class MinePresenter extends BasePresenter<IMineView> {
    private MineModel model;

    public MinePresenter(IMineView view) {
        this.mView = view;
        this.model = new MineModel();
    }
}
