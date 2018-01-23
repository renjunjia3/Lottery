package com.quduo.lottery.ui.login.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.quduo.lottery.http.api.ApiUtil;
import com.quduo.lottery.http.base.LzyResponse;
import com.quduo.lottery.http.callback.JsonCallback;
import com.quduo.lottery.http.listener.HttpResultListener;

/**
 * 验证码
 * Created by scene on 2018/1/23.
 */

public class CodeModel {
    public void getRegisterCode(HttpParams params, final HttpResultListener<String> listener) {
        OkGo.<LzyResponse<String>>post(ApiUtil.API_PRE + ApiUtil.GET_CODE)
                .tag(ApiUtil.GET_CODE_TAG)
                .params(params)
                .execute(new JsonCallback<LzyResponse<String>>() {
                    @Override
                    public void onSuccess(Response<LzyResponse<String>> response) {
                        try {
                            if (response.code() == 200) {
                                listener.onSuccess("");
                            } else {
                                listener.onFail(response.body().message);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            listener.onFail("验证码获取失败，请重试");
                        }

                    }

                    @Override
                    public void onError(Response<LzyResponse<String>> response) {
                        super.onError(response);
                        try {
                            listener.onFail(response.getException().getMessage());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        listener.onFinish();
                    }
                });
    }
}
