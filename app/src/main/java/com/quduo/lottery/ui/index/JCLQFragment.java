package com.quduo.lottery.ui.index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.quduo.lottery.AppConfig;
import com.quduo.lottery.R;
import com.quduo.lottery.mvp.BaseBackMvpFragment;
import com.quduo.lottery.ui.index.popwindow.JCLQMatchPopWindow;
import com.quduo.lottery.ui.index.popwindow.JCLQPlayWayPopWindow;
import com.quduo.lottery.ui.index.popwindow.JCZQMatchPopWindow;
import com.quduo.lottery.ui.index.presenter.JCLQPresenter;
import com.quduo.lottery.ui.index.view.IJCLQView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 竞彩足球
 * Created by scene on 2017/12/26.
 */

public class JCLQFragment extends BaseBackMvpFragment<IJCLQView, JCLQPresenter> implements IJCLQView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_play_way_arrow)
    ImageView toolbarPlayWayArrow;
    @BindView(R.id.toolbar_play_way)
    LinearLayout toolbarPlayWay;
    @BindView(R.id.toolbar_win_result)
    ImageView toolbarWinResult;
    @BindView(R.id.saixuan)
    ImageView saixuan;
    @BindView(R.id.jiajiang)
    ImageView jiajiang;
    @BindView(R.id.toolbar_layout)
    RelativeLayout toolbarLayout;
    Unbinder unbinder;

    private JCLQMatchPopWindow matchPopWindow;

    private JCLQPlayWayPopWindow playWayPopWindow;
    private String[] jclqPlayWays;
    private int jclqPlayWayPosition;

    public static JCLQFragment newInstance() {
        Bundle args = new Bundle();
        JCLQFragment fragment = new JCLQFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jclq, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initToolbarNav(toolbar);
        jclqPlayWays = getResources().getStringArray(R.array.jclq_play_way);
        jclqPlayWayPosition = SPUtils.getInstance().getInt(AppConfig.KEY_JCLQ_DEFAULT_PLAY_WAY_POSITION, 0);
        setToolbarTitle(jclqPlayWayPosition);
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
    public JCLQPresenter initPresenter() {
        return new JCLQPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.toolbar_play_way)
    public void onClickToolBarPlayWay() {
        showJCZQPlayWay();
    }

    private void showJCZQPlayWay() {
        if (playWayPopWindow == null) {
            playWayPopWindow = new JCLQPlayWayPopWindow(getContext());
            playWayPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    toolbarPlayWayArrow.setImageResource(R.drawable.ic_arrow_bottom_toolbar);
                }
            });
            playWayPopWindow.setOnJCZQPlayWayItemClickListener(new JCLQPlayWayPopWindow.OnJCLQPlayWayItemClickListener() {
                @Override
                public void OnJCLQPlayWayItemClick(int position) {
                    setToolbarTitle(position);
                    presenter.changeLayoutView();
                }
            });
        }
        playWayPopWindow.show(toolbar);
        toolbarPlayWayArrow.setImageResource(R.drawable.ic_arrow_top_toolbar);
    }

    private void setToolbarTitle(int titlePosition) {
        jclqPlayWayPosition = titlePosition;
        toolbarTitle.setText(jclqPlayWays[titlePosition]);
    }

    @OnClick(R.id.saixuan)
    public void onClickSaixuan() {
        showMatchPopUpWindow();
    }

    private void showMatchPopUpWindow() {
        if (matchPopWindow == null) {
            matchPopWindow = new JCLQMatchPopWindow(getContext());
            matchPopWindow.setOnJCZQButtonClickListener(new JCLQMatchPopWindow.OnJCLQButtonClickListener() {
                @Override
                public void OnConfirmClick(List<Integer> position) {

                }
            });
        }
        matchPopWindow.show(toolbar);
    }

    @Override
    public int getPlayWayPosition() {
        return jclqPlayWayPosition;
    }

    @Override
    public void showPlayWayType1() {

    }

    @Override
    public void showPlayWayType2() {

    }

    @Override
    public void showPlayWayType3() {

    }

    @Override
    public void showPlayWayType4() {

    }

    @Override
    public void showPlayWayType5() {

    }

    @Override
    public void showPlayWayType6() {

    }
}
