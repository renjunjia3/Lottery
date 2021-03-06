package com.quduo.lottery.http.api;

import com.blankj.utilcode.util.AppUtils;
import com.quduo.lottery.MyApplication;
import com.quduo.lottery.util.MD5Util;
import com.quduo.lottery.util.NetTimeUtils;

import java.util.HashMap;


/**
 * Case By:API
 * package:wiki.scene.shop.http.api
 * Author：scene on 2017/6/27 12:51
 */

public class ApiUtil {
    private static final String SIGN_KEY = "045448f765b0c0592563123a2652fb63";
    public static final String API_PRE = "http://192.168.0.88:8082/";

    public static final String GET_CODE = "common/code";
    public static final String GET_CODE_TAG = "common/code";

    /**
     * Case By:创建参数基础信息
     * Author: scene on 2017/5/19 13:19
     */
    public static HashMap<String, String> createParams() {
        HashMap<String, String> params = new HashMap<>();
        long timestamp = NetTimeUtils.getWebsiteDatetime();
        params.put("resource_id", MyApplication.getInstance().getResourceId());
        params.put("timestamp", timestamp + "");
        params.put("signature", getSign(timestamp + ""));
        params.put("app_type", "1");
        params.put("version", AppUtils.getAppVersionCode() + "");
        return params;
    }

    /**
     * Case By:获取sign
     * Author: scene on 2017/5/19 13:19
     */
    private static String getSign(String timestamp) {
        return MD5Util.string2Md5(MD5Util.string2Md5(1 + MyApplication.getInstance().getResourceId() + timestamp + AppUtils.getAppVersionCode(), "UTF-8") + SIGN_KEY, "UTF-8");
    }
}
