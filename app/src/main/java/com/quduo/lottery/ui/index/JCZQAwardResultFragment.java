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
import android.widget.TextView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.quduo.lottery.R;
import com.quduo.lottery.mvp.BaseBackMvpFragment;
import com.quduo.lottery.ui.index.adapter.JCZQMatchResultAdapter;
import com.quduo.lottery.ui.index.entity.JCLQMatchResultContentInfo;
import com.quduo.lottery.ui.index.entity.JCLQMatchResultHeaderInfo;
import com.quduo.lottery.ui.index.presenter.JCZQMatchResultPresenter;
import com.quduo.lottery.ui.index.view.IJCZQMatchResultView;

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
 * 足球比赛结果
 * Created by scene on 2018/1/3.
 */

public class JCZQAwardResultFragment extends BaseBackMvpFragment<IJCZQMatchResultView, JCZQMatchResultPresenter> implements IJCZQMatchResultView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptr_layout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.statusView)
    StatusViewLayout statusView;
    Unbinder unbinder;

    private JCZQMatchResultAdapter adapter;
    private List<MultiItemEntity> list = new ArrayList<>();

    public static JCZQAwardResultFragment newInstance() {
        Bundle args = new Bundle();
        JCZQAwardResultFragment fragment = new JCZQAwardResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jczq_match_result, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        toolbarTitle.setText("竞彩足球 开奖");
        initToolbarNav(toolbar);
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
                                try {
                                    refreshComplete();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }, 2000);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            JCLQMatchResultHeaderInfo headerInfo = new JCLQMatchResultHeaderInfo();
            headerInfo.setTime(1000L);
            for (int j = 0; j < 4; j++) {
                JCLQMatchResultContentInfo contentInfo = new JCLQMatchResultContentInfo("");
                headerInfo.addSubItem(contentInfo);
            }
            list.add(headerInfo);
        }
        adapter = new JCZQMatchResultAdapter(list);
        recyclerView.setAdapter(adapter);
        if (list.size() > 0) {
            adapter.expand(0);
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
    public JCZQMatchResultPresenter initPresenter() {
        return new JCZQMatchResultPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void refreshComplete() {
        try {
            ptrLayout.refreshComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
