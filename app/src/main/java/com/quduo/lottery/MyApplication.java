package com.quduo.lottery;

import android.app.Activity;
import android.app.Application;


import com.blankj.utilcode.util.Utils;
import com.quduo.lottery.Exception.core.Recovery;

import java.util.ArrayList;
import java.util.List;

/**
 * application
 * Created by scene on 2016/7/1.
 */
public class MyApplication extends Application {

    //记录当前栈里所有activity
    private List<Activity> activities = new ArrayList<Activity>();
    //记录需要一次性关闭的页面
    private List<Activity> activitys = new ArrayList<Activity>();

    private String resourceId;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化异常管理工具
        Recovery.getInstance()
                .debug(false)//关闭后 在错误统一管理页面不显示异常数据
                .recoverInBackground(true)
                .recoverStack(false)
                .mainPage(WelcomeActivity.class)//恢复页面
                .init(this);
        //初始化工具类
        Utils.init(this);
    }

    /**
     * 应用实例
     **/
    private static MyApplication instance;

    /**
     * 获得实例
     *
     * @return
     */
    public static MyApplication getInstance() {
        return instance;
    }

    /**
     * 新建了一个activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            this.activities.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /*
    *给临时Activitys
    * 添加activity
    * */
    public void addTemActivity(Activity activity) {
        activitys.add(activity);
    }

    public void finishTemActivity(Activity activity) {
        if (activity != null) {
            this.activitys.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /*
    * 退出指定的Activity
    * */
    public void exitActivitys() {
        for (Activity activity : activitys) {
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /**
     * 应用退出，结束所有的activity
     */
    public void exit() {
        for (Activity activity : activities) {
            if (activity != null) {
                activity.finish();
            }
        }
        System.exit(0);
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
