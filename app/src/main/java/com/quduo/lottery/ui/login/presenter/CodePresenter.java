package com.quduo.lottery.ui.login.presenter;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;

import com.lzy.okgo.model.HttpParams;
import com.quduo.lottery.R;
import com.quduo.lottery.http.listener.HttpResultListener;
import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.login.model.CodeModel;
import com.quduo.lottery.ui.login.view.ICodeView;

/**
 * 验证码
 * Created by scene on 2018/1/23.
 */

public class CodePresenter extends BasePresenter<ICodeView> {
    private CodeModel model;

    public CodePresenter(ICodeView view) {
        this.mView = view;
        this.model = new CodeModel();
    }

    public void getCodeData() {
        try {
            mView.showDialog();
            HttpParams params = new HttpParams();
            params.put("type", "reg");
            params.put("mobile", mView.getPhoneNumber());
            model.getRegisterCode(params, new HttpResultListener<String>() {
                @Override
                public void onSuccess(String data) {
                    try {
                        mView.getCodeSuccess();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFail(String message) {
                    try {
                        mView.getCodeFail(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFinish() {
                    try {
                        mView.hideDialog();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showCountDownTimer(final Context context) {
        try {
            mView.updateResendText("60秒后重发");
            mView.updateResendClickable(false);
            mView.updateResendTextColor(ContextCompat.getColor(context, R.color.text_color_des));
            new CountDownTimer(60000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mView.updateResendText((millisUntilFinished / 1000) + "秒后重发");
                }

                @Override
                public void onFinish() {
                    mView.updateResendText("重新发送验证码");
                    mView.updateResendClickable(true);
                    mView.updateResendTextColor(ContextCompat.getColor(context, R.color.blue_text_color));
                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
