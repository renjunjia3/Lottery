package com.quduo.lottery.ui.mine.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.mine.model.MyGiftModel;
import com.quduo.lottery.ui.mine.view.IMyGiftView;

/**
 * 我的优惠券
 * Created by scene on 2018/1/8.
 */

public class MyGiftPresenter extends BasePresenter<IMyGiftView> {
    private MyGiftModel model;

    public MyGiftPresenter(IMyGiftView view) {
        this.mView = view;
        this.model = new MyGiftModel();
    }
}
