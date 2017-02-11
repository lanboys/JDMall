package com.bing.lan.jdmall.ui.productlist;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

public interface IProductListContract {

    interface IProductListView
            extends IBaseActivityContract.IBaseActivityView<IProductListPresenter> {

    }

    interface IProductListPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IProductListView, IProductListModule> {

    }

    interface IProductListModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
