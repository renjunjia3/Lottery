package com.quduo.lottery.ui.wincode;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quduo.lottery.R;
import com.quduo.lottery.event.StartBrotherEvent;
import com.quduo.lottery.mvp.BaseMainMvpFragment;
import com.quduo.lottery.ui.index.DLTAwardResultFragment;
import com.quduo.lottery.ui.wincode.presenter.WincodePresenter;
import com.quduo.lottery.ui.wincode.view.IWincodeView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * 开奖号码
 * Created by scene on 2017/12/18.
 */

public class WinCodeFragment extends BaseMainMvpFragment<IWincodeView, WincodePresenter> implements IWincodeView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.ptr_layout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.statusView)
    StatusViewLayout statusView;
    Unbinder unbinder;

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
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        toolbarTitle.setText("开奖号码");
        initView();
    }

    @Override
    public void initView() {
        super.initView();
        showContentPage();
    }

    @Override
    public void showLoadingPage() {
        try {
            statusView.showLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showContentPage() {
        try {
            statusView.showContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showErrorPage() {
        try {
            statusView.showFailed(retryListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public WincodePresenter initPresenter() {
        return new WincodePresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    @OnClick(R.id.dlt)
    public void onClickDLT() {
        EventBus.getDefault().post(new StartBrotherEvent(DLTAwardResultFragment.newInstance()));
    }
}
