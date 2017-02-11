package com.bing.lan.jdmall.ui.productlist;

import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;
import com.bing.lan.jdmall.bean.BrandResultBean;
import com.bing.lan.jdmall.bean.ProductListResultBean;

import java.util.List;

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
        loadData(PRODUCT_LIST_ACTION, params);
    }

    @Override
    public void loadData(int action, Object... parameter) {
        mModule.loadData(action, this, parameter);
    }

    @Override
    public void onSuccess(int action, Object data) {
        switch (action) {
            case BRAND_ACTION:
                mView.updateBrandGridView((List<BrandResultBean.BrandInfo>) data);
                break;
            case PRODUCT_LIST_ACTION:
                mView.updateProductList((List<ProductListResultBean.ProductListInfo.ProductInfo>) data);
                break;
        }
    }

    @Override
    public void onError(int action, Throwable e) {

    }
}
