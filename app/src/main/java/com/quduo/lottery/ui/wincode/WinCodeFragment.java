package com.quduo.lottery.ui.wincode;

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
import com.quduo.lottery.ui.wincode.presenter.WincodePresenter;
import com.quduo.lottery.ui.wincode.view.IWincodeView;

/**
 * 开奖号码
 * Created by scene on 2017/12/18.
 */

public class WinCodeFragment extends BaseMainMvpFragment<IWincodeView, WincodePresenter> implements IWincodeView {
    public static WinCodeFragment newInstance() {
        Bundle args = new Bundle();
        WinCodeFragment fragment = new WinCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wincode, container, false);
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
    public WincodePresenter initPresenter() {
        return new WincodePresenter(this);
    }
}
