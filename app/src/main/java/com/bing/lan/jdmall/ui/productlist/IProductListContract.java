package com.bing.lan.jdmall.ui.productlist;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;
import com.bing.lan.jdmall.bean.BrandResultBean;
import com.bing.lan.jdmall.bean.ProductListResultBean;

import java.util.List;

public interface IProductListContract {

    interface IProductListView
            extends IBaseActivityContract.IBaseActivityView<IProductListPresenter> {

        void updateProductList(List<ProductListResultBean.ProductListInfo.ProductInfo> datas);

        void updateBrandGridView(List<BrandResultBean.BrandInfo> datas);
    }

    interface IProductListPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IProductListView, IProductListModule> {

    }

    interface IProductListModule
            extends IBaseActivityContract.IBaseActivityModule {

        void loadBrand(int action, IBaseContract.OnDataChangerListener listener, int categoryId);

        void loadProductList(int action, IBaseContract.OnDataChangerListener listener, SProductListParams paramsBean);
    }
}
