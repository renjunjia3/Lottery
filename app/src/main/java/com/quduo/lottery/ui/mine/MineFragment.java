package com.quduo.lottery.ui.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quduo.lottery.R;
import com.quduo.lottery.mvp.BaseMainMvpFragment;
import com.quduo.lottery.ui.mine.presenter.MinePresenter;
import com.quduo.lottery.ui.mine.view.IMineView;

/**
 * 我的
 * Created by scene on 2017/12/18.
 */

public class MineFragment extends BaseMainMvpFragment<IMineView, MinePresenter> implements IMineView {
    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
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
    public MinePresenter initPresenter() {
        return new MinePresenter(this);
    }
}
