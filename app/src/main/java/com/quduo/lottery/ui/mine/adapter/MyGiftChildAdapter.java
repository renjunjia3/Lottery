package com.quduo.lottery.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.quduo.lottery.R;
import com.quduo.lottery.ui.mine.entity.MyGiftInfo;
import com.quduo.lottery.ui.mine.viewholder.MyGiftViewHolder;

import java.util.List;

/**
 * 我的优惠券
 * Created by scene on 2018/1/5.
 */

public class MyGiftChildAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<MyGiftInfo> list;

    public MyGiftChildAdapter(Context context, List<MyGiftInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyGiftViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_my_gift_child_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        MyGiftViewHolder holder = (MyGiftViewHolder) viewHolder;
        MyGiftInfo info = list.get(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
