package com.quduo.lottery.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.quduo.lottery.R;
import com.quduo.lottery.ui.mine.entity.BettingRecordInfo;
import com.quduo.lottery.ui.mine.viewholder.BettingRecordViewHolder;
import com.quduo.lottery.ui.mine.viewholder.ZHMXViewHolder;

import java.util.List;

/**
 * 账户明细
 * Created by scene on 2018/1/5.
 */

public class BettingRecordChildAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<BettingRecordInfo> list;

    public BettingRecordChildAdapter(Context context, List<BettingRecordInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BettingRecordViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_betting_record_child_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        BettingRecordViewHolder holder = (BettingRecordViewHolder) viewHolder;
        BettingRecordInfo info = list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
