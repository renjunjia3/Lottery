package com.quduo.lottery.ui.index.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
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

public class JCZQPlayWayAdapter extends BaseAdapter {
    private Context context;
    private List<PlayWayInfo> list;
    private LayoutInflater inflater;

    public JCZQPlayWayAdapter(Context context, List<PlayWayInfo> list) {
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
            view = inflater.inflate(R.layout.pop_jczq_match_item, viewGroup, false);
            holder = new PlayWayViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (PlayWayViewHolder) view.getTag();
        }
        PlayWayInfo info = list.get(i);
        holder.playWayName.setText(info.getPlayWayName());
        holder.playWayName.setTextColor(info.isChoosed() ? ContextCompat.getColor(context, R.color.theme_color) : Color.parseColor("#CCCCCC"));
        holder.playWayName.setBackgroundResource(info.isChoosed() ? R.drawable.bg_red_stoke_0dp : R.drawable.bg_gray_stoke_0dp);
        return view;
    }

    static class PlayWayViewHolder {
        @BindView(R.id.play_way_name)
        TextView playWayName;

        PlayWayViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
