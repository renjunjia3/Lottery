package com.quduo.lottery.ui.index.adapter.jczq;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.quduo.lottery.R;
import com.quduo.lottery.ui.index.entity.JCZQType1ContentInfo;
import com.quduo.lottery.ui.index.entity.JCZQType1HeaderInfo;

import java.util.List;

public class JCZQType5Adapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int TYPE_LEVEL_0 = 0;
    public static final int TYPE_LEVEL_1 = 1;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public JCZQType5Adapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TYPE_LEVEL_0, R.layout.fragment_jczq_item_header);
        addItemType(TYPE_LEVEL_1, R.layout.fragment_jczq_item_content_5);
    }

    @Override
    protected void convert(final BaseViewHolder holder, final MultiItemEntity item) {
        switch (holder.getItemViewType()) {
            case TYPE_LEVEL_0:
                final JCZQType1HeaderInfo headerInfo = (JCZQType1HeaderInfo) item;
                if (headerInfo.isExpanded()) {
                    holder.setImageResource(R.id.header_arrow, R.drawable.ic_arrow_bottom);
                } else {
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
                final JCZQType1ContentInfo contentInfo = (JCZQType1ContentInfo) item;
                holder.setGone(R.id.layout_history, contentInfo.isShowHistory());
                holder.getView(R.id.see_history).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        holder.setGone(R.id.layout_history, !contentInfo.isShowHistory());
                        contentInfo.setShowHistory(!contentInfo.isShowHistory());
                    }
                });
                break;
        }
    }
}
