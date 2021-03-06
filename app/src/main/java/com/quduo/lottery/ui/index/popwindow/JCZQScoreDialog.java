package com.quduo.lottery.ui.index.popwindow;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.quduo.lottery.R;

import wiki.scene.loadmore.utils.PtrLocalDisplay;

/**
 * 比分
 * Created by scene on 2017/12/26.
 */

public class JCZQScoreDialog extends Dialog {
    public JCZQScoreDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void show() {
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = (int) (PtrLocalDisplay.SCREEN_WIDTH_PIXELS * 0.9f); // 宽度
        //lp.height = (int) (PtrLocalDisplay.SCREEN_HEIGHT_PIXELS * 0.85f);
        dialogWindow.setAttributes(lp);
        super.show();
    }

    public JCZQScoreDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected JCZQScoreDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public static class Builder {
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        public JCZQScoreDialog create() {
            LayoutInflater inflater = LayoutInflater.from(context);
            final JCZQScoreDialog dialog = new JCZQScoreDialog(context, R.style.Dialog);
            View rootView = inflater.inflate(R.layout.dialog_jczq_score, null);
            dialog.addContentView(rootView, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            dialog.setContentView(rootView);
            dialog.setCanceledOnTouchOutside(false);
            rootView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                }
            });
            rootView.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                }
            });
            return dialog;
        }
    }
}
