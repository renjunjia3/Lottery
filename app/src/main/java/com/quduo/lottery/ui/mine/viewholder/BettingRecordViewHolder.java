package com.quduo.lottery.ui.mine.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.quduo.lottery.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 投注记录
 * Created by scene on 2018/1/5.
 */

public class BettingRecordViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.lottery_name)
    public TextView lotteryName;

    public BettingRecordViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
