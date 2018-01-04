package com.quduo.lottery.ui.mine;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.quduo.lottery.R;
import com.quduo.lottery.mvp.BaseBackMvpFragment;
import com.quduo.lottery.ui.mine.presenter.SettingPresenter;
import com.quduo.lottery.ui.mine.view.ISettingView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 设置
 * Created by scene on 2018/1/4.
 */

public class SettingFragment extends BaseBackMvpFragment<ISettingView, SettingPresenter> implements ISettingView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.gzh)
    TextView gzh;
    @BindView(R.id.kfrx)
    TextView kfrx;
    Unbinder unbinder;
    @BindView(R.id.app_version)
    TextView appVersion;

    public static SettingFragment newInstance() {
        Bundle args = new Bundle();
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        toolbarTitle.setText("设置");
        initToolbarNav(toolbar);
    }

    @Override
    public void initView() {
        super.initView();
        presenter.getAppVersion();
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
    public SettingPresenter initPresenter() {
        return new SettingPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showAppVersion(String appVersionName) {
        try {
            appVersion.setText(appVersionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.gzh)
    public void onClickGzh() {
        try {
            String gzhStr = gzh.getText().toString();
            ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            clipboardManager.setPrimaryClip(ClipData.newPlainText(null, gzhStr));
            ToastUtils.showShort("已复制到剪切板：" + gzhStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
