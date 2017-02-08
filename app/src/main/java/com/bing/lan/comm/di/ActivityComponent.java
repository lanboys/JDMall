package com.bing.lan.comm.di;

import com.bing.lan.jdmall.ui.login.LoginActivity;
import com.bing.lan.jdmall.ui.main.MainActivity;
import com.bing.lan.jdmall.ui.register.RegisterActivity;
import com.bing.lan.jdmall.ui.splash.SplashActivity;

import dagger.Component;

/**
 * @author 蓝兵
 * @time 2017/1/10  11:02
 */
@Component(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(LoginActivity loginActivity);

    void inject(RegisterActivity registerActivity);

    void inject(SplashActivity splashActivity);
}

