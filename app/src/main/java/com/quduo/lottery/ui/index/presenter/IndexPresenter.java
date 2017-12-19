package com.quduo.lottery.ui.index.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.index.model.IndexModel;
import com.quduo.lottery.ui.index.view.IIndexView;

/**
 * 首页
 * Created by scene on 2017/12/18.
 */

public class IndexPresenter extends BasePresenter<IIndexView> {
    private IndexModel model;

    public IndexPresenter(IIndexView view) {
        this.mView = view;
        this.model = new IndexModel();
    }
}
