package com.bing.lan.comm.utils;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bing.lan.jdmall.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

public class ImageUtil {

    private static final String TAG = "-->520it";
    private static ImageLoader mImageLoader;
    private static ImageUtil sImageUtil;
    private final DisplayImageOptions mNoLoadingOptions;
    private DisplayImageOptions mOptions;
    private int mDefaultPicId = R.mipmap.ic_launcher;

    private ImageUtil() {

        if (mImageLoader == null) {
            Log.w(TAG, "ImageUtil中prepare方法未被调用,请先调用!");
            throw new RuntimeException("prepare方法未被调用,请先调用!");
        }

        mOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(mDefaultPicId) // 展示一个默认图片
                .cacheInMemory(true) // 是否要进行内存缓存
                .cacheOnDisk(true) // 是否要进行磁盘缓存
                .build();

        mNoLoadingOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true) // 是否要进行内存缓存
                .cacheOnDisk(true) // 是否要进行磁盘缓存
                .build();
    }

    public static void prepare(Context context) {
        // Create global configuration and initialize ImageLoader with this config
        //首次调用进行初始化
        if (mImageLoader == null) {
            File cacheDir = context.getExternalCacheDir();
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                    //后面再来做配置
                    .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                    .memoryCacheSize(2 * 1024 * 1024)
                    .diskCache(new UnlimitedDiskCache(cacheDir)) // default
                    .diskCacheSize(50 * 1024 * 1024)
                    .diskCacheFileCount(100)
                    .build();

            mImageLoader = ImageLoader.getInstance();
            mImageLoader.init(config);
        } else {
            //第二次调用报异常
            throw new RuntimeException("prepare()方法已经被调用过了!");
        }
    }

    public static ImageUtil getInstance() {
        if (sImageUtil == null) {
            synchronized (ImageUtil.class) {
                if (sImageUtil == null) {
                    sImageUtil = new ImageUtil();
                }
            }
        }
        return sImageUtil;
    }

    public void display(String imgUrl, ImageView imageView) {
        display(imgUrl, imageView, R.mipmap.ic_launcher);
    }

    public void displayNoLoading(String imgUrl, ImageView imageView) {
        //如果传过来的默认图片跟先前不一致,就重新给option赋值,让新的设置生效
        mImageLoader.displayImage(imgUrl, imageView, mNoLoadingOptions);
    }

    //加载图片
    public void display(String imgUrl, ImageView imageView, int picRes) {
        //如果传过来的默认图片跟先前不一致,就重新给option赋值,让新的设置生效
        if (mDefaultPicId != picRes) {
            mDefaultPicId = picRes;
            mOptions = new DisplayImageOptions.Builder()
                    .showImageOnLoading(mDefaultPicId) // 展示一个默认图片
                    .cacheInMemory(true) // 是否要进行内存缓存
                    .cacheOnDisk(true) // 是否要进行磁盘缓存
                    .build();
        }
        mImageLoader.displayImage(imgUrl, imageView, mOptions);
    }
}
