package com.bing.lan.jdmall.ui.productdetail.details;

import com.bing.lan.comm.base.mvp.fragment.IBaseFragmentContract;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public interface IProductDetailContract {

    interface IProductDetailView extends IBaseFragmentContract.IBaseFragmentView<IProductDetailPresenter> {

    }

    interface IProductDetailPresenter extends
            IBaseFragmentContract.IBaseFragmentPresenter<IProductDetailView, IProductDetailModule> {

    }

    interface IProductDetailModule extends IBaseFragmentContract.IBaseFragmentModule {

    }
}
