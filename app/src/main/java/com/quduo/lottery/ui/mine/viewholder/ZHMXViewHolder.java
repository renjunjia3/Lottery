package com.quduo.lottery.ui.mine.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.quduo.lottery.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 账户明细
 * Created by scene on 2018/1/5.
 */

public class ZHMXViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.type)
    public TextView type;
    @BindView(R.id.money)
    public TextView money;

    public ZHMXViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
