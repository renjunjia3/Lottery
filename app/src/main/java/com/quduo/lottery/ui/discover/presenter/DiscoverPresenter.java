package com.quduo.lottery.ui.discover.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.discover.model.DiscoverModel;
import com.quduo.lottery.ui.discover.view.IDiscoverView;

/**
 * 发现
 * Created by scene on 2017/12/18.
 */

public class DiscoverPresenter extends BasePresenter<IDiscoverView> {
    private DiscoverModel model;

    public DiscoverPresenter(IDiscoverView view) {
        this.model = new DiscoverModel();
        this.mView = view;
    }
}
