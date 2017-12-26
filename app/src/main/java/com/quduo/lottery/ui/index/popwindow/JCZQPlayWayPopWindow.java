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

import com.blankj.utilcode.util.SPUtils;
import com.quduo.lottery.AppConfig;
import com.quduo.lottery.R;
import com.quduo.lottery.ui.index.adapter.SSCPlayWayAdapter;
import com.quduo.lottery.ui.index.entity.PlayWayInfo;
import com.quduo.lottery.widgets.CustomeGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * 时时彩玩法弹窗
 * Created by scene on 2017/12/20.
 */

public class JCZQPlayWayPopWindow extends PopupWindow {
    private Context context;

    private CustomeGridView gridView;
    private SSCPlayWayAdapter adapter;
    private OnJCZQPlayWayItemClickListener onJCZQPlayWayItemClickListener;

    public JCZQPlayWayPopWindow(Context context) {
        super(context);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = inflater.inflate(R.layout.pop_ssc_play_way, null);
        this.setContentView(mView);
        init();
        initView(mView);
    }

    public void setOnJCZQPlayWayItemClickListener(OnJCZQPlayWayItemClickListener onJCZQPlayWayItemClickListener) {
        this.onJCZQPlayWayItemClickListener = onJCZQPlayWayItemClickListener;
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
        String[] sscPlayWays = context.getResources().getStringArray(R.array.jczq_play_way);
        gridView = rootView.findViewById(R.id.gridView);
        final List<PlayWayInfo> list = new ArrayList<>();
        list.add(new PlayWayInfo(sscPlayWays[0], false, false));
        list.add(new PlayWayInfo(sscPlayWays[1], false, false));
        list.add(new PlayWayInfo(sscPlayWays[2], false, false));
        list.add(new PlayWayInfo(sscPlayWays[3], false, false));
        list.add(new PlayWayInfo(sscPlayWays[4], false, false));
        list.add(new PlayWayInfo(sscPlayWays[5], false, false));
        list.add(new PlayWayInfo(sscPlayWays[6], false, false));
        list.add(new PlayWayInfo(sscPlayWays[7], false, false));
        list.add(new PlayWayInfo(sscPlayWays[8], false, false));
        final int defaultPayWayPosition = SPUtils.getInstance().getInt(AppConfig.KEY_JCZQ_DEFAULT_PLAY_WAY_POSITION, 0);
        list.get(defaultPayWayPosition).setChoosed(true);

        adapter = new SSCPlayWayAdapter(context, list);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!list.get(i).isChoosed()) {
                    for (int j = 0; j < list.size(); j++) {
                        list.get(j).setChoosed(false);
                    }
                }
                list.get(i).setChoosed(true);
                adapter.notifyDataSetChanged();
                SPUtils.getInstance().put(AppConfig.KEY_JCZQ_DEFAULT_PLAY_WAY_POSITION, i);
                dismiss();
                if (onJCZQPlayWayItemClickListener != null) {
                    onJCZQPlayWayItemClickListener.OnJCZQPlayWayItemClick(i);
                }
            }
        });
    }

    @Override
    public void dismiss() {
        if (this.isShowing()) {
            setBackgroundAlpha(1.0f);
            super.dismiss();
        }
    }

    public void show(View view) {
        setBackgroundAlpha(0.5f);
        showAsDropDown(view);
    }

    private void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) context).getWindow().setAttributes(lp);
    }

    public interface OnJCZQPlayWayItemClickListener {
        void OnJCZQPlayWayItemClick(int position);
    }

}
