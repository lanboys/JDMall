package com.bing.lan.jdmall.ui.register;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;



public interface IRegisterContract {

    interface IRegisterView<T extends IRegisterPresenter> extends IBaseActivityContract.IBaseActivityView<T> {

    }

    interface IRegisterPresenter<T extends IRegisterView, M extends IRegisterModule>
            extends IBaseActivityContract.IBaseActivityPresenter<T, M> {

    }

    interface IRegisterModule extends IBaseActivityContract.IBaseActivityModule {

    }
}
