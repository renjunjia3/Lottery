package com.quduo.lottery.ui.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SizeUtils;
import com.quduo.lottery.R;
import com.quduo.lottery.itemDecoration.SpacesItemDecoration;
import com.quduo.lottery.mvp.BaseMvpFragment;
import com.quduo.lottery.ui.mine.adapter.MyGiftChildAdapter;
import com.quduo.lottery.ui.mine.entity.MyGiftInfo;
import com.quduo.lottery.ui.mine.presenter.MyGiftChildPresenter;
import com.quduo.lottery.ui.mine.view.IMyGiftChildView;

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

public class MyGiftChildFragment extends BaseMvpFragment<IMyGiftChildView, MyGiftChildPresenter> implements IMyGiftChildView {
    public static final int TYPE_UNUSE = 0;
    public static final int TYPE_USED = 1;
    public static final int TYPE_OVERDUE = 2;
    private static final String ARG_TYPE = "type";
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ptr_layout)
    PtrClassicFrameLayout ptrLayout;
    @BindView(R.id.statusView)
    StatusViewLayout statusView;
    Unbinder unbinder;
    private int type = TYPE_UNUSE;

    private RecyclerAdapterWithHF mAdapter;
    private List<MyGiftInfo> list = new ArrayList<>();

    public static MyGiftChildFragment newInstance(int type) {
        Bundle args = new Bundle();
        args.putInt(ARG_TYPE, type);
        MyGiftChildFragment fragment = new MyGiftChildFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getInt(ARG_TYPE, TYPE_UNUSE);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_gift_child, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
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
            MyGiftInfo info = new MyGiftInfo();
            info.setType(i);
            list.add(info);
        }
        MyGiftChildAdapter adapter = new MyGiftChildAdapter(getContext(), list);
        mAdapter = new RecyclerAdapterWithHF(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SpacesItemDecoration(SizeUtils.dp2px(10), true, true));
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
    public MyGiftChildPresenter initPresenter() {
        return new MyGiftChildPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
