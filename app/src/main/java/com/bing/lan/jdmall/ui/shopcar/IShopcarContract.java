package com.bing.lan.jdmall.ui.shopcar;

import com.bing.lan.comm.base.mvp.fragment.IBaseFragmentContract;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public interface IShopcarContract {

    interface IShopcarView extends IBaseFragmentContract.IBaseFragmentView<IShopcarPresenter> {



    }

    interface IShopcarPresenter extends
            IBaseFragmentContract.IBaseFragmentPresenter<IShopcarView, IShopcarModule> {


    }

    interface IShopcarModule extends IBaseFragmentContract.IBaseFragmentModule {


    }


}
