package com.quduo.lottery.ui.mine.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.quduo.lottery.R;
import com.quduo.lottery.ui.mine.entity.ZHMXInfo;
import com.quduo.lottery.ui.mine.viewholder.ZHMXViewHolder;

import java.util.List;

/**
 * 账户明细
 * Created by scene on 2018/1/5.
 */

public class ZHMXChildAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ZHMXInfo> list;

    public ZHMXChildAdapter(Context context, List<ZHMXInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ZHMXViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_zhmx_child_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ZHMXViewHolder holder = (ZHMXViewHolder) viewHolder;
        ZHMXInfo info = list.get(position);
        if (info.getType() == 1) {
            holder.type.setText("购彩金");
            holder.money.setTextColor(Color.parseColor("#EE322B"));
        } else {
            holder.type.setText("投注");
            holder.money.setTextColor(Color.parseColor("#272727"));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
