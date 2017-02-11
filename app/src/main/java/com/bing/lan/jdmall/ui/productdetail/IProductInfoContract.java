package com.bing.lan.jdmall.ui.productdetail;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;

public interface IProductInfoContract {

    interface IProductInfoView
            extends IBaseActivityContract.IBaseActivityView<IProductInfoPresenter> {

    }

    interface IProductInfoPresenter
            extends IBaseActivityContract.IBaseActivityPresenter<IProductInfoView, IProductInfoModule> {

    }

    interface IProductInfoModule
            extends IBaseActivityContract.IBaseActivityModule {

    }
}
