package com.quduo.lottery.ui.discover;

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

import com.blankj.utilcode.util.SizeUtils;
import com.quduo.lottery.R;
import com.quduo.lottery.itemDecoration.SpacesItemDecoration;
import com.quduo.lottery.mvp.BaseMainMvpFragment;
import com.quduo.lottery.ui.discover.adapter.DiscoverAdapter;
import com.quduo.lottery.ui.discover.entity.DiscoverInfo;
import com.quduo.lottery.ui.discover.presenter.DiscoverPresenter;
import com.quduo.lottery.ui.discover.view.IDiscoverView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 发现
 * Created by scene on 2017/12/18.
 */

public class DiscoverFragment extends BaseMainMvpFragment<IDiscoverView, DiscoverPresenter> implements IDiscoverView {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;

    private List<DiscoverInfo> list = new ArrayList<>();

    public static DiscoverFragment newInstance() {
        Bundle args = new Bundle();
        DiscoverFragment fragment = new DiscoverFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        toolbarTitle.setText("发现");
        initView();
    }

    @Override
    public void initView() {
        super.initView();
        DiscoverInfo info1 = new DiscoverInfo();
        info1.setImageResId(R.drawable.ic_discover_1);
        info1.setTitle(getString(R.string.discover_title_1));
        info1.setContent(getString(R.string.discover_content_1));
        list.add(info1);
        DiscoverInfo info2 = new DiscoverInfo();
        info2.setImageResId(R.drawable.ic_discover_2);
        info2.setTitle(getString(R.string.discover_title_2));
        info2.setContent(getString(R.string.discover_content_2));
        list.add(info2);
        DiscoverInfo info3 = new DiscoverInfo();
        info3.setImageResId(R.drawable.ic_discover_3);
        info3.setTitle(getString(R.string.discover_title_3));
        info3.setContent(getString(R.string.discover_content_3));
        list.add(info3);
        DiscoverInfo info4 = new DiscoverInfo();
        info4.setImageResId(R.drawable.ic_discover_4);
        info4.setTitle(getString(R.string.discover_title_4));
        info4.setContent(getString(R.string.discover_content_4));
        list.add(info4);
        DiscoverInfo info5 = new DiscoverInfo();
        info5.setImageResId(R.drawable.ic_discover_5);
        info5.setTitle(getString(R.string.discover_title_5));
        info5.setContent(getString(R.string.discover_content_5));
        list.add(info5);
        DiscoverInfo info6 = new DiscoverInfo();
        info6.setImageResId(R.drawable.ic_discover_6);
        info6.setTitle(getString(R.string.discover_title_6));
        info6.setContent(getString(R.string.discover_content_6));
        list.add(info6);
        DiscoverAdapter adapter = new DiscoverAdapter(R.layout.fragment_discover_item, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SpacesItemDecoration(SizeUtils.dp2px(10), true, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showLoadingPage() {

    }

    @Override
    public void showContentPage() {

    }

    @Override
    public void showErrorPage() {

    }

    @Override
    public DiscoverPresenter initPresenter() {
        return new DiscoverPresenter(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
