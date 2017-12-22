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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.quduo.lottery.AppConfig;
import com.quduo.lottery.R;
import com.quduo.lottery.mvp.BaseBackMvpFragment;
import com.quduo.lottery.ui.index.adapter.SSQBlueAdapter;
import com.quduo.lottery.ui.index.adapter.SSQRedAdapter;
import com.quduo.lottery.ui.index.adapter.SSQWinCodeAdapter;
import com.quduo.lottery.ui.index.entity.SSQBallInfo;
import com.quduo.lottery.ui.index.popwindow.SSQMenuPopWindow;
import com.quduo.lottery.ui.index.presenter.SSQPresenter;
import com.quduo.lottery.ui.index.view.ISSQView;
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
 * 双色球
 * Created by scene on 2017/12/19.
 */

public class SSQFragment extends BaseBackMvpFragment<ISSQView, SSQPresenter> implements ISSQView {

    public static final int SSQ_RED_NUMBER = 6;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.listView)
    CustomListView listView;
    @BindView(R.id.gridView1)
    CustomeGridView gridView1;
    @BindView(R.id.gridView2)
    CustomeGridView gridView2;
    @BindView(R.id.ptr_layout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.statusView)
    StatusViewLayout statusView;
    @BindView(R.id.see_all_wincode_arrow)
    ImageView seeAllWincodeArrow;
    Unbinder unbinder;
    @BindView(R.id.total_num)
    TextView totalNum;
    @BindView(R.id.total_price)
    TextView totalPrice;
    @BindView(R.id.toolbar_menu)
    ImageView toolbarMenu;
    @BindView(R.id.see_all_wincode)
    LinearLayout seeAllWincode;
    @BindView(R.id.machine)
    TextView machine;
    @BindView(R.id.delete_all)
    ImageView deleteAll;
    @BindView(R.id.confirm)
    TextView confirm;
    @BindView(R.id.bottom_layout)
    LinearLayout bottomLayout;
    @BindView(R.id.guide_layout)
    RelativeLayout guideLayout;

    private SSQRedAdapter redAdapter;
    private List<SSQBallInfo> redList = new ArrayList<>();
    private SSQBlueAdapter blueAdapter;
    private List<SSQBallInfo> blueList = new ArrayList<>();
    private SSQWinCodeAdapter winCodeAdapter;
    private List<String> winCodeList = new ArrayList<>();

    public static SSQFragment newInstance() {
        Bundle args = new Bundle();
        SSQFragment fragment = new SSQFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ssq, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        boolean isFirstUse = SPUtils.getInstance().getBoolean(AppConfig.KEY_FIRST_USE_PLAY_SSQ, true);
        if (isFirstUse) {
            guideLayout.setVisibility(View.VISIBLE);
            SPUtils.getInstance().put(AppConfig.KEY_FIRST_USE_PLAY_SSQ, false);
        } else {
            guideLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void initView() {
        super.initView();
        toolbarTitle.setText(getString(R.string.ssq));
        initToolbarNav(toolbar);

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
        bindRedAdapter();
    }

    private void bindRedAdapter() {
        for (int i = 1; i < 34; i++) {
            SSQBallInfo ballInfo = new SSQBallInfo();
            if (i < 10) {
                ballInfo.setNumber(String.valueOf("0" + i));
                redList.add(ballInfo);
                winCodeList.add(String.valueOf("0" + i));
            } else {
                ballInfo.setNumber(String.valueOf(i));
                redList.add(ballInfo);
            }
            if (i < 17) {
                SSQBallInfo blueBallInfo = new SSQBallInfo();
                if (i < 10) {
                    blueBallInfo.setNumber(String.valueOf("0" + i));
                    blueList.add(blueBallInfo);
                } else {
                    blueBallInfo.setNumber(String.valueOf(i));
                    blueList.add(blueBallInfo);
                }
            }
        }
        redAdapter = new SSQRedAdapter(getContext(), redList);
        gridView1.setAdapter(redAdapter);
        blueAdapter = new SSQBlueAdapter(getContext(), blueList);
        gridView2.setAdapter(blueAdapter);
        winCodeAdapter = new SSQWinCodeAdapter(getContext(), winCodeList);
        listView.setAdapter(winCodeAdapter);

        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                redList.get(i).setCheck(!redList.get(i).isCheck());
                redAdapter.notifyDataSetChanged();
                presenter.setTotalNumAndPrice();
            }
        });
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                blueList.get(i).setCheck(!blueList.get(i).isCheck());
                blueAdapter.notifyDataSetChanged();
                presenter.setTotalNumAndPrice();
            }
        });

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
    public SSQPresenter initPresenter() {
        return new SSQPresenter(this);
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
    public void onClickSeeAllWincode() {
        if (listView.getVisibility() == View.VISIBLE) {
            listView.setVisibility(View.GONE);
            seeAllWincodeArrow.setImageResource(R.drawable.ic_arrow_bottom);
        } else {
            seeAllWincodeArrow.setImageResource(R.drawable.ic_arrow_top);
            listView.setVisibility(View.VISIBLE);
        }
    }

    @OnClick(R.id.machine)
    public void onClickMachine() {
        presenter.clickMachine();
    }

    @Override
    public List<SSQBallInfo> getRedList() {
        return redList;
    }

    @Override
    public List<SSQBallInfo> getBlueList() {
        return blueList;
    }

    @Override
    public void setTotalNumber(String number) {
        try {
            totalNum.setText(number);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setTotalPrice(String price) {
        try {
            totalPrice.setText(price);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyRedAdapter() {
        try {
            redAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyBuleAdapter() {
        try {
            blueAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.delete_all)
    public void onClickDeleteAll() {
        presenter.deleteAllCode();
    }

    private SSQMenuPopWindow menuPopWindow;

    @OnClick(R.id.toolbar_menu)
    public void onClickToolbarMenu() {
        if (menuPopWindow == null) {
            menuPopWindow = new SSQMenuPopWindow(getContext());
        }
        menuPopWindow.show(toolbarMenu);
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
        presenter.clickConfirm();
    }

    @Override
    public void toConfirmPage() {
        try {
            start(SSQConfirmBetFragment.newInstance(1, 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}