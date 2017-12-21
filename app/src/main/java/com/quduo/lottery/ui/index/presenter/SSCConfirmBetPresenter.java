package com.quduo.lottery.ui.index.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.index.model.SSCConfirmBetModel;
import com.quduo.lottery.ui.index.view.ISSCConfirmBetView;

/**
 * 确认投注
 * Created by scene on 2017/12/21.
 */

public class SSCConfirmBetPresenter extends BasePresenter<ISSCConfirmBetView> {
    private SSCConfirmBetModel model;

    public SSCConfirmBetPresenter(ISSCConfirmBetView view) {
        this.mView = view;
    }
}
