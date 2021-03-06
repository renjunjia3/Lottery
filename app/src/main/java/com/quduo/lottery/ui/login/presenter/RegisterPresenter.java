package com.quduo.lottery.ui.login.presenter;

import android.os.CountDownTimer;

import com.lzy.okgo.model.HttpParams;
import com.quduo.lottery.http.listener.HttpResultListener;
import com.quduo.lottery.mvp.BasePresenter;
import com.quduo.lottery.ui.login.model.RegisterModel;
import com.quduo.lottery.ui.login.view.IRegisterView;

/**
 * 注册
 * Created by scene on 2018/1/23.
 */

public class RegisterPresenter extends BasePresenter<IRegisterView> {
    private RegisterModel model;

    public RegisterPresenter(IRegisterView view) {
        this.mView = view;
        this.model = new RegisterModel();
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

}
