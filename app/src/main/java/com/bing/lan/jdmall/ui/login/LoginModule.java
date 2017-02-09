package com.bing.lan.jdmall.ui.login;

import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.api.ApiService;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.jdmall.bean.LoginResultBean;
import com.bing.lan.jdmall.bean.LoginUserInfo;
import com.bing.lan.jdmall.cons.Constants;
import com.bing.lan.jdmall.db.JDUserInfoDao;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:12
 */
public class LoginModule extends BaseActivityModule implements ILoginContract.ILoginModule {


    private ApiService mApiService;

    public LoginModule() {
        mApiService = ApiManager.getApiService();
    }

    @Override
    public void login(final int action, String username, String psd, final IBaseContract.OnDataChangerListener listener) {

        mApiService.login(username, psd)
                .filter(new Func1<LoginResultBean, Boolean>() {
                    @Override
                    public Boolean call(LoginResultBean data) {
                        if (data.isSuccess()) {
                            return true;
                        } else {
                            throw new RuntimeException(data.getErrorMsg());
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginResultBean>() {
                    @Override
                    public void onNext(LoginResultBean data) {
                        listener.onSuccess(action, data.getResult());
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(action, e);
                        // log.e("OnError: 加载数据失败 ", e);
                        log.e("onError(): 加载数据失败 " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onCompleted() {

                    }
                });
    }

    @Override
    public void queryUserInfo(int action, IBaseContract.OnDataChangerListener listener) {
        LoginUserInfo loginUserInfo;
        //先从内存中取
        loginUserInfo = AppUtil.getGlobal(Constants.USER_LOGIN_INFO);
        if (loginUserInfo == null) {
            //数据库中取
            loginUserInfo = JDUserInfoDao.queryUserInfo();
        }
        if (loginUserInfo != null) {
            listener.onSuccess(action, loginUserInfo);
        }

        log.d("queryUserInfo(): " + loginUserInfo);
    }

    @Override
    public void saveLoginUserInfo(String username, String psd) {

        JDUserInfoDao.deleteAllLoginUserInfo();
        LoginUserInfo loginUserInfo = new LoginUserInfo(username, psd);
        //存到数据库
        JDUserInfoDao.saveLoginUserInfo(loginUserInfo);
        log.i("saveLoginUserInfo():数据库中用户信息条数 " + JDUserInfoDao.queryUserInfoCount());
        //存到内存
        AppUtil.putGlobal(Constants.USER_LOGIN_INFO, loginUserInfo);
    }

    @Override
    public void saveUserInfo(Object data) {
        AppUtil.putGlobal(Constants.USER_INFO, data);
    }

    //    interface OnDataChangerListener {
    //
    //        void onSuccess(int action, Object data);
    //
    //        void onError(int action, Throwable e);
    //    }
}
