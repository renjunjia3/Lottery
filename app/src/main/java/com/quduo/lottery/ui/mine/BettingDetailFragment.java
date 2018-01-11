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
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.quduo.lottery.R;
import com.quduo.lottery.mvp.BaseBackMvpFragment;
import com.quduo.lottery.ui.mine.presenter.BettingDetailPresenter;
import com.quduo.lottery.ui.mine.view.IBettingDetailView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * 投注详情
 * Created by scene on 2018/1/11.
 */

public class BettingDetailFragment extends BaseBackMvpFragment<IBettingDetailView, BettingDetailPresenter> implements IBettingDetailView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_menu)
    ImageView toolbarMenu;
    @BindView(R.id.type_image)
    ImageView typeImage;
    @BindView(R.id.type_text)
    TextView typeText;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.fabh)
    TextView fabh;
    @BindView(R.id.copy)
    TextView copy;
    @BindView(R.id.ptr_layout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.statusView)
    StatusViewLayout statusView;
    Unbinder unbinder;

    public static BettingDetailFragment newInstance() {
        Bundle args = new Bundle();
        BettingDetailFragment fragment = new BettingDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_betting_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        toolbarTitle.setText("投注详情");
        toolbarMenu.setImageResource(R.drawable.ic_toolbar_share);
        initToolbarNav(toolbar);
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

    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    @Override
    public BettingDetailPresenter initPresenter() {
        return new BettingDetailPresenter(this);
    }

    @OnClick(R.id.copy)
    public void onClickCopy() {
        try {
            String gzhStr = fabh.getText().toString();
            ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            clipboardManager.setPrimaryClip(ClipData.newPlainText(null, gzhStr));
            ToastUtils.showShort("已复制到剪切板：" + gzhStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
