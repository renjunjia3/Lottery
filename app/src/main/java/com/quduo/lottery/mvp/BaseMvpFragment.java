package com.quduo.lottery.mvp;

import android.os.Bundle;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * Case By:
 * package:wiki.scene.shop.mvp
 * Author：scene on 2017/6/27 16:46
 */

public abstract class BaseMvpFragment<V, T extends BasePresenter<V>> extends SupportFragment {
    public T presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attach((V) this);
    }

    @Override
    public void onDestroy() {
        presenter.dettach();
        super.onDestroy();
    }

    // 实例化presenter
    public abstract T initPresenter();
}
