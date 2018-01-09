package com.quduo.lottery.ui.index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quduo.lottery.R;
import com.quduo.lottery.mvp.BaseBackMvpFragment;
import com.quduo.lottery.ui.index.presenter.FC3DAwardResultPresenter;
import com.quduo.lottery.ui.index.view.IFC3DAwardResultView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * 福彩3D开奖结果
 * Created by scene on 2018/1/8.
 */

public class FC3DAwardResultFragment extends BaseBackMvpFragment<IFC3DAwardResultView, FC3DAwardResultPresenter> implements IFC3DAwardResultView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptr_layout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.statusView)
    StatusViewLayout statusView;
    Unbinder unbinder;

    public static FC3DAwardResultFragment newInstance() {
        Bundle args = new Bundle();
        FC3DAwardResultFragment fragment = new FC3DAwardResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fc3d_award_result, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        toolbarTitle.setText("时时彩 开奖号码");
        initToolbarNav(toolbar);
    }

    @Override
    public void initView() {
        super.initView();
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
    public FC3DAwardResultPresenter initPresenter() {
        return new FC3DAwardResultPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
