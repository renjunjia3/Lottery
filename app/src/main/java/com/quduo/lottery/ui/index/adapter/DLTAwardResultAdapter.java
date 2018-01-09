package com.quduo.lottery.ui.index.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.quduo.lottery.R;
import com.quduo.lottery.ui.index.entity.DLTAwardResultInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 大乐透开奖号码
 * Created by scene on 2018/1/8.
 */

public class DLTAwardResultAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<DLTAwardResultInfo> list;
    private OnDLTAwardResultClickListener onDLTAwardResultClickListener;

    public DLTAwardResultAdapter(Context context, List<DLTAwardResultInfo> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnDLTAwardResultClickListener(OnDLTAwardResultClickListener onDLTAwardResultClickListener) {
        this.onDLTAwardResultClickListener = onDLTAwardResultClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DLTAwardResultViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_dlt_award_result_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        DLTAwardResultViewHolder holder = (DLTAwardResultViewHolder) viewHolder;
        DLTAwardResultInfo info = list.get(position);
        if (info.isShow()) {
            holder.contentLayout.setVisibility(View.VISIBLE);
            holder.arrow.setImageResource(R.drawable.ic_arrow_top);
        } else {
            holder.contentLayout.setVisibility(View.GONE);
            holder.arrow.setImageResource(R.drawable.ic_arrow_bottom);
        }
        holder.headerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onDLTAwardResultClickListener != null) {
                    onDLTAwardResultClickListener.onDLTAwardResultClickArrow(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class DLTAwardResultViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.arrow)
        ImageView arrow;
        @BindView(R.id.content_layout)
        LinearLayout contentLayout;
        @BindView(R.id.header_layout)
        LinearLayout headerLayout;

        DLTAwardResultViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnDLTAwardResultClickListener {
        void onDLTAwardResultClickArrow(int position);
    }
}
