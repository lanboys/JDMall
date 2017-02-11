package com.bing.lan.jdmall.ui.register;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

import static com.bing.lan.jdmall.ui.productlist.ProductListPresenter.BRAND_ACTION;
import static com.bing.lan.jdmall.ui.productlist.ProductListPresenter.PRODUCT_LIST_ACTION;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:12
 */
public class RegisterModule extends BaseActivityModule
        implements IRegisterContract.IRegisterModule {

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

    }
}
