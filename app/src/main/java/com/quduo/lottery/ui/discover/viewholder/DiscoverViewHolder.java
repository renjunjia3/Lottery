package com.quduo.lottery.ui.discover.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.quduo.lottery.R;

import butterknife.BindView;

public class DiscoverViewHolder extends BaseViewHolder {
    @BindView(R.id.image)
    public ImageView image;
    @BindView(R.id.title)
    public TextView title;
    @BindView(R.id.content)
    public TextView content;

    DiscoverViewHolder(View view) {
        super(view);
        image = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        content = view.findViewById(R.id.content);
//            ButterKnife.bind(this, view);
    }
}