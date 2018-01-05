package com.quduo.lottery.ui.index;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.quduo.lottery.R;
import com.quduo.lottery.event.StartBrotherEvent;
import com.quduo.lottery.mvp.BaseMainMvpFragment;
import com.quduo.lottery.ui.index.presenter.IndexPresenter;
import com.quduo.lottery.ui.index.view.IIndexView;
import com.quduo.lottery.util.BannerImageLoader;
import com.quduo.lottery.widgets.RatioImageView;
import com.sunsky.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import org.greenrobot.eventbus.EventBus;

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
    @BindView(R.id.image1)
    RatioImageView image1;
    @BindView(R.id.layout_zucai_qiudui)
    LinearLayout layoutZucaiQiudui;
    @BindView(R.id.layout_item_ssc)
    LinearLayout layoutItemSsc;
    @BindView(R.id.layout_item_ssq)
    LinearLayout layoutItemSsq;
    @BindView(R.id.layout_item_jczq)
    LinearLayout layoutItemJczq;
    @BindView(R.id.layout_item_jclq)
    LinearLayout layoutItemJclq;
    @BindView(R.id.layout_item_dlt)
    LinearLayout layoutItemDlt;
    @BindView(R.id.layout_item_fc3d)
    LinearLayout layoutItemFc3d;
    @BindView(R.id.gzh)
    TextView gzh;

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
        initBanner();
        bindBanner(null);
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

    private List<String> bannerImageUrls = new ArrayList<>();

    private void initBanner() {
        //设置banner高度
        banner.setImageLoader(new BannerImageLoader());
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setDelayTime(2000);
        banner.setImages(bannerImageUrls);
        banner.start();
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

    @OnClick(R.id.layout_item_ssq)
    public void onClickLayoutItemSSQ() {
        EventBus.getDefault().post(new StartBrotherEvent(SSQFragment.newInstance()));
    }

    @OnClick(R.id.layout_item_ssc)
    public void onClickLayoutItemSSC() {
        EventBus.getDefault().post(new StartBrotherEvent(SSCFragment.newInstance()));
    }

    @OnClick(R.id.layout_item_dlt)
    public void onClickLayoutItemDLT() {
        EventBus.getDefault().post(new StartBrotherEvent(DLTFragment.newInstance()));
    }

    @OnClick(R.id.layout_item_fc3d)
    public void onClickLayoutItemFC3D() {
        EventBus.getDefault().post(new StartBrotherEvent(FC3DFragment.newInstance()));
    }

    @OnClick(R.id.layout_item_jclq)
    public void onClickLayoutItemJCLQ() {
        EventBus.getDefault().post(new StartBrotherEvent(JCLQFragment.newInstance()));
    }

    @OnClick(R.id.layout_item_jczq)
    public void onClickLayoutItemJCZQ() {
        EventBus.getDefault().post(new StartBrotherEvent(JCZQFragment.newInstance()));
    }

    @OnClick(R.id.gzh)
    public void onClickGzh() {
        try {
            String gzhStr = gzh.getText().toString();
            ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            clipboardManager.setPrimaryClip(ClipData.newPlainText(null, gzhStr));
            ToastUtils.showShort("已复制到剪切板：" + gzhStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void bindBanner(List<String> bannerImages) {
        try {
            bannerImageUrls.clear();
            bannerImageUrls.add("http://mycp.iplay78.com/res/activity/4c057081-8639-4642-b788-84b9009b645c.png");
            bannerImageUrls.add("http://mycp.iplay78.com/res/activity/85673495-8a96-4975-b35b-3ed8cb8592d1.png");
            bannerImageUrls.add("http://mycp.iplay78.com/res/activity/968838b8-d27d-4383-b6ec-4e91898d6ee1.jpg");
            bannerImageUrls.add("http://mycp.iplay78.com/res/activity/9ba0454e-e828-4f86-b25f-b09845700d4f.png");
            bannerImageUrls.add("http://mycp.iplay78.com/res/activity/fcad7936-a6a3-45d9-98f3-6635a4f66ff8.jpg");
            banner.setImages(bannerImageUrls);
            banner.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
