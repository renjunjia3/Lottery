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
import com.quduo.lottery.ui.index.adapter.SSCBallAdapter;
import com.quduo.lottery.ui.index.adapter.SSCWinCodeAdapter;
import com.quduo.lottery.ui.index.entity.SSQBallInfo;
import com.quduo.lottery.ui.index.popwindow.SSCMenuPopWindow;
import com.quduo.lottery.ui.index.popwindow.SSCPlayWayPopWindow;
import com.quduo.lottery.ui.index.presenter.SSCPresenter;
import com.quduo.lottery.ui.index.view.ISSCView;
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
 * 时时彩
 * Created by scene on 2017/12/20.
 */

public class SSCFragment extends BaseBackMvpFragment<ISSCView, SSCPresenter> implements ISSCView {
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
    @BindView(R.id.gridView4)
    CustomeGridView gridView4;
    @BindView(R.id.gridView5)
    CustomeGridView gridView5;
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
    @BindView(R.id.layout4)
    LinearLayout layout4;
    @BindView(R.id.layout5)
    LinearLayout layout5;
    @BindView(R.id.toolbar_play_way_arrow)
    ImageView toolbarPlayWayArrow;
    @BindView(R.id.toolbar_play_way)
    LinearLayout toolbarPlayWay;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.layout_wan)
    RelativeLayout layoutWan;
    @BindView(R.id.layout_qian)
    RelativeLayout layoutQian;
    @BindView(R.id.layout_bai)
    RelativeLayout layoutBai;
    @BindView(R.id.layout_shi)
    RelativeLayout layoutShi;
    @BindView(R.id.layout_ge)
    RelativeLayout layoutGe;
    @BindView(R.id.geweiText)
    TextView geweiText;

    private List<SSQBallInfo> list1 = new ArrayList<>();
    private List<SSQBallInfo> list2 = new ArrayList<>();
    private List<SSQBallInfo> list3 = new ArrayList<>();
    private List<SSQBallInfo> list4 = new ArrayList<>();
    private List<SSQBallInfo> list5 = new ArrayList<>();
    private List<SSQBallInfo> listBigSmallSingleDouble1 = new ArrayList<>();
    private List<SSQBallInfo> listBigSmallSingleDouble2 = new ArrayList<>();

    private SSCBallAdapter ballAdapter1;
    private SSCBallAdapter ballAdapter2;
    private SSCBallAdapter ballAdapter3;
    private SSCBallAdapter ballAdapter4;
    private SSCBallAdapter ballAdapter5;
    private SSCBallAdapter ballAdapterBigSmallSingleDouble1;
    private SSCBallAdapter ballAdapterBigSmallSingleDouble2;

    private List<String> winCodeList = new ArrayList<>();
    private SSCWinCodeAdapter winCodeAdapter;

    private SSCPlayWayPopWindow playWayPopWindow;
    private SSCMenuPopWindow menuPopWindow;

    private String[] sscPlayWays;

    private int sscPlayWayPosition;

    public static SSCFragment newInstance() {
        Bundle args = new Bundle();
        SSCFragment fragment = new SSCFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ssc, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initToolbarNav(toolbar);
        sscPlayWays = getResources().getStringArray(R.array.ssc_play_way);
        sscPlayWayPosition = SPUtils.getInstance().getInt(AppConfig.KEY_SSC_DEFAULT_PLAY_WAY_POSITION, 0);
        setToolbarTitle(sscPlayWayPosition);
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
            SSQBallInfo info4 = new SSQBallInfo();
            info4.setNumber(String.valueOf(i));
            SSQBallInfo info5 = new SSQBallInfo();
            info5.setNumber(String.valueOf(i));
            list1.add(info1);
            list2.add(info2);
            list3.add(info3);
            list4.add(info4);
            list5.add(info5);
        }
        SSQBallInfo ssqBallInfo1 = new SSQBallInfo();
        ssqBallInfo1.setNumber("大");
        SSQBallInfo ssqBallInfo2 = new SSQBallInfo();
        ssqBallInfo2.setNumber("小");
        SSQBallInfo ssqBallInfo3 = new SSQBallInfo();
        ssqBallInfo3.setNumber("单");
        SSQBallInfo ssqBallInfo4 = new SSQBallInfo();
        ssqBallInfo4.setNumber("双");
        listBigSmallSingleDouble1.add(ssqBallInfo1);
        listBigSmallSingleDouble1.add(ssqBallInfo2);
        listBigSmallSingleDouble1.add(ssqBallInfo3);
        listBigSmallSingleDouble1.add(ssqBallInfo4);

        SSQBallInfo ssqBallInfo5 = new SSQBallInfo();
        ssqBallInfo5.setNumber("大");
        SSQBallInfo ssqBallInfo6 = new SSQBallInfo();
        ssqBallInfo6.setNumber("小");
        SSQBallInfo ssqBallInfo7 = new SSQBallInfo();
        ssqBallInfo7.setNumber("单");
        SSQBallInfo ssqBallInfo8 = new SSQBallInfo();
        ssqBallInfo8.setNumber("双");
        listBigSmallSingleDouble2.add(ssqBallInfo5);
        listBigSmallSingleDouble2.add(ssqBallInfo6);
        listBigSmallSingleDouble2.add(ssqBallInfo7);
        listBigSmallSingleDouble2.add(ssqBallInfo8);


        ballAdapter1 = new SSCBallAdapter(getContext(), list1);
        ballAdapter2 = new SSCBallAdapter(getContext(), list2);
        ballAdapter3 = new SSCBallAdapter(getContext(), list3);
        ballAdapter4 = new SSCBallAdapter(getContext(), list4);
        ballAdapter5 = new SSCBallAdapter(getContext(), list5);
        ballAdapterBigSmallSingleDouble1 = new SSCBallAdapter(getContext(), listBigSmallSingleDouble1);
        ballAdapterBigSmallSingleDouble2 = new SSCBallAdapter(getContext(), listBigSmallSingleDouble2);

        gridView1.setAdapter(ballAdapter1);
        gridView2.setAdapter(ballAdapter2);
        gridView3.setAdapter(ballAdapter3);
        //大小单双的时候绑定不同的adapter
        if (sscPlayWayPosition == 2) {
            gridView4.setAdapter(ballAdapterBigSmallSingleDouble1);
            gridView5.setAdapter(ballAdapterBigSmallSingleDouble2);
        } else {
            gridView4.setAdapter(ballAdapter4);
            gridView5.setAdapter(ballAdapter5);
        }

        for (int i = 0; i < 10; i++) {
            winCodeList.add(String.valueOf(i));
        }
        winCodeAdapter = new SSCWinCodeAdapter(getContext(), winCodeList);
        listView.setAdapter(winCodeAdapter);

        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                list1.get(i).setCheck(!list1.get(i).isCheck());
                ballAdapter1.notifyDataSetChanged();
                presenter.mathAllStake(sscPlayWayPosition);
            }
        });
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                list2.get(i).setCheck(!list2.get(i).isCheck());
                ballAdapter2.notifyDataSetChanged();
                presenter.mathAllStake(sscPlayWayPosition);
            }
        });
        gridView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                list3.get(i).setCheck(!list3.get(i).isCheck());
                ballAdapter3.notifyDataSetChanged();
                presenter.mathAllStake(sscPlayWayPosition);
            }
        });
        gridView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (gridView4.getAdapter() == ballAdapter4) {
                    list4.get(i).setCheck(!list4.get(i).isCheck());
                    ballAdapter4.notifyDataSetChanged();
                } else {
                    listBigSmallSingleDouble1.get(i).setCheck(!listBigSmallSingleDouble1.get(i).isCheck());
                    ballAdapterBigSmallSingleDouble1.notifyDataSetChanged();
                }
                presenter.mathAllStake(sscPlayWayPosition);
            }
        });
        gridView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (gridView5.getAdapter() == ballAdapter5) {
                    list5.get(i).setCheck(!list5.get(i).isCheck());
                    ballAdapter5.notifyDataSetChanged();
                } else {
                    listBigSmallSingleDouble2.get(i).setCheck(!listBigSmallSingleDouble2.get(i).isCheck());
                    ballAdapterBigSmallSingleDouble2.notifyDataSetChanged();
                }
                presenter.mathAllStake(sscPlayWayPosition);
            }
        });

        presenter.changeLayoutView(sscPlayWayPosition);
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
    public SSCPresenter initPresenter() {
        return new SSCPresenter(this);
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
            playWayPopWindow = new SSCPlayWayPopWindow(getContext());
            playWayPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    toolbarPlayWayArrow.setImageResource(R.drawable.ic_arrow_bottom_toolbar);
                }
            });

            playWayPopWindow.setOnSSCPlayWayItemClickListener(new SSCPlayWayPopWindow.OnSSCPlayWayItemClickListener() {
                @Override
                public void OnSSCPlayWayItemClick(int position) {
                    setToolbarTitle(position);
                    presenter.changeLayoutView(position);
                }
            });
        }
        playWayPopWindow.show(toolbar);
        toolbarPlayWayArrow.setImageResource(R.drawable.ic_arrow_top_toolbar);
    }

    private void setToolbarTitle(int titlePosition) {
        sscPlayWayPosition = titlePosition;
        toolbarTitle.setText(sscPlayWays[titlePosition]);
    }

    @OnClick(R.id.toolbar_menu)
    public void onClickToolbarMenu() {
        if (menuPopWindow == null) {
            menuPopWindow = new SSCMenuPopWindow(getContext());
        }
        menuPopWindow.show(toolbarMenu);
    }

    @Override
    public void showStar5Direct() {
        try {
            layoutWan.setVisibility(View.VISIBLE);
            layoutQian.setVisibility(View.VISIBLE);
            layoutBai.setVisibility(View.VISIBLE);
            layoutShi.setVisibility(View.VISIBLE);
            layoutGe.setVisibility(View.VISIBLE);
            gridView4.setAdapter(ballAdapter4);
            gridView5.setAdapter(ballAdapter5);
            geweiText.setText("个位");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showStar5Combination() {
        try {
            layoutWan.setVisibility(View.VISIBLE);
            layoutQian.setVisibility(View.VISIBLE);
            layoutBai.setVisibility(View.VISIBLE);
            layoutShi.setVisibility(View.VISIBLE);
            layoutGe.setVisibility(View.VISIBLE);
            gridView4.setAdapter(ballAdapter4);
            gridView5.setAdapter(ballAdapter5);
            geweiText.setText("个位");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showBigSmallSingleDouble() {
        try {
            layoutWan.setVisibility(View.GONE);
            layoutQian.setVisibility(View.GONE);
            layoutBai.setVisibility(View.GONE);
            layoutShi.setVisibility(View.VISIBLE);
            layoutGe.setVisibility(View.VISIBLE);
            gridView4.setAdapter(ballAdapterBigSmallSingleDouble1);
            gridView5.setAdapter(ballAdapterBigSmallSingleDouble2);
            geweiText.setText("选号");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showStar3Direct() {
        try {
            layoutWan.setVisibility(View.GONE);
            layoutQian.setVisibility(View.GONE);
            layoutBai.setVisibility(View.VISIBLE);
            layoutShi.setVisibility(View.VISIBLE);
            layoutGe.setVisibility(View.VISIBLE);
            gridView4.setAdapter(ballAdapter4);
            gridView5.setAdapter(ballAdapter5);
            geweiText.setText("个位");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showStar3Combination3() {
        try {
            layoutWan.setVisibility(View.GONE);
            layoutQian.setVisibility(View.GONE);
            layoutBai.setVisibility(View.GONE);
            layoutShi.setVisibility(View.GONE);
            layoutGe.setVisibility(View.VISIBLE);
            gridView4.setAdapter(ballAdapter4);
            gridView5.setAdapter(ballAdapter5);
            geweiText.setText("选号");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showStar3Combination6() {
        try {
            layoutWan.setVisibility(View.GONE);
            layoutQian.setVisibility(View.GONE);
            layoutBai.setVisibility(View.GONE);
            layoutShi.setVisibility(View.GONE);
            layoutGe.setVisibility(View.VISIBLE);
            gridView4.setAdapter(ballAdapter4);
            gridView5.setAdapter(ballAdapter5);
            geweiText.setText("选号");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showStar2Direct() {
        try {
            layoutWan.setVisibility(View.GONE);
            layoutQian.setVisibility(View.GONE);
            layoutBai.setVisibility(View.GONE);
            layoutShi.setVisibility(View.VISIBLE);
            layoutGe.setVisibility(View.VISIBLE);
            gridView4.setAdapter(ballAdapter4);
            gridView5.setAdapter(ballAdapter5);
            geweiText.setText("个位");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showStar2Combination() {
        try {
            layoutWan.setVisibility(View.GONE);
            layoutQian.setVisibility(View.GONE);
            layoutBai.setVisibility(View.GONE);
            layoutShi.setVisibility(View.GONE);
            layoutGe.setVisibility(View.VISIBLE);
            gridView4.setAdapter(ballAdapter4);
            gridView5.setAdapter(ballAdapter5);
            geweiText.setText("选号");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showStar1Direct() {
        try {
            layoutWan.setVisibility(View.GONE);
            layoutQian.setVisibility(View.GONE);
            layoutBai.setVisibility(View.GONE);
            layoutShi.setVisibility(View.GONE);
            layoutGe.setVisibility(View.VISIBLE);
            gridView4.setAdapter(ballAdapter4);
            gridView5.setAdapter(ballAdapter5);
            geweiText.setText("个位");
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
            for (int i = 0; i < list4.size(); i++) {
                list4.get(i).setCheck(false);
            }
            for (int i = 0; i < list5.size(); i++) {
                list5.get(i).setCheck(false);
            }
            for (int i = 0; i < listBigSmallSingleDouble1.size(); i++) {
                listBigSmallSingleDouble1.get(i).setCheck(false);
            }
            for (int i = 0; i < listBigSmallSingleDouble2.size(); i++) {
                listBigSmallSingleDouble2.get(i).setCheck(false);
            }
            ballAdapter1.notifyDataSetChanged();
            ballAdapter2.notifyDataSetChanged();
            ballAdapter3.notifyDataSetChanged();
            ballAdapter4.notifyDataSetChanged();
            ballAdapter5.notifyDataSetChanged();
            ballAdapterBigSmallSingleDouble1.notifyDataSetChanged();
            ballAdapterBigSmallSingleDouble2.notifyDataSetChanged();
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
    public List<SSQBallInfo> getList4() {
        return list4;
    }

    @Override
    public List<SSQBallInfo> getList5() {
        return list5;
    }

    @Override
    public List<SSQBallInfo> getListBigSmallSingleDouble1() {
        return listBigSmallSingleDouble1;
    }

    @Override
    public List<SSQBallInfo> getListBigSmallSingleDouble2() {
        return listBigSmallSingleDouble2;
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
            presenter.changeLayoutView(sscPlayWayPosition);
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
            ballAdapter4.notifyDataSetChanged();
            ballAdapter5.notifyDataSetChanged();
            ballAdapterBigSmallSingleDouble1.notifyDataSetChanged();
            ballAdapterBigSmallSingleDouble2.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCurrentPlayWayPosition() {
        return sscPlayWayPosition;
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
            start(SSCConfirmBetFragment.newInstance(1, 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
