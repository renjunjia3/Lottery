package com.quduo.lottery.ui.index.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.index.model.SSCConfirmBetModel;
import com.quduo.lottery.ui.index.view.IDLTConfirmBetView;
import com.quduo.lottery.ui.index.view.ISSQConfirmBetView;

/**
 * 确认投注
 * Created by scene on 2017/12/21.
 */

public class DLTConfirmBetPresenter extends BasePresenter<IDLTConfirmBetView> {
    private SSCConfirmBetModel model;

    public DLTConfirmBetPresenter(IDLTConfirmBetView view) {
        this.mView = view;
    }
}
