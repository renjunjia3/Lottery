package com.quduo.lottery.ui.index.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quduo.lottery.R;
import com.quduo.lottery.ui.index.entity.SSCAwardResultInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 双色球开奖号码
 * Created by scene on 2018/1/8.
 */

public class SSCAwardResultAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<SSCAwardResultInfo> list;

    public SSCAwardResultAdapter(Context context, List<SSCAwardResultInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SSCAwardResultViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_ssc_award_result_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        SSCAwardResultViewHolder holder = (SSCAwardResultViewHolder) viewHolder;
        SSCAwardResultInfo info = list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class SSCAwardResultViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cycle_code)
        TextView cycleCode;

        SSCAwardResultViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
