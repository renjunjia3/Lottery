package com.quduo.lottery.ui.index.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.index.model.SSCConfirmBetModel;
import com.quduo.lottery.ui.index.view.IDLTConfirmBetView;
import com.quduo.lottery.ui.index.view.IFC3DConfirmBetView;

/**
 * 确认投注
 * Created by scene on 2017/12/21.
 */

public class FC3DConfirmBetPresenter extends BasePresenter<IFC3DConfirmBetView> {
    private SSCConfirmBetModel model;

    public FC3DConfirmBetPresenter(IFC3DConfirmBetView view) {
        this.mView = view;
    }
}
