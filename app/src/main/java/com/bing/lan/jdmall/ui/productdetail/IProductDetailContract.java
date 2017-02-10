package com.bing.lan.jdmall.ui.productdetail;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

public interface IProductDetailContract {

    interface IProductDetailView
            extends IBaseActivityContract.IBaseActivityView<IProductDetailPresenter> {

    }

    interface IProductDetailPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IProductDetailView, IProductDetailModule> {

    }

    interface IProductDetailModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
