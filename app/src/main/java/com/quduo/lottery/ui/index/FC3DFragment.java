package com.quduo.lottery.ui.index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.quduo.lottery.AppConfig;
import com.quduo.lottery.R;
import com.quduo.lottery.mvp.BaseBackMvpFragment;
import com.quduo.lottery.ui.index.adapter.FC3DBallAdapter;
import com.quduo.lottery.ui.index.adapter.FC3DWinCodeAdapter;
import com.quduo.lottery.ui.index.adapter.SSCBallAdapter;
import com.quduo.lottery.ui.index.entity.SSQBallInfo;
import com.quduo.lottery.ui.index.popwindow.FC3DMenuPopWindow;
import com.quduo.lottery.ui.index.popwindow.FC3DPlayWayPopWindow;
import com.quduo.lottery.ui.index.popwindow.SSCMenuPopWindow;
import com.quduo.lottery.ui.index.presenter.FC3DPresenter;
import com.quduo.lottery.ui.index.view.IFC3DView;
import com.quduo.lottery.widgets.CustomListView;
import com.quduo.lottery.widgets.CustomeGridView;

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
 * 福彩3D
 * Created by scene on 2017/12/20.
 */

public class FC3DFragment extends BaseBackMvpFragment<IFC3DView, FC3DPresenter> implements IFC3DView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    Unbinder unbinder;
    @BindView(R.id.toolbar_menu)
    ImageView toolbarMenu;
    @BindView(R.id.jiajiang)
    ImageView jiajiang;
    @BindView(R.id.machine)
    TextView machine;
    @BindView(R.id.gridView1)
    CustomeGridView gridView1;
    @BindView(R.id.gridView2)
    CustomeGridView gridView2;
    @BindView(R.id.gridView3)
    CustomeGridView gridView3;
    @BindView(R.id.ptr_layout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.statusView)
    StatusViewLayout statusView;
    @BindView(R.id.delete_all)
    ImageView deleteAll;
    @BindView(R.id.total_num)
    TextView totalNum;
    @BindView(R.id.total_price)
    TextView totalPrice;
    @BindView(R.id.listView)
    CustomListView listView;
    @BindView(R.id.see_all_wincode_arrow)
    ImageView seeAllWincodeArrow;
    @BindView(R.id.see_all_wincode)
    LinearLayout seeAllWincode;
    @BindView(R.id.layout1)
    LinearLayout layout1;
    @BindView(R.id.layout2)
    LinearLayout layout2;
    @BindView(R.id.layout3)
    LinearLayout layout3;
    @BindView(R.id.toolbar_play_way_arrow)
    ImageView toolbarPlayWayArrow;
    @BindView(R.id.toolbar_play_way)
    LinearLayout toolbarPlayWay;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.layout_bai)
    RelativeLayout layoutBai;
    @BindView(R.id.layout_shi)
    RelativeLayout layoutShi;
    @BindView(R.id.layout_ge)
    RelativeLayout layoutGe;
    @BindView(R.id.geweiText)
    TextView geweiText;
    @BindView(R.id.toolbar_layout)
    RelativeLayout toolbarLayout;
    @BindView(R.id.confirm)
    TextView confirm;
    @BindView(R.id.bottom_layout)
    LinearLayout bottomLayout;
    @BindView(R.id.guide_layout)
    RelativeLayout guideLayout;

    private List<SSQBallInfo> list1 = new ArrayList<>();
    private List<SSQBallInfo> list2 = new ArrayList<>();
    private List<SSQBallInfo> list3 = new ArrayList<>();

    private FC3DBallAdapter ballAdapter1;
    private FC3DBallAdapter ballAdapter2;
    private FC3DBallAdapter ballAdapter3;

    private List<String> winCodeList = new ArrayList<>();
    private FC3DWinCodeAdapter winCodeAdapter;

    private FC3DPlayWayPopWindow playWayPopWindow;
    private FC3DMenuPopWindow menuPopWindow;

    private String[] fc3dPlayWays;

    private int fc3dPlayWayPosition;

    public static FC3DFragment newInstance() {
        Bundle args = new Bundle();
        FC3DFragment fragment = new FC3DFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fc3d, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initToolbarNav(toolbar);
        fc3dPlayWays = getResources().getStringArray(R.array.fc3d_play_way);
        fc3dPlayWayPosition = SPUtils.getInstance().getInt(AppConfig.KEY_FC3D_DEFAULT_PLAY_WAY_POSITION, 0);
        setToolbarTitle(fc3dPlayWayPosition);
        showGuide();
    }

    private void showGuide() {
        boolean isFirstUse = SPUtils.getInstance().getBoolean(AppConfig.KEY_FIRST_USE_PLAY_FC3D, true);
        if (isFirstUse) {
            guideLayout.setVisibility(View.VISIBLE);
            SPUtils.getInstance().put(AppConfig.KEY_FIRST_USE_PLAY_FC3D, false);
        } else {
            guideLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void initView() {
        super.initView();
        showContentPage();
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
                }, 2);
            }
        });

        for (int i = 0; i < 10; i++) {
            SSQBallInfo info1 = new SSQBallInfo();
            info1.setNumber(String.valueOf(i));
            SSQBallInfo info2 = new SSQBallInfo();
            info2.setNumber(String.valueOf(i));
            SSQBallInfo info3 = new SSQBallInfo();
            info3.setNumber(String.valueOf(i));
            list1.add(info1);
            list2.add(info2);
            list3.add(info3);
        }

        ballAdapter1 = new FC3DBallAdapter(getContext(), list1);
        ballAdapter2 = new FC3DBallAdapter(getContext(), list2);
        ballAdapter3 = new FC3DBallAdapter(getContext(), list3);

        gridView1.setAdapter(ballAdapter1);
        gridView2.setAdapter(ballAdapter2);
        gridView3.setAdapter(ballAdapter3);

        for (int i = 0; i < 10; i++) {
            winCodeList.add(String.valueOf(i));
        }
        winCodeAdapter = new FC3DWinCodeAdapter(getContext(), winCodeList);
        listView.setAdapter(winCodeAdapter);

        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                list1.get(i).setCheck(!list1.get(i).isCheck());
                ballAdapter1.notifyDataSetChanged();
                presenter.mathAllStake(fc3dPlayWayPosition);
            }
        });
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                list2.get(i).setCheck(!list2.get(i).isCheck());
                ballAdapter2.notifyDataSetChanged();
                presenter.mathAllStake(fc3dPlayWayPosition);
            }
        });
        gridView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                list3.get(i).setCheck(!list3.get(i).isCheck());
                ballAdapter3.notifyDataSetChanged();
                presenter.mathAllStake(fc3dPlayWayPosition);
            }
        });

        presenter.changeLayoutView(fc3dPlayWayPosition);
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
    public FC3DPresenter initPresenter() {
        return new FC3DPresenter(this);
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

    @OnClick(R.id.see_all_wincode)
    public void onClickSeeAllWinCode() {
        if (listView.getVisibility() == View.VISIBLE) {
            listView.setVisibility(View.GONE);
            seeAllWincodeArrow.setImageResource(R.drawable.ic_arrow_bottom);
        } else {
            listView.setVisibility(View.VISIBLE);
            seeAllWincodeArrow.setImageResource(R.drawable.ic_arrow_top);

        }
    }

    @OnClick(R.id.machine)
    public void onClickMachine() {
        presenter.clickMachine();
    }

    @OnClick(R.id.toolbar_play_way)
    public void onClickToolBarPlayWay() {
        if (playWayPopWindow == null) {
            playWayPopWindow = new FC3DPlayWayPopWindow(getContext());
            playWayPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    toolbarPlayWayArrow.setImageResource(R.drawable.ic_arrow_bottom_toolbar);
                }
            });

            playWayPopWindow.setOnFC3DPlayWayItemClickListener(new FC3DPlayWayPopWindow.OnFC3DPlayWayItemClickListener() {
                @Override
                public void OnFC3DPlayWayItemClick(int position) {
                    setToolbarTitle(position);
                    presenter.changeLayoutView(position);
                }
            });
        }
        playWayPopWindow.show(toolbar);
        toolbarPlayWayArrow.setImageResource(R.drawable.ic_arrow_top_toolbar);
    }

    private void setToolbarTitle(int titlePosition) {
        fc3dPlayWayPosition = titlePosition;
        toolbarTitle.setText(fc3dPlayWays[titlePosition]);
    }

    @OnClick(R.id.toolbar_menu)
    public void onClickToolbarMenu() {
        if (menuPopWindow == null) {
            menuPopWindow = new FC3DMenuPopWindow(getContext());
        }
        menuPopWindow.show(toolbarMenu);
    }


    @Override
    public void showStar3Direct() {
        try {
            layoutBai.setVisibility(View.VISIBLE);
            layoutShi.setVisibility(View.VISIBLE);
            layoutGe.setVisibility(View.VISIBLE);
            geweiText.setText("个位");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showStar3Combination3() {
        try {
            layoutBai.setVisibility(View.GONE);
            layoutShi.setVisibility(View.GONE);
            layoutGe.setVisibility(View.VISIBLE);
            geweiText.setText("选号");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showStar3Combination6() {
        try {
            layoutBai.setVisibility(View.GONE);
            layoutShi.setVisibility(View.GONE);
            layoutGe.setVisibility(View.VISIBLE);
            geweiText.setText("选号");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void clearAllChoosedBall() {
        try {
            for (int i = 0; i < list1.size(); i++) {
                list1.get(i).setCheck(false);
            }
            for (int i = 0; i < list2.size(); i++) {
                list2.get(i).setCheck(false);
            }
            for (int i = 0; i < list3.size(); i++) {
                list3.get(i).setCheck(false);
            }
            ballAdapter1.notifyDataSetChanged();
            ballAdapter2.notifyDataSetChanged();
            ballAdapter3.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<SSQBallInfo> getList1() {
        return list1;
    }

    @Override
    public List<SSQBallInfo> getList2() {
        return list2;
    }

    @Override
    public List<SSQBallInfo> getList3() {
        return list3;
    }

    @Override
    public void showAllStakeAndPrice(int totalNumber) {
        try {
            totalNum.setText(String.valueOf(totalNumber));
            totalPrice.setText(String.valueOf((totalNumber * 2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.delete_all)
    public void onClickDeleteAll() {
        try {
            presenter.changeLayoutView(fc3dPlayWayPosition);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyAllAdapter() {
        try {
            ballAdapter1.notifyDataSetChanged();
            ballAdapter2.notifyDataSetChanged();
            ballAdapter3.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCurrentPlayWayPosition() {
        return fc3dPlayWayPosition;
    }

    @Override
    public int getTotalStake() {
        try {
            return Integer.parseInt(totalNum.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @OnClick(R.id.confirm)
    public void onClickConfirm() {
        try {
            presenter.clickConfirm();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void toConfirmBetPage() {
        try {
            start(FC3DConfirmBetFragment.newInstance(1, 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
