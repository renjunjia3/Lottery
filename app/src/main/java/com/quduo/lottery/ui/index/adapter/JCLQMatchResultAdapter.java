package com.quduo.lottery.ui.index.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.quduo.lottery.R;
import com.quduo.lottery.ui.index.entity.JCLQMatchResultHeaderInfo;

import java.util.List;

public class JCLQMatchResultAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public JCLQMatchResultAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.fragment_jclq_match_result_header);
        addItemType(TYPE_LEVEL_1, R.layout.fragment_jclq_match_result_content);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                final JCLQMatchResultHeaderInfo headerInfo = (JCLQMatchResultHeaderInfo) item;
                if (headerInfo.isExpanded()) {
                    holder.setImageResource(R.id.header_arrow, R.drawable.ic_arrow_bottom);
                    holder.setGone(R.id.divider, false);
                } else {
                    holder.setGone(R.id.divider, true);
                    holder.setImageResource(R.id.header_arrow, R.drawable.ic_arrow_top);
                }
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
                        if (headerInfo.isExpanded()) {
                            collapse(pos);
                            holder.setImageResource(R.id.header_arrow, R.drawable.ic_arrow_top);
                        } else {
                            expand(pos);
                            holder.setImageResource(R.id.header_arrow, R.drawable.ic_arrow_bottom);
                        }
                    }
                });
                break;
            case TYPE_LEVEL_1:
                int pos = holder.getAdapterPosition() - getParentPosition(item) - 1;
                holder.setText(R.id.count, "position：" + pos);
                if (pos % 2 == 0) {
                    holder.setBackgroundColor(R.id.base_view, Color.parseColor("#E2E2E2"));
                } else {
                    holder.setBackgroundColor(R.id.base_view, Color.parseColor("#FFFFFF"));
                }
                JCLQMatchResultHeaderInfo headerInfo2 = (JCLQMatchResultHeaderInfo) getData().get(getParentPosition(item));
                holder.setGone(R.id.divider, pos + 1 >= headerInfo2.getSubItems().size() ? true : false);
                break;
        }
    }
}
