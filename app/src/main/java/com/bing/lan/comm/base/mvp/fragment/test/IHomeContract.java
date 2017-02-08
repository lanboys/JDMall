package com.bing.lan.comm.base.mvp.fragment.test;

import com.bing.lan.comm.base.mvp.fragment.IBaseFragmentContract;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public interface IHomeContract {

    interface IHomeView extends IBaseFragmentContract.IBaseFragmentView<IHomePresenter> {
    }

    interface IHomePresenter extends
            IBaseFragmentContract.IBaseFragmentPresenter<IHomeView, IHomeModule> {
    }

    interface IHomeModule extends IBaseFragmentContract.IBaseFragmentModule {
    }
}
