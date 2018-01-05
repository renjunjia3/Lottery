package com.quduo.lottery.ui.index.view;

import com.quduo.lottery.mvp.BaseView;

import java.util.List;

/**
 * 首页
 * Created by scene on 2017/12/18.
 */

public interface IIndexView extends BaseView {
    void bindNoticeInfo();

    void bindBanner(List<String> bannerImages);
}
