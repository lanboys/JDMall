package com.bing.lan.jdmall.ui.productlist;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;

import static com.bing.lan.jdmall.ui.productlist.ProductListPresenter.BRAND_ACTION;
import static com.bing.lan.jdmall.ui.productlist.ProductListPresenter.PRODUCT_LIST_ACTION;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:12
 */
public class ProductListModule extends BaseActivityModule
        implements IProductListContract.IProductListModule {

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {
        switch (action) {

            case BRAND_ACTION:
                log.d("loadData():BRAND_ACTION " + "加载品牌信息" + parameter);
                break;
            case PRODUCT_LIST_ACTION:
                log.d("loadData(): PRODUCT_LIST_ACTION" + "加载产品列表" + parameter);
                break;
        }
    }
}
