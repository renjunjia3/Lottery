package com.quduo.lottery.ui.discover.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.lottery.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoverViewHolder extends BaseViewHolder {
    @BindView(R.id.image)
    public ImageView image;
    @BindView(R.id.title)
    public TextView title;
    @BindView(R.id.content)
    public TextView content;

    DiscoverViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}