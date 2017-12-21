package com.quduo.lottery.ui.index.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quduo.lottery.R;
import com.quduo.lottery.ui.index.entity.PlayWayInfo;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 时时彩玩法
 * Created by scene on 2017/12/20.
 */

public class SSCPlayWayAdapter extends BaseAdapter {
    private Context context;
    private List<PlayWayInfo> list;
    private LayoutInflater inflater;

    public SSCPlayWayAdapter(Context context, List<PlayWayInfo> list) {
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
        PlayWayViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_ssc_play_way_item, viewGroup, false);
            holder = new PlayWayViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (PlayWayViewHolder) view.getTag();
        }
        PlayWayInfo info = list.get(i);
        holder.playWayName.setText(info.getPlayWayName());
        holder.isJiajiang.setVisibility(info.isJiajiang() ? View.VISIBLE : View.GONE);
        holder.isChoosed.setVisibility(info.isChoosed() ? View.VISIBLE : View.GONE);
        return view;
    }

    static class PlayWayViewHolder {
        @BindView(R.id.play_way_name)
        TextView playWayName;
        @BindView(R.id.isJiajiang)
        ImageView isJiajiang;
        @BindView(R.id.isChoosed)
        ImageView isChoosed;

        PlayWayViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
