package com.quduo.lottery.ui.index.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.quduo.lottery.R;
import com.quduo.lottery.ui.index.entity.SSQAwardResultInfo;
import com.quduo.lottery.ui.index.entity.SSQAwardResultInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 双色球开奖号码
 * Created by scene on 2018/1/8.
 */

public class SSQAwardResultAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<SSQAwardResultInfo> list;
    private OnSSQAwardResultClickListener onSSQAwardResultClickListener;

    public SSQAwardResultAdapter(Context context, List<SSQAwardResultInfo> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnSSQAwardResultClickListener(OnSSQAwardResultClickListener onSSQAwardResultClickListener) {
        this.onSSQAwardResultClickListener = onSSQAwardResultClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SSQAwardResultViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_ssq_award_result_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        SSQAwardResultViewHolder holder = (SSQAwardResultViewHolder) viewHolder;
        SSQAwardResultInfo info = list.get(position);
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
                if (onSSQAwardResultClickListener != null) {
                    onSSQAwardResultClickListener.onSSQAwardResultClickArrow(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class SSQAwardResultViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.arrow)
        ImageView arrow;
        @BindView(R.id.content_layout)
        LinearLayout contentLayout;
        @BindView(R.id.header_layout)
        LinearLayout headerLayout;

        SSQAwardResultViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnSSQAwardResultClickListener {
        void onSSQAwardResultClickArrow(int position);
    }
}
