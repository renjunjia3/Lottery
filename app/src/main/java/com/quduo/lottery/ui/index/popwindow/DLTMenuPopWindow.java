package com.quduo.lottery.ui.index.popwindow;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.quduo.lottery.AppConfig;
import com.quduo.lottery.R;

/**
 * 双色球菜单弹窗
 * Created by scene on 2017/12/20.
 */

public class DLTMenuPopWindow extends PopupWindow implements View.OnClickListener {
    private Context context;
    private OnClickMenuListener onClickMenuListener;
    private boolean isXSYL = false;
    private TextView xsyl;

    public DLTMenuPopWindow(Context context) {
        super(context);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View mView = inflater.inflate(R.layout.pop_ssq_toolbar_menu, null);
        this.setContentView(mView);
        init(mView);
        initView();
    }

    public void setOnClickMenuListener(OnClickMenuListener onClickMenuListener) {
        this.onClickMenuListener = onClickMenuListener;
    }

    private void init(View mView) {
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
        xsyl = mView.findViewById(R.id.xsyl);
    }

    private void initView() {
        isXSYL = SPUtils.getInstance().getBoolean(AppConfig.KEY_DLT_XSYL, false);
        xsyl.setOnClickListener(this);
        xsyl.setText(isXSYL ? "隐藏遗漏" : "显示遗漏");
    }

    @Override
    public void dismiss() {
        if (this.isShowing()) {
            setBackgroundAlpha(1.0f);
            super.dismiss();
        }
    }

    public void show(View view) {
        initView();
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
        switch (view.getId()) {
            case R.id.xsyl:
                if (onClickMenuListener != null) {
                    isXSYL = !isXSYL;
                    SPUtils.getInstance().put(AppConfig.KEY_DLT_XSYL, isXSYL);
                    onClickMenuListener.onClickMenuXsyl(isXSYL);
                    dismiss();
                }
                break;
        }
    }

    public interface OnClickMenuListener {
        void onClickMenuXsyl(boolean isXSYL);
    }
}
