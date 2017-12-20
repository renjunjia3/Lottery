package com.quduo.lottery.ui.index.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.quduo.lottery.R;
import com.quduo.lottery.ui.index.entity.SSQBallInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 双色球红球
 * Created by scene on 2017/12/20.
 */

public class SSQRedAdapter extends BaseAdapter {
    private Context context;
    private List<SSQBallInfo> list;
    private LayoutInflater inflater;

    public SSQRedAdapter(Context context, List<SSQBallInfo> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SSQBlueAdapter.SSQRedViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_ssq_red_item, viewGroup, false);
            holder = new SSQBlueAdapter.SSQRedViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (SSQBlueAdapter.SSQRedViewHolder) view.getTag();
        }
        SSQBallInfo info = list.get(i);
        holder.number.setText(info.getNumber());
        if (info.isCheck()) {
            holder.number.setBackgroundResource(R.drawable.bg_ssq_red_s);
            holder.number.setTextColor(ContextCompat.getColor(context, R.color.white));
        } else {
            holder.number.setBackgroundResource(R.drawable.bg_ssq_red_d);
            holder.number.setTextColor(ContextCompat.getColor(context, R.color.theme_color));
        }
        return view;
    }

    static class SSQRedViewHolder {
        @BindView(R.id.number)
        TextView number;
        @BindView(R.id.miss_time)
        TextView missTime;

        SSQRedViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
