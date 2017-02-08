package com.bing.lan.jdmall.ui.mine;

import com.bing.lan.comm.base.mvp.fragment.IBaseFragmentContract;
import com.bing.lan.jdmall.bean.LoginResultBean;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public interface IMineContract {

    interface IMineView extends IBaseFragmentContract.IBaseFragmentView<IMinePresenter> {

        void updateViewData(LoginResultBean.UserInfoBean userInfoBean);

        void logout();


    }

    interface IMinePresenter extends
            IBaseFragmentContract.IBaseFragmentPresenter<IMineView, IMineModule> {

        void logout();
    }

    interface IMineModule extends IBaseFragmentContract.IBaseFragmentModule {
        void logout();

    }

    //    interface IMineView<T extends IMinePresenter>
//            extends IBaseFragmentContract.IBaseFragmentView<T> {
//
//    }
//
//    interface IMinePresenter<T extends IMineView, M extends IMineModule>
//            extends IBaseFragmentContract.IBaseFragmentPresenter<T, M> {
//
//    }
//
//    interface IMineModule extends IBaseFragmentContract.IBaseFragmentModule {
//
//    }
}
