package com.bing.lan.jdmall.ui.login;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract;
import com.bing.lan.jdmall.bean.LoginUserInfo;


public interface ILoginContract {

    interface ILoginView<T extends ILoginPresenter>
            extends IBaseActivityContract.IBaseActivityView<T> {

        void goMainActivity();

        void initUserInfo(LoginUserInfo loginUserInfo);
    }

    interface ILoginPresenter<T extends ILoginView, M extends ILoginModule>
            extends IBaseActivityContract.IBaseActivityPresenter<T, M> {

        void login(String mUsername, String mPsd);
    }

    interface ILoginModule extends IBaseActivityContract.IBaseActivityModule {

        void login(int action, String username, String psd,  IBaseContract.OnDataChangerListener listener);

        void queryUserInfo(int action,  IBaseContract.OnDataChangerListener listener);

        void saveLoginUserInfo(String username, String psd);

        void saveUserInfo(Object data);
    }
}
