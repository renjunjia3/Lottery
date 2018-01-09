package com.quduo.lottery.ui.index.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.quduo.lottery.R;
import com.quduo.lottery.ui.index.entity.FC3DAwardResultInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 福彩3D开奖号码
 * Created by scene on 2018/1/8.
 */

public class FC3DAwardResultAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<FC3DAwardResultInfo> list;
    private OnFC3DAwardResultClickListener onFC3DAwardResultClickListener;

    public FC3DAwardResultAdapter(Context context, List<FC3DAwardResultInfo> list) {
        this.context = context;
        this.list = list;
    }

    public void setOnFC3DAwardResultClickListener(OnFC3DAwardResultClickListener onFC3DAwardResultClickListener) {
        this.onFC3DAwardResultClickListener = onFC3DAwardResultClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FC3DAwardResultViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_fc3d_award_result_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        FC3DAwardResultViewHolder holder = (FC3DAwardResultViewHolder) viewHolder;
        FC3DAwardResultInfo info = list.get(position);
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
                if (onFC3DAwardResultClickListener != null) {
                    onFC3DAwardResultClickListener.onFC3DAwardResultClickArrow(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class FC3DAwardResultViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.arrow)
        ImageView arrow;
        @BindView(R.id.content_layout)
        LinearLayout contentLayout;
        @BindView(R.id.header_layout)
        LinearLayout headerLayout;

        FC3DAwardResultViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnFC3DAwardResultClickListener {
        void onFC3DAwardResultClickArrow(int position);
    }
}
