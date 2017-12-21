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

    private List<SSQBallInfo> list1 = new ArrayList<>();
    private List<SSQBallInfo> list2 = new ArrayList<>();
    private List<SSQBallInfo> list3 = new ArrayList<>();
    private List<SSQBallInfo> list4 = new ArrayList<>();
    private List<SSQBallInfo> list5 = new ArrayList<>();

    private SSCBallAdapter ballAdapter1;
    private SSCBallAdapter ballAdapter2;
    private SSCBallAdapter ballAdapter3;
    private SSCBallAdapter ballAdapter4;
    private SSCBallAdapter ballAdapter5;

    private List<String> winCodeList = new ArrayList<>();
    private SSCWinCodeAdapter winCodeAdapter;

    private SSCPlayWayPopWindow playWayPopWindow;
    private SSCMenuPopWindow menuPopWindow;

    private String[] sscPlayWay;

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
        sscPlayWay = getResources().getStringArray(R.array.ssc_play_way);
        int sscPlayWayPosition = SPUtils.getInstance().getInt(AppConfig.KEY_SSC_DEFAULT_PLAY_WAY_POSITION, 0);
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

        ballAdapter1 = new SSCBallAdapter(getContext(), list1);
        ballAdapter2 = new SSCBallAdapter(getContext(), list2);
        ballAdapter3 = new SSCBallAdapter(getContext(), list3);
        ballAdapter4 = new SSCBallAdapter(getContext(), list4);
        ballAdapter5 = new SSCBallAdapter(getContext(), list5);

        gridView1.setAdapter(ballAdapter1);
        gridView2.setAdapter(ballAdapter2);
        gridView3.setAdapter(ballAdapter3);
        gridView4.setAdapter(ballAdapter4);
        gridView5.setAdapter(ballAdapter5);


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
            }
        });
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                list2.get(i).setCheck(!list2.get(i).isCheck());
                ballAdapter2.notifyDataSetChanged();
            }
        });
        gridView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                list3.get(i).setCheck(!list3.get(i).isCheck());
                ballAdapter3.notifyDataSetChanged();
            }
        });
        gridView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                list4.get(i).setCheck(!list4.get(i).isCheck());
                ballAdapter4.notifyDataSetChanged();
            }
        });
        gridView5.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                list5.get(i).setCheck(!list5.get(i).isCheck());
                ballAdapter5.notifyDataSetChanged();
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
                }
            });
        }
        playWayPopWindow.show(toolbar);
        toolbarPlayWayArrow.setImageResource(R.drawable.ic_arrow_top_toolbar);
    }

    private void setToolbarTitle(int titlePosition) {
        toolbarTitle.setText(sscPlayWay[titlePosition]);
    }


    @OnClick(R.id.toolbar_menu)
    public void onClickToolbarMenu() {
        if (menuPopWindow == null) {
            menuPopWindow = new SSCMenuPopWindow(getContext());
        }
        menuPopWindow.show(toolbarMenu);
    }
}
