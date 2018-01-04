package com.quduo.lottery.ui.mine.presenter;

import com.blankj.utilcode.util.AppUtils;
import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.mine.model.SettingModel;
import com.quduo.lottery.ui.mine.view.ISettingView;

/**
 * 设置
 * Created by scene on 2018/1/4.
 */

public class SettingPresenter extends BasePresenter<ISettingView> {
    private SettingModel model;

    public SettingPresenter(ISettingView view) {
        this.mView = view;
    }

    public void getAppVersion() {
        try {
            String appVersionName = AppUtils.getAppVersionName();
            mView.showAppVersion(appVersionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
