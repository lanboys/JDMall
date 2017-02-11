package com.bing.lan.comm.di;

import android.app.Activity;
import android.content.Intent;

import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.jdmall.ui.login.ILoginContract;
import com.bing.lan.jdmall.ui.login.LoginActivity;
import com.bing.lan.jdmall.ui.login.LoginModule;
import com.bing.lan.jdmall.ui.login.LoginPresenter;
import com.bing.lan.jdmall.ui.main.IMainContract;
import com.bing.lan.jdmall.ui.main.MainActivity;
import com.bing.lan.jdmall.ui.main.MainModule;
import com.bing.lan.jdmall.ui.main.MainPresenter;
import com.bing.lan.jdmall.ui.productdetail.IProductInfoContract;
import com.bing.lan.jdmall.ui.productdetail.ProductInfoActivity;
import com.bing.lan.jdmall.ui.productdetail.ProductInfoModule;
import com.bing.lan.jdmall.ui.productdetail.ProductInfoPresenter;
import com.bing.lan.jdmall.ui.productlist.IProductListContract;
import com.bing.lan.jdmall.ui.productlist.ProductListActivity;
import com.bing.lan.jdmall.ui.productlist.ProductListModule;
import com.bing.lan.jdmall.ui.productlist.ProductListPresenter;
import com.bing.lan.jdmall.ui.register.IRegisterContract;
import com.bing.lan.jdmall.ui.register.RegisterActivity;
import com.bing.lan.jdmall.ui.register.RegisterModule;
import com.bing.lan.jdmall.ui.register.RegisterPresenter;
import com.bing.lan.jdmall.ui.splash.ISplashContract;
import com.bing.lan.jdmall.ui.splash.SplashActivity;
import com.bing.lan.jdmall.ui.splash.SplashModule;
import com.bing.lan.jdmall.ui.splash.SplashPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * @author 蓝兵
 * @time 2017/1/10  10:51
 */
@Module
public class ActivityModule {

    private Activity mActivity;
    private Intent mIntent;

    // protected LogUtil log = LogUtil.getLogUtil(getClass(), 1);

    public ActivityModule(Activity activity, Intent intent) {
        this.mActivity = activity;
        this.mIntent = intent;
    }

    /**
     * 注入的类型必须完全一致
     *
     * @return
     */
    @Provides
    public ISplashContract.ISplashPresenter provideSplashPresenter() {
        SplashPresenter splashPresenter = new SplashPresenter();
        splashPresenter.setModule(new SplashModule());
        splashPresenter.onAttachView((SplashActivity) mActivity);
        return splashPresenter;
    }

    @Provides
    public ILoginContract.ILoginPresenter provideLoginPresenter() {
        LoginPresenter loginPresenter = new LoginPresenter();
        loginPresenter.setModule(new LoginModule());
        loginPresenter.onAttachView((LoginActivity) mActivity);
        return loginPresenter;
    }

    @Provides
    public IRegisterContract.IRegisterPresenter provideRegisterPresenter() {
        RegisterPresenter registerPresenter = new RegisterPresenter();
        registerPresenter.setModule(new RegisterModule());
        registerPresenter.onAttachView((RegisterActivity) mActivity);
        return registerPresenter;
    }

    @Provides
    public IMainContract.IMainPresenter provideMainPresenter() {
        MainPresenter loginPresenter = new MainPresenter();
        loginPresenter.setModule(new MainModule());
        loginPresenter.onAttachView((MainActivity) mActivity);
        return loginPresenter;
    }

    @Provides
    public IProductInfoContract.IProductInfoPresenter provideProductInfoPresenter() {
        ProductInfoPresenter productInfoPresenter = new ProductInfoPresenter();
        productInfoPresenter.setModule(new ProductInfoModule());
        productInfoPresenter.onAttachView((ProductInfoActivity) mActivity);
        return productInfoPresenter;
    }

    @Provides
    public IProductListContract.IProductListPresenter provideProductListPresenter() {
        ProductListPresenter productListPresenter = new ProductListPresenter();
        productListPresenter.setModule(new ProductListModule());
        productListPresenter.onAttachView((ProductListActivity) mActivity);
        return productListPresenter;
    }

    @Provides
    public LogUtil provideLogCat() {
        return LogUtil.getLogUtil(mActivity.getClass(), 1);
    }
}
