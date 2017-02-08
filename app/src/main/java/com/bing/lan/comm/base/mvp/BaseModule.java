package com.bing.lan.comm.base.mvp;

import android.widget.ImageView;

import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.ImageLoaderManager;
import com.bing.lan.comm.utils.LogUtil;

/**
 * @author 蓝兵
 * @time 2017/1/12  18:48
 */
public abstract class BaseModule  implements IBaseContract.IBaseModule {

    protected LogUtil log = LogUtil.getLogUtil(getClass(), 1);
    public void loadImage(Object path, ImageView imageView) {
        // Picasso.with(AppUtil.getAppContext()).load((String) path).into(imageView);
        //Glide可以加载 path 为object的路径,比图本地资源文件  R.mipmap.ic_launcher
        // Glide.with(AppUtil.getAppContext())
        //         .load(path)
        //         .crossFade()
        //         .into(imageView);

        ImageLoaderManager.loadRefreshImage(AppUtil.getAppContext(), imageView, (String) path);
    }


}
