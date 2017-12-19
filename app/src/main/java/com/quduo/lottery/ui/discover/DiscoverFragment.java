package com.quduo.lottery.ui.discover;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quduo.lottery.R;
import com.quduo.lottery.mvp.BaseMainMvpFragment;
import com.quduo.lottery.ui.discover.presenter.DiscoverPresenter;
import com.quduo.lottery.ui.discover.view.IDiscoverView;

/**
 * 发现
 * Created by scene on 2017/12/18.
 */

public class DiscoverFragment extends BaseMainMvpFragment<IDiscoverView, DiscoverPresenter> implements IDiscoverView {
    public static DiscoverFragment newInstance() {
        Bundle args = new Bundle();
        DiscoverFragment fragment = new DiscoverFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        return view;
    }

    @Override
    public void showLoadingPage() {

    }

    @Override
    public void showContentPage() {

    }

    @Override
    public void showErrorPage() {

    }

    @Override
    public DiscoverPresenter initPresenter() {
        return new DiscoverPresenter(this);
    }
}
