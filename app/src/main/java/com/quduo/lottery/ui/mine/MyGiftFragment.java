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
import android.widget.ImageView;
import android.widget.TextView;

import com.quduo.lottery.R;
import com.quduo.lottery.mvp.BaseBackMvpFragment;
import com.quduo.lottery.ui.mine.adapter.MyGiftPageFragmentAdapter;
import com.quduo.lottery.ui.mine.presenter.MyGiftPresenter;
import com.quduo.lottery.ui.mine.view.IMyGiftView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 我的优惠券
 * Created by scene on 2018/1/8.
 */

public class MyGiftFragment extends BaseBackMvpFragment<IMyGiftView, MyGiftPresenter> implements IMyGiftView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    Unbinder unbinder;
    @BindView(R.id.toolbar_menu)
    ImageView toolbarMenu;

    public static MyGiftFragment newInstance() {
        Bundle args = new Bundle();
        MyGiftFragment fragment = new MyGiftFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_gift, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        toolbarTitle.setText("我的优惠券");
        toolbarMenu.setImageResource(R.drawable.ic_toolbar_question);
        initToolbarNav(toolbar);
    }

    @Override
    public void initView() {
        super.initView();
        String tabTitle[] = {"未使用", "已使用", "已过期"};
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(MyGiftChildFragment.newInstance(MyGiftChildFragment.TYPE_USED));
        fragmentList.add(MyGiftChildFragment.newInstance(MyGiftChildFragment.TYPE_UNUSE));
        fragmentList.add(MyGiftChildFragment.newInstance(MyGiftChildFragment.TYPE_OVERDUE));
        tab.addTab(tab.newTab().setText(tabTitle[0]));
        tab.addTab(tab.newTab().setText(tabTitle[1]));
        tab.addTab(tab.newTab().setText(tabTitle[2]));
        viewPager.setAdapter(new MyGiftPageFragmentAdapter(getChildFragmentManager(), tabTitle, fragmentList));
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
    public MyGiftPresenter initPresenter() {
        return new MyGiftPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
