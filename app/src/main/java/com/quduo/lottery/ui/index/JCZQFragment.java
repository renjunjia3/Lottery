package com.quduo.lottery.ui.index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.blankj.utilcode.util.SizeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.quduo.lottery.AppConfig;
import com.quduo.lottery.R;
import com.quduo.lottery.itemDecoration.SpacesItemDecoration;
import com.quduo.lottery.mvp.BaseBackMvpFragment;
import com.quduo.lottery.ui.index.adapter.jczq.JCZQType1Adapter;
import com.quduo.lottery.ui.index.adapter.jczq.JCZQType2Adapter;
import com.quduo.lottery.ui.index.adapter.jczq.JCZQType3Adapter;
import com.quduo.lottery.ui.index.adapter.jczq.JCZQType4Adapter;
import com.quduo.lottery.ui.index.entity.JCZQType1ContentInfo;
import com.quduo.lottery.ui.index.entity.JCZQType1HeaderInfo;
import com.quduo.lottery.ui.index.popwindow.JCZQMatchPopWindow;
import com.quduo.lottery.ui.index.popwindow.JCZQMenuPopWindow;
import com.quduo.lottery.ui.index.popwindow.JCZQMorePlayWayDialog;
import com.quduo.lottery.ui.index.popwindow.JCZQPlayWayPopWindow;
import com.quduo.lottery.ui.index.popwindow.JCZQScoreDialog;
import com.quduo.lottery.ui.index.presenter.JCZQPresenter;
import com.quduo.lottery.ui.index.view.IJCZQView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.PtrDefaultHandler;
import wiki.scene.loadmore.PtrFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * 竞彩足球
 * Created by scene on 2017/12/26.
 */

