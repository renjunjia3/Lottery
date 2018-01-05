package com.quduo.lottery.ui.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quduo.lottery.R;
import com.quduo.lottery.event.StartBrotherEvent;
import com.quduo.lottery.mvp.BaseMainMvpFragment;
import com.quduo.lottery.ui.mine.presenter.MinePresenter;
import com.quduo.lottery.ui.mine.view.IMineView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.PtrDefaultHandler;
import wiki.scene.loadmore.PtrFrameLayout;

/**
 * 我的
 * Created by scene on 2017/12/18.
 */

public class MineFragment extends BaseMainMvpFragment<IMineView, MinePresenter> implements IMineView {
    @BindView(R.id.edit_user_info)
    TextView editUserInfo;
    @BindView(R.id.avatar)
    ImageView avatar;
    @BindView(R.id.username)
    TextView username;
    @BindView(R.id.setting)
    ImageView setting;
    @BindView(R.id.userinfo)
    ImageView userinfo;
    @BindView(R.id.no_login)
    LinearLayout noLogin;
    @BindView(R.id.has_login)
    LinearLayout hasLogin;
    @BindView(R.id.ptr_layout)
    PtrClassicFrameLayout ptrLayout;
    Unbinder unbinder;

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
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initView();
    }

    @Override
    public void initView() {
        super.initView();
        ptrLayout.setLastUpdateTimeRelateObject(this);
        ptrLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                ptrLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        _mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    ptrLayout.refreshComplete();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }, 1000);
            }
        });
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

    @OnClick(R.id.setting)
    public void onClickSetting() {
        EventBus.getDefault().post(new StartBrotherEvent(SettingFragment.newInstance()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
