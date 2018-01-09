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

import com.quduo.lottery.R;
import com.quduo.lottery.mvp.BaseBackMvpFragment;
import com.quduo.lottery.ui.index.adapter.DLTAwardResultAdapter;
import com.quduo.lottery.ui.index.entity.DLTAwardResultInfo;
import com.quduo.lottery.ui.index.presenter.DLTAwardResultPresenter;
import com.quduo.lottery.ui.index.view.IDLTAwardResultView;

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
 * 大乐透开奖结果
 * Created by scene on 2018/1/8.
 */

public class DLTAwardResultFragment extends BaseBackMvpFragment<IDLTAwardResultView, DLTAwardResultPresenter> implements IDLTAwardResultView {
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

    private List<DLTAwardResultInfo> list = new ArrayList<>();

    public static DLTAwardResultFragment newInstance() {
        Bundle args = new Bundle();
        DLTAwardResultFragment fragment = new DLTAwardResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dlt_award_result, container, false);
        unbinder = ButterKnife.bind(this, view);
        return attachToSwipeBack(view);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        toolbarTitle.setText("大乐透 开奖号码");
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
                        try {
                            ptrLayout.refreshComplete();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, 1000);
            }
        });
        for (int i = 0; i < 10; i++) {
            DLTAwardResultInfo info = new DLTAwardResultInfo();
            info.setShow(i == 0);
            list.add(info);
        }
        final DLTAwardResultAdapter adapter = new DLTAwardResultAdapter(getContext(), list);
        adapter.setOnDLTAwardResultClickListener(new DLTAwardResultAdapter.OnDLTAwardResultClickListener() {
            @Override
            public void onDLTAwardResultClickArrow(int position) {
                list.get(position).setShow(!list.get(position).isShow());
                adapter.notifyDataSetChanged();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
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
    public DLTAwardResultPresenter initPresenter() {
        return new DLTAwardResultPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