public class JCZQFragment extends BaseBackMvpFragment<IJCZQView, JCZQPresenter> implements IJCZQView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.toolbar_play_way_arrow)
    ImageView toolbarPlayWayArrow;
    @BindView(R.id.toolbar_play_way)
    LinearLayout toolbarPlayWay;
    @BindView(R.id.toolbar_menu)
    ImageView toolbarMenu;
    @BindView(R.id.saixuan)
    ImageView saixuan;
    @BindView(R.id.jiajiang)
    ImageView jiajiang;
    @BindView(R.id.toolbar_layout)
    RelativeLayout toolbarLayout;
    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptr_layout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.statusView)
    StatusViewLayout statusView;

    private List<MultiItemEntity> list1 = new ArrayList<>();
    private List<MultiItemEntity> list2 = new ArrayList<>();
    private List<MultiItemEntity> list3 = new ArrayList<>();
    private List<MultiItemEntity> list4 = new ArrayList<>();

    private JCZQPlayWayPopWindow playWayPopWindow;
    private String[] jczqPlayWays;
    private int jczqPlayWayPosition;

    private JCZQMenuPopWindow menuPopWindow;

    private JCZQMatchPopWindow matchPopWindow;

    private JCZQMorePlayWayDialog morePlayWayDialog;
    private JCZQScoreDialog scoreDialog;

    private JCZQType1Adapter type1Adapter;
    private JCZQType2Adapter type2Adapter;
    private JCZQType3Adapter type3Adapter;
    private JCZQType4Adapter type4Adapter;

    public static JCZQFragment newInstance() {
        Bundle args = new Bundle();
        JCZQFragment fragment = new JCZQFragment();
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
        View view = inflater.inflate(R.layout.fragment_jczq, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initToolbarNav(toolbar);
        jczqPlayWays = getResources().getStringArray(R.array.jczq_play_way);
        jczqPlayWayPosition = SPUtils.getInstance().getInt(AppConfig.KEY_JCZQ_DEFAULT_PLAY_WAY_POSITION, 0);
        setToolbarTitle(jczqPlayWayPosition);
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
                                ptrLayout.refreshComplete();
                            }
                        });

                    }
                }, 2000);
            }
        });
        showContentPage();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(SizeUtils.dp2px(10)));

        presenter.changeLayoutView();
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
    public JCZQPresenter initPresenter() {
        return new JCZQPresenter(this);
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

    private void showJCZQPlayWay() {
        if (playWayPopWindow == null) {
            playWayPopWindow = new JCZQPlayWayPopWindow(getContext());
            playWayPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    toolbarPlayWayArrow.setImageResource(R.drawable.ic_arrow_bottom_toolbar);
                }
            });
            playWayPopWindow.setOnJCZQPlayWayItemClickListener(new JCZQPlayWayPopWindow.OnJCZQPlayWayItemClickListener() {
                @Override
                public void OnJCZQPlayWayItemClick(int position) {
                    setToolbarTitle(position);
                    presenter.changeLayoutView();
                }
            });
        }
        playWayPopWindow.show(toolbar);
        toolbarPlayWayArrow.setImageResource(R.drawable.ic_arrow_top_toolbar);
    }

    private void setToolbarTitle(int titlePosition) {
        jczqPlayWayPosition = titlePosition;
        toolbarTitle.setText(jczqPlayWays[titlePosition]);
    }

    @OnClick(R.id.toolbar_play_way)
    public void onClickToolBarPlayWay() {
        showJCZQPlayWay();
    }

    public void showMenuPopUpWindow() {
        if (menuPopWindow == null) {
            menuPopWindow = new JCZQMenuPopWindow(getContext());
        }
        menuPopWindow.show(toolbarMenu);
    }

    @OnClick(R.id.toolbar_menu)
    public void onClickToolbarMenu() {
        showMenuPopUpWindow();
    }

    @OnClick(R.id.saixuan)
    public void onClickSaixuan() {
        showMatchPopUpWindow();
    }

    private void showMatchPopUpWindow() {
        if (matchPopWindow == null) {
            matchPopWindow = new JCZQMatchPopWindow(getContext());
            matchPopWindow.setOnJCZQButtonClickListener(new JCZQMatchPopWindow.OnJCZQButtonClickListener() {
                @Override
                public void OnConfirmClick(List<Integer> position) {

                }
            });
        }
        matchPopWindow.show(toolbar);
    }

    @Override
    public int getPlayWayPosition() {
        return jczqPlayWayPosition;
    }

    @Override
    public void showPlayWay1() {
        try {
            if (type1Adapter == null) {
                for (int i = 0; i < 3; i++) {
                    JCZQType1HeaderInfo headerInfo = new JCZQType1HeaderInfo();
                    for (int j = 0; j < 8; j++) {
                        JCZQType1ContentInfo contentInfo = new JCZQType1ContentInfo("");
                        headerInfo.addSubItem(contentInfo);
                    }
                    list1.add(headerInfo);
                }
                type1Adapter = new JCZQType1Adapter(list1);
                type1Adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        if (view.getId() == R.id.layout_more_play_way) {
                            if (morePlayWayDialog == null) {
                                JCZQMorePlayWayDialog.Builder builder = new JCZQMorePlayWayDialog.Builder(getContext());
                                morePlayWayDialog = builder.create();
                            }
                            morePlayWayDialog.show();
                        }
                    }
                });
            }
            recyclerView.setAdapter(type1Adapter);
            type1Adapter.expandAll();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showPlayWay2() {
        try {
            if (type2Adapter == null) {
                for (int i = 0; i < 3; i++) {
                    JCZQType1HeaderInfo headerInfo = new JCZQType1HeaderInfo();
                    for (int j = 0; j < 8; j++) {
                        JCZQType1ContentInfo contentInfo = new JCZQType1ContentInfo("");
                        headerInfo.addSubItem(contentInfo);
                    }
                    list2.add(headerInfo);
                }
                type2Adapter = new JCZQType2Adapter(list2);
            }
            recyclerView.setAdapter(type2Adapter);
            type2Adapter.expandAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showPlayWay3() {
        try {
            if (type3Adapter == null) {
                for (int i = 0; i < 3; i++) {
                    JCZQType1HeaderInfo headerInfo = new JCZQType1HeaderInfo();
                    for (int j = 0; j < 8; j++) {
                        JCZQType1ContentInfo contentInfo = new JCZQType1ContentInfo("");
                        headerInfo.addSubItem(contentInfo);
                    }
                    list3.add(headerInfo);
                }
                type3Adapter = new JCZQType3Adapter(list3);
            }
            recyclerView.setAdapter(type3Adapter);
            type3Adapter.expandAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showPlayWay4() {
        try {
            if (type4Adapter == null) {
                for (int i = 0; i < 3; i++) {
                    JCZQType1HeaderInfo headerInfo = new JCZQType1HeaderInfo();
                    for (int j = 0; j < 8; j++) {
                        JCZQType1ContentInfo contentInfo = new JCZQType1ContentInfo("");
                        headerInfo.addSubItem(contentInfo);
                    }
                    list4.add(headerInfo);
                }
                type4Adapter = new JCZQType4Adapter(list4);
                type4Adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        if (view.getId() == R.id.see_all_score) {
                           showScoreDialog();
                        }
                    }
                });
            }
            recyclerView.setAdapter(type4Adapter);
            type4Adapter.expandAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showPlayWay5() {

    }

    @Override
    public void showPlayWay6() {

    }

    @Override
    public void showPlayWay7() {

    }

    @Override
    public void showPlayWay8() {

    }

    @Override
    public void showPlayWay9() {

    }

    public void showScoreDialog() {
        if (scoreDialog == null) {
            JCZQScoreDialog.Builder builder = new JCZQScoreDialog.Builder(getContext());
            scoreDialog = builder.create();
        }
        scoreDialog.show();
    }
}
