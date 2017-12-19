package com.quduo.lottery.ui.discover.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.lottery.R;
import com.quduo.lottery.ui.discover.entity.DiscoverInfo;
import com.quduo.lottery.ui.discover.viewholder.DiscoverViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 发现
 * Created by scene on 2017/12/19.
 */

public class DiscoverAdapter extends BaseQuickAdapter<DiscoverInfo,DiscoverViewHolder> {

    public DiscoverAdapter(int layoutResId, @Nullable List<DiscoverInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(DiscoverViewHolder holder, DiscoverInfo item) {
        holder.content.setText(item.getContent());
        holder.image.setImageResource(item.getImageResId());
        holder.title.setText(item.getTitle());
    }

}
