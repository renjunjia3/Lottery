package com.quduo.lottery.ui.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quduo.lottery.R;
import com.quduo.lottery.mvp.BaseMvpFragment;
import com.quduo.lottery.ui.mine.adapter.ZHMXChildAdapter;
import com.quduo.lottery.ui.mine.entity.ZHMXInfo;
import com.quduo.lottery.ui.mine.presenter.ZHMXChildPresenter;
import com.quduo.lottery.ui.mine.view.IZHMXChildView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import wiki.scene.loadmore.PtrClassicFrameLayout;
import wiki.scene.loadmore.PtrDefaultHandler;
import wiki.scene.loadmore.PtrFrameLayout;
import wiki.scene.loadmore.StatusViewLayout;
import wiki.scene.loadmore.recyclerview.RecyclerAdapterWithHF;

/**
 * 账户明细子界面
 * Created by scene on 2018/1/5.
 */

public class ZHMXChildFragment extends BaseMvpFragment<IZHMXChildView, ZHMXChildPresenter> implements IZHMXChildView {
    public static final int TYPE_ALL = 0;
    public static final int TYPE_GET = 1;
    public static final int TYPE_PUT = 2;
    private static final String ARG_TYPE = "type";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptr_layout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.statusView)
    StatusViewLayout statusView;
    Unbinder unbinder;
    private int type = TYPE_ALL;

    private RecyclerAdapterWithHF mAdapter;
    private List<ZHMXInfo> list = new ArrayList<>();

    public static ZHMXChildFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(ARG_TYPE, type);
        ZHMXChildFragment fragment = new ZHMXChildFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt(ARG_TYPE, TYPE_ALL);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zhmx_child, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        initRefreshLayout();
        initView();
        showContentPage();
    }

    private void initRefreshLayout() {
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
                                    ptrLayout.refreshComplete();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }, 1000);
            }
        });
    }

    private void initView() {
        for (int i = 0; i < 5; i++) {
            ZHMXInfo info = new ZHMXInfo();
            if (type == TYPE_ALL) {
                info.setType(i % 2 == 0 ? 1 : 2);
            } else if (type == TYPE_GET) {
                info.setType(1);
            } else {
                info.setType(2);
            }
            list.add(info);
        }
        ZHMXChildAdapter adapter = new ZHMXChildAdapter(getContext(), list);
        mAdapter = new RecyclerAdapterWithHF(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
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
    public ZHMXChildPresenter initPresenter() {
        return new ZHMXChildPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
