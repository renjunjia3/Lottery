package com.quduo.lottery.ui.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quduo.lottery.R;
import com.quduo.lottery.mvp.BaseBackMvpFragment;
import com.quduo.lottery.ui.mine.adapter.ZHMXPageFragmentAdapter;
import com.quduo.lottery.ui.mine.presenter.BettingRecordPresenter;
import com.quduo.lottery.ui.mine.view.IBettingRecordView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 投注记录
 * Created by scene on 2018/1/8.
 */

public class BettingRecordFragment extends BaseBackMvpFragment<IBettingRecordView, BettingRecordPresenter> implements IBettingRecordView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;

    public static BettingRecordFragment newInstance() {
        Bundle args = new Bundle();
        BettingRecordFragment fragment = new BettingRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_betting_recored, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        toolbarTitle.setText("投注记录");
        initToolbarNav(toolbar);
    }

    @Override
    public void initView() {
        super.initView();
        String tabTitle[] = {"全部", "已中奖", "待开奖", "未成功"};
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(BettingRecordChildFragment.newInstance(BettingRecordChildFragment.TYPE_ALL));
        fragmentList.add(BettingRecordChildFragment.newInstance(BettingRecordChildFragment.TYPE_WIN));
        fragmentList.add(BettingRecordChildFragment.newInstance(BettingRecordChildFragment.TYPE_WAIT));
        fragmentList.add(BettingRecordChildFragment.newInstance(BettingRecordChildFragment.TYPE_FAIL));
        tab.addTab(tab.newTab().setText(tabTitle[0]));
        tab.addTab(tab.newTab().setText(tabTitle[1]));
        tab.addTab(tab.newTab().setText(tabTitle[2]));
        tab.addTab(tab.newTab().setText(tabTitle[3]));
        viewPager.setAdapter(new ZHMXPageFragmentAdapter(getChildFragmentManager(), tabTitle, fragmentList));
        viewPager.setOffscreenPageLimit(4);
        tab.setupWithViewPager(viewPager);
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
    public BettingRecordPresenter initPresenter() {
        return new BettingRecordPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
