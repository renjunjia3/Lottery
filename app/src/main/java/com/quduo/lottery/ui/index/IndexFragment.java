package com.quduo.lottery.ui.index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quduo.lottery.R;
import com.quduo.lottery.base.BaseMainFragment;
import com.quduo.lottery.mvp.BaseMainMvpFragment;
import com.quduo.lottery.ui.index.presenter.IndexPresenter;
import com.quduo.lottery.ui.index.view.IIndexView;

/**
 * 主页
 * Created by scene on 2017/12/18.
 */

public class IndexFragment extends BaseMainMvpFragment<IIndexView, IndexPresenter> implements IIndexView {
    public static IndexFragment newInstance() {
        Bundle args = new Bundle();
        IndexFragment fragment = new IndexFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, container, false);
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
    public IndexPresenter initPresenter() {
        return new IndexPresenter(this);
    }
}
