package com.bing.lan.jdmall.ui.splash;

import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:11
 */
public class SplashPresenter
        extends BaseActivityPresenter<ISplashContract.ISplashView, ISplashContract.ISplashModule>
        implements ISplashContract.ISplashPresenter<ISplashContract.ISplashView, ISplashContract.ISplashModule> {

    @Override
    public void onStart() {
        mView.startAnimation();
    }

    public void animationFinished() {
        mView.animationFinished();
    }

    @Override
    public void onSuccess(int action, Object data) {

    }

    @Override
    public void onError(int action, Throwable e) {

    }
}
