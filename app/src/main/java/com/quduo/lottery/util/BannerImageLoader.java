package com.quduo.lottery.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.quduo.lottery.R;
import com.sunfusheng.glideimageview.GlideImageLoader;
import com.youth.banner.loader.ImageLoader;

/**
 * banner图片加载
 * Created by scene on 2018/1/5.
 */

public class BannerImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide 加载图片简单用法
        Glide.with(context).load(path).into(imageView);
        GlideImageLoader.create(imageView).loadImage((String) path, R.drawable.ic_banner);
    }
}