package com.quduo.lottery.ui.index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quduo.lottery.R;
import com.quduo.lottery.mvp.BaseMainMvpFragment;
import com.quduo.lottery.ui.index.presenter.IndexPresenter;
import com.quduo.lottery.ui.index.view.IIndexView;
import com.sunsky.marqueeview.MarqueeView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.PtrDefaultHandler;
import wiki.scene.loadmore.PtrFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;

/**
 * 主页
 * Created by scene on 2017/12/18.
 */

public class IndexFragment extends BaseMainMvpFragment<IIndexView, IndexPresenter> implements IIndexView {
    @BindView(R.id.statusView)
    StatusViewLayout statusView;
    @BindView(R.id.ptr_layout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.banner)
    Banner banner;
    Unbinder unbinder;
    @BindView(R.id.win_notice)
    MarqueeView winNotice;

    private List<View> noticeViewList = new ArrayList<>();

    public static IndexFragment newInstance() {
        Bundle args = new Bundle();
        IndexFragment fragment = new IndexFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            winNotice.startFlipping();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        try {
            winNotice.stopFlipping();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        bindNoticeInfo();
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
            statusView.showNetError(retryListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bindNoticeInfo() {
        noticeViewList.clear();
        List<String> noticeList = new ArrayList<>();
        noticeList.add("周**");
        noticeList.add("吴**");
        noticeList.add("郑**");
        noticeList.add("王**");
        noticeList.add("岳**");
        noticeList.add("黄**");
        int noticeSize = noticeList.size();
        for (int i = 0; i < noticeSize; i = i + 2) {
            View noticeView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_index_win_notice_item, null);
            TextView nickName1 = noticeView.findViewById(R.id.nickname_1);
            TextView betWay1 = noticeView.findViewById(R.id.bet_way_1);
            TextView nickName2 = noticeView.findViewById(R.id.nickname_2);
            TextView betWay2 = noticeView.findViewById(R.id.bet_way_2);
            TextView winMoney1 = noticeView.findViewById(R.id.win_money_1);
            TextView winMoney2 = noticeView.findViewById(R.id.win_money_2);
            if (noticeSize % 2 == 0) {//偶数条
                nickName1.setText(noticeList.get(i));
                nickName2.setText(noticeList.get(i + 1));
            } else {//奇数条
                if (noticeSize > i + 1) {//不是最后的1条
                    nickName1.setText(noticeList.get(i));
                    nickName2.setText(noticeList.get(i + 1));
                } else {//最后一条
                    nickName1.setText(noticeList.get(i));
                    nickName2.setText(noticeList.get(0));
                }
            }
            noticeViewList.add(noticeView);
        }
        winNotice.setViews(noticeViewList);
    }

    @Override
    public IndexPresenter initPresenter() {
        return new IndexPresenter(this);
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

}
