package com.quduo.lottery.ui.index.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.quduo.lottery.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 大乐透开奖记录
 * Created by scene on 2017/12/20.
 */

public class DLTWinCodeAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;
    private LayoutInflater inflater;

    public DLTWinCodeAdapter(Context context, List<String> list) {
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
        WinCodeViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_dlt_wincode_item, viewGroup, false);
            holder = new WinCodeViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (WinCodeViewHolder) view.getTag();
        }
        if (i % 2 == 0) {
            holder.itemView.setBackground(new ColorDrawable(ContextCompat.getColor(context, R.color.white)));
        } else {
            holder.itemView.setBackground(new ColorDrawable(Color.parseColor("#F0F0F0")));
        }
        return view;
    }

    static class WinCodeViewHolder {
        @BindView(R.id.item_view)
        LinearLayout itemView;

        WinCodeViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
