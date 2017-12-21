package com.quduo.lottery.ui.index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quduo.lottery.R;
import com.quduo.lottery.mvp.BaseBackMvpFragment;
import com.quduo.lottery.ui.index.presenter.SSQConfirmBetPresenter;
import com.quduo.lottery.ui.index.view.ISSQConfirmBetView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 确认投注
 * Created by scene on 2017/12/21.
 */

public class SSQConfirmBetFragment extends BaseBackMvpFragment<ISSQConfirmBetView, SSQConfirmBetPresenter> implements ISSQConfirmBetView {
    public static final String ARG_LOTTERY_TYPE = "lottery_type";//彩种
    public static final String ARG_PLAY_WAY_TYPE = "play_way_type";//玩法
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    Unbinder unbinder;

    private int lotteryType;
    private int playWayType;

    public static SSQConfirmBetFragment newInstance(int lotteryType, int playWayType) {
        Bundle args = new Bundle();
        args.putInt(ARG_LOTTERY_TYPE, lotteryType);
        args.putInt(ARG_PLAY_WAY_TYPE, playWayType);
        SSQConfirmBetFragment fragment = new SSQConfirmBetFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() == null) {
            onBackPressedSupport();
        } else {
            lotteryType = getArguments().getInt(ARG_LOTTERY_TYPE, 0);
            playWayType = getArguments().getInt(ARG_PLAY_WAY_TYPE, 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ssq_confirm_bet, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        toolbarTitle.setText(getString(R.string.ssq));
        initToolbarNav(toolbar);
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public SSQConfirmBetPresenter initPresenter() {
        return new SSQConfirmBetPresenter(this);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
