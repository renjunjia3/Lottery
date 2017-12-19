package com.quduo.lottery.ui.discover.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.quduo.lottery.ui.discover.entity.DiscoverInfo;
import com.quduo.lottery.ui.discover.viewholder.DiscoverViewHolder;
import com.sunfusheng.glideimageview.GlideImageLoader;

import java.util.List;

/**
 * 发现
 * Created by scene on 2017/12/19.
 */

public class DiscoverAdapter extends BaseQuickAdapter<DiscoverInfo, DiscoverViewHolder> {

    public DiscoverAdapter(int layoutResId, @Nullable List<DiscoverInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(DiscoverViewHolder holder, DiscoverInfo item) {
        holder.content.setText(item.getContent());
        GlideImageLoader.create(holder.image).loadLocalImage(item.getImageResId(), 0);
        holder.title.setText(item.getTitle());
    }

}
