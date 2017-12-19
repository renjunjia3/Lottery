package com.quduo.lottery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.quduo.lottery.event.StartBrotherEvent;
import com.quduo.lottery.event.TabSelectedEvent;
import com.quduo.lottery.ui.discover.DiscoverFragment;
import com.quduo.lottery.ui.index.IndexFragment;
import com.quduo.lottery.ui.mine.MineFragment;
import com.quduo.lottery.ui.wincode.WinCodeFragment;
import com.quduo.lottery.widgets.bottombar.BottomBar;
import com.quduo.lottery.widgets.bottombar.BottomBarTab;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 主Fragment
 * Created by scene on 2017/12/18.
 */

public class MainFragment extends SupportFragment {
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOUR = 3;

    @BindView(R.id.fl_tab_container)
    FrameLayout flTabContainer;
    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    Unbinder unbinder;


    private SupportFragment[] mFragments = new SupportFragment[4];
    private List<String> tabNames = new ArrayList<>();

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SupportFragment firstFragment = findChildFragment(IndexFragment.class);
        tabNames.add(getString(R.string.tab_index));
        tabNames.add(getString(R.string.tab_wincode));
        tabNames.add(getString(R.string.tab_discover));
        tabNames.add(getString(R.string.tab_mine));
        if (firstFragment == null) {
            mFragments[FIRST] = IndexFragment.newInstance();
            mFragments[SECOND] = WinCodeFragment.newInstance();
            mFragments[THIRD] = DiscoverFragment.newInstance();
            mFragments[FOUR] = MineFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOUR]);
        } else {
            // 这里我们需要拿到mFragments的引用,也可以通过getChildFragmentManager.findFragmentByTag自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findChildFragment(WinCodeFragment.class);
            mFragments[THIRD] = findChildFragment(DiscoverFragment.class);
            mFragments[FOUR] = findChildFragment(MineFragment.class);
        }
        initView();
    }

    private void initView() {
        bottomBar
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_bottombar_index_d, R.drawable.ic_bottombar_index_s, getString(R.string.tab_index)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_bottombar_wincode_d, R.drawable.ic_bottombar_wincode_s, getString(R.string.tab_wincode)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_bottombar_discover_d, R.drawable.ic_bottombar_discover_s, getString(R.string.tab_discover)))
                .addItem(new BottomBarTab(_mActivity, R.drawable.ic_bottombar_mine_d, R.drawable.ic_bottombar_mine_s, getString(R.string.tab_mine)));


        bottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
            }
        });
    }

    @Subscribe
    public void startBrother(StartBrotherEvent event) {
        start(event.targetFragment);
    }

    @Subscribe
    public void toIndexPage(TabSelectedEvent event) {
        bottomBar.setCurrentItem(event.position);
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
        unbinder.unbind();
    }
}
