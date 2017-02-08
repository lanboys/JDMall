package com.bing.lan.jdmall.ui.login;

import com.bing.lan.comm.base.mvp.activity.BaseActivityPresenter;
import com.bing.lan.jdmall.bean.LoginUserInfo;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:11
 */
public class LoginPresenter
        extends BaseActivityPresenter<ILoginContract.ILoginView, ILoginContract.ILoginModule>
        implements ILoginContract.ILoginPresenter<ILoginContract.ILoginView, ILoginContract.ILoginModule> {


      private static final int LOGIN_ACTION = 1;
      private static final int QUERY_USERINFO_ACTION = 2;


    private String mUsername;
    private String mPsd;

    @Override
    public void onStart() {
        mModule.queryUserInfo(QUERY_USERINFO_ACTION, this);
    }

    public void login(String username, String psd) {
        mUsername = username;
        mPsd = psd;
        mModule.login(LOGIN_ACTION, username, psd, this);
    }

    @Override
    public void onSuccess(int action, Object data) {
        log.d("onSuccess(): " + action + Thread.currentThread().getName());
        switch (action) {
            case QUERY_USERINFO_ACTION:
                mView.initUserInfo((LoginUserInfo) data);
                break;
            case LOGIN_ACTION:
                mModule.saveUserInfo(data);
                mModule.saveLoginUserInfo(mUsername, mPsd);
                mView.goMainActivity();
                break;
        }
    }

    @Override
    public void onError(int action, Throwable e) {

    }
}
