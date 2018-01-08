package com.quduo.lottery.ui.mine.presenter;

import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.mine.model.UpdateNickNameModel;
import com.quduo.lottery.ui.mine.view.IUpdateNickNameView;

/**
 * 修改昵称
 * Created by scene on 2018/1/8.
 */

public class UpdateNickNamePresenter extends BasePresenter<IUpdateNickNameView> {
    private UpdateNickNameModel model;

    public UpdateNickNamePresenter(IUpdateNickNameView view) {
        this.mView = view;
        this.model = new UpdateNickNameModel();
    }
}
