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
import com.quduo.lottery.ui.mine.presenter.ZHMXPresenter;
import com.quduo.lottery.ui.mine.view.IZHMXView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 账户明细
 * Created by scene on 2018/1/5.
 */

public class ZHMXFragment extends BaseBackMvpFragment<IZHMXView, ZHMXPresenter> implements IZHMXView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;

    public static ZHMXFragment newInstance() {
        Bundle args = new Bundle();
        ZHMXFragment fragment = new ZHMXFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhmx, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        toolbarTitle.setText("账户明细");
        initToolbarNav(toolbar);
    }

    @Override
    public void initView() {
        super.initView();
        String tabTitle[] = {"全部", "收入", "支出"};
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(ZHMXChildFragment.newInstance(ZHMXChildFragment.TYPE_ALL));
        fragmentList.add(ZHMXChildFragment.newInstance(ZHMXChildFragment.TYPE_GET));
        fragmentList.add(ZHMXChildFragment.newInstance(ZHMXChildFragment.TYPE_PUT));
        tab.addTab(tab.newTab().setText(tabTitle[0]));
        tab.addTab(tab.newTab().setText(tabTitle[1]));
        tab.addTab(tab.newTab().setText(tabTitle[2]));
        viewPager.setAdapter(new ZHMXPageFragmentAdapter(getChildFragmentManager(), tabTitle, fragmentList));
        viewPager.setOffscreenPageLimit(3);
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
    public ZHMXPresenter initPresenter() {
        return new ZHMXPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
