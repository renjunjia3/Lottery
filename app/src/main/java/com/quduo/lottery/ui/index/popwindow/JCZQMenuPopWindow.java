package com.quduo.lottery.ui.index.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.blankj.utilcode.util.SizeUtils;
import com.quduo.lottery.R;

/**
 * 足彩菜单弹窗
 * Created by scene on 2017/12/20.
 */

public class JCZQMenuPopWindow extends PopupWindow implements View.OnClickListener {
    private Context context;
    private OnClickMenuListener onClickMenuListener;

    public JCZQMenuPopWindow(Context context) {
        super(context);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = inflater.inflate(R.layout.pop_jczq_toolbar_menu, null);
        this.setContentView(mView);
        init();
        initView(mView);
    }

    public void setOnClickMenuListener(OnClickMenuListener onClickMenuListener) {
        this.onClickMenuListener = onClickMenuListener;
    }

    private void init() {
        //设置PopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
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

    private void initView(View view) {
        view.findViewById(R.id.kjxx).setOnClickListener(this);
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
        showAsDropDown(view, SizeUtils.dp2px(-25), 0);
    }

    private void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = ((Activity) context).getWindow().getAttributes();
        lp.alpha = bgAlpha;
        ((Activity) context).getWindow().setAttributes(lp);
    }

    @Override
    public void onClick(View view) {
        if (onClickMenuListener == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.kjxx:
                onClickMenuListener.onClickMenuKJXX();
                break;
            case R.id.bfzb:
                onClickMenuListener.onClickMenuBFZB();
                break;
        }
        dismiss();
    }

    public interface OnClickMenuListener {
        void onClickMenuKJXX();

        void onClickMenuBFZB();
    }
}
