package com.quduo.lottery.ui.index.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.PopupWindow;

import com.quduo.lottery.R;
import com.quduo.lottery.ui.index.adapter.JCZQPlayWayAdapter;
import com.quduo.lottery.ui.index.entity.PlayWayInfo;
import com.quduo.lottery.widgets.CustomeGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 足彩赛事弹窗
 * Created by scene on 2017/12/20.
 */

public class JCZQMatchPopWindow extends PopupWindow {
    private Context context;

    private CustomeGridView gridView;
    private JCZQPlayWayAdapter adapter;
    private OnJCZQButtonClickListener onJCZQButtonClickListener;

    private List<Integer> positions = new ArrayList<>();
    private List<PlayWayInfo> list;

    private boolean isNeedRevent = true;

    public JCZQMatchPopWindow(Context context) {
        super(context);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = inflater.inflate(R.layout.pop_jczq_match, null);
        this.setContentView(mView);
        init();
        initView(mView);
    }

    public void setOnJCZQButtonClickListener(OnJCZQButtonClickListener onJCZQButtonClickListener) {
        this.onJCZQButtonClickListener = onJCZQButtonClickListener;
    }

    private void init() {
        //设置PopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置PopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.pop_animation_top_to_bottom);
        //设置SelectPicPopupWindow弹出窗体的背景
        ColorDrawable dw = new ColorDrawable(0x00000000);
        //设置可以点击外边关闭
        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(dw);
    }

    private void initView(View rootView) {
        String[] sscPlayWays = context.getResources().getStringArray(R.array.jczq_match);
        gridView = rootView.findViewById(R.id.gridView);
        list = new ArrayList<>();
        list.add(new PlayWayInfo(sscPlayWays[0], false, true));
        list.add(new PlayWayInfo(sscPlayWays[1], false, true));
        list.add(new PlayWayInfo(sscPlayWays[2], false, true));
        list.add(new PlayWayInfo(sscPlayWays[3], false, true));
        list.add(new PlayWayInfo(sscPlayWays[4], false, true));
        list.add(new PlayWayInfo(sscPlayWays[5], false, true));
        list.add(new PlayWayInfo(sscPlayWays[6], false, true));

        adapter = new JCZQPlayWayAdapter(context, list);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                list.get(i).setChoosed(!list.get(i).isChoosed());
                adapter.notifyDataSetChanged();
            }
        });
        rootView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        rootView.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                positions.clear();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).isChoosed()) {
                        positions.add(i);
                    }
                }
                onJCZQButtonClickListener.OnConfirmClick(positions);
                isNeedRevent = false;
                dismiss();
            }
        });

        rootView.findViewById(R.id.check_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setChoosed(true);
                }
                adapter.notifyDataSetChanged();
            }
        });
        rootView.findViewById(R.id.check_inverse).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setChoosed(!list.get(i).isChoosed());
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void dismiss() {
        if (isNeedRevent) {
            reventGridView();
        }
        if (this.isShowing()) {
            setBackgroundAlpha(1.0f);
            super.dismiss();
        }
    }

    public void show(View view) {
        setBackgroundAlpha(0.5f);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).isChoosed()) {
                    positions.add(i);
                }
            }
        }
        showAsDropDown(view);
        isNeedRevent = true;
    }

    private void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) context).getWindow().setAttributes(lp);
    }

    public interface OnJCZQButtonClickListener {
        void OnConfirmClick(List<Integer> position);
    }

    private void reventGridView() {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setChoosed(false);
        }
        for (int j = 0; j < positions.size(); j++) {
            list.get(positions.get(j)).setChoosed(true);
        }
        adapter.notifyDataSetChanged();
    }
}
