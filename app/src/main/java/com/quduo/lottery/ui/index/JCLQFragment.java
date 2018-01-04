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
import com.quduo.lottery.ui.index.adapter.jclq.JCLQType1Adapter;
import com.quduo.lottery.ui.index.adapter.jclq.JCLQType2Adapter;
import com.quduo.lottery.ui.index.adapter.jclq.JCLQType3Adapter;
import com.quduo.lottery.ui.index.adapter.jclq.JCLQType4Adapter;
import com.quduo.lottery.ui.index.adapter.jclq.JCLQType5Adapter;
import com.quduo.lottery.ui.index.adapter.jclq.JCLQType6Adapter;
import com.quduo.lottery.ui.index.entity.JCZQType1ContentInfo;
import com.quduo.lottery.ui.index.entity.JCZQType1HeaderInfo;
import com.quduo.lottery.ui.index.popwindow.JCLQMatchPopWindow;
import com.quduo.lottery.ui.index.popwindow.JCLQMorePlayWayDialog;
import com.quduo.lottery.ui.index.popwindow.JCLQPlayWayPopWindow;
import com.quduo.lottery.ui.index.popwindow.JCLQScoreDialog;
import com.quduo.lottery.ui.index.presenter.JCLQPresenter;
import com.quduo.lottery.ui.index.view.IJCLQView;

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
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptr_layout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.statusView)
    StatusViewLayout statusView;
    @BindView(R.id.delete_all)
    ImageView deleteAll;
    @BindView(R.id.confirm)
    TextView confirm;

    private JCLQMatchPopWindow matchPopWindow;

    private JCLQPlayWayPopWindow playWayPopWindow;
    private String[] jclqPlayWays;
    private int jclqPlayWayPosition;

    private List<MultiItemEntity> list1 = new ArrayList<>();
    private List<MultiItemEntity> list2 = new ArrayList<>();
    private List<MultiItemEntity> list3 = new ArrayList<>();
    private List<MultiItemEntity> list4 = new ArrayList<>();
    private List<MultiItemEntity> list5 = new ArrayList<>();
    private List<MultiItemEntity> list6 = new ArrayList<>();

    private JCLQType1Adapter type1Adapter;
    private JCLQType2Adapter type2Adapter;
    private JCLQType3Adapter type3Adapter;
    private JCLQType4Adapter type4Adapter;
    private JCLQType5Adapter type5Adapter;
    private JCLQType6Adapter type6Adapter;

    private JCLQMorePlayWayDialog morePlayWayDialog;
    private JCLQScoreDialog scoreDialog;

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

    private View.OnClickListener retryListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

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
                type1Adapter = new JCLQType1Adapter(list1);
                type1Adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        if (view.getId() == R.id.layout_more_play_way) {
                            showMorePlayWayDialog();
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
    public void showPlayWayType2() {
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
                type2Adapter = new JCLQType2Adapter(list2);
            }
            recyclerView.setAdapter(type2Adapter);
            type2Adapter.expandAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showPlayWayType3() {
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
                type3Adapter = new JCLQType3Adapter(list3);
            }
            recyclerView.setAdapter(type3Adapter);
            type3Adapter.expandAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showPlayWayType4() {
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
                type4Adapter = new JCLQType4Adapter(list4);
                type4Adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        if (view.getId() == R.id.see_all_score) {
                            //showScoreDialog();
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
    public void showPlayWayType5() {
        try {
            if (type5Adapter == null) {
                for (int i = 0; i < 3; i++) {
                    JCZQType1HeaderInfo headerInfo = new JCZQType1HeaderInfo();
                    for (int j = 0; j < 8; j++) {
                        JCZQType1ContentInfo contentInfo = new JCZQType1ContentInfo("");
                        headerInfo.addSubItem(contentInfo);
                    }
                    list5.add(headerInfo);
                }
                type5Adapter = new JCLQType5Adapter(list5);
            }
            recyclerView.setAdapter(type5Adapter);
            type5Adapter.expandAll();
            type5Adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    if (view.getId() == R.id.see_all_score) {
                        showScoreDialog();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showPlayWayType6() {
        try {
            if (type6Adapter == null) {
                for (int i = 0; i < 3; i++) {
                    JCZQType1HeaderInfo headerInfo = new JCZQType1HeaderInfo();
                    for (int j = 0; j < 8; j++) {
                        JCZQType1ContentInfo contentInfo = new JCZQType1ContentInfo("");
                        headerInfo.addSubItem(contentInfo);
                    }
                    list6.add(headerInfo);
                }
                type6Adapter = new JCLQType6Adapter(list6);
                type6Adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        if (view.getId() == R.id.see_all_score) {
                            // showHalfFullResultDialog();
                        }
                    }
                });
            }
            recyclerView.setAdapter(type6Adapter);
            type6Adapter.expandAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMorePlayWayDialog() {
        try {
            if (morePlayWayDialog == null) {
                JCLQMorePlayWayDialog.Builder builder = new JCLQMorePlayWayDialog.Builder(getContext());
                morePlayWayDialog = builder.create();
            }
            morePlayWayDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showScoreDialog() {
        try {
            if (scoreDialog == null) {
                JCLQScoreDialog.Builder builder = new JCLQScoreDialog.Builder(getContext());
                scoreDialog = builder.create();
            }
            scoreDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.toolbar_win_result)
    public void onClickToolbarWinResult() {
        start(JCLQMatchResultFragment.newInstance());
    }

    @OnClick(R.id.confirm)
    public void onClickConfirm() {
        start(JCLQConfirmBetFragment.newInstance(1, 1));
    }
}
