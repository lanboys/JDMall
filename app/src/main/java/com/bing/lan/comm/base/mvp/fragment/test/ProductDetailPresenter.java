package com.bing.lan.comm.base.mvp.fragment.test;

import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:11
 */
public class ProductDetailPresenter
        extends BaseActivityPresenter<IProductDetailContract.IProductDetailView, IProductDetailContract.IProductDetailModule>
        implements IProductDetailContract.IProductDetailPresenter {

    @Override
    public void onStart() {

    }

    @Override
    public void onSuccess(int action, Object data) {

    }

    @Override
    public void onError(int action, Throwable e) {

    }
}
