package com.bing.lan.jdmall.ui.productlist;

import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:11
 */
public class ProductListPresenter
        extends BaseActivityPresenter<IProductListContract.IProductListView,
        IProductListContract.IProductListModule>
        implements IProductListContract.IProductListPresenter {

    public static final int BRAND_ACTION = 11;
    public static final int PRODUCT_LIST_ACTION = 12;

    @Override
    public void onStart(Object... params) {
        loadData(BRAND_ACTION, params);
    }

    @Override
    public void loadData(int action, Object... parameter) {
        mModule.loadData(action, this, parameter);
    }

    @Override
    public void onSuccess(int action, Object data) {

    }

    @Override
    public void onError(int action, Throwable e) {

    }
}
