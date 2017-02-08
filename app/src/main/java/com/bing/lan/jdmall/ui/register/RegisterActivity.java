package com.bing.lan.jdmall.ui.register;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.jdmall.R;

public class RegisterActivity extends BaseActivity<IRegisterContract.IRegisterPresenter>
        implements IRegisterContract.IRegisterView<IRegisterContract.IRegisterPresenter> {


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);

    }

    @Override
    protected void readyStartPresenter() {
        //启动p层逻辑
        mPresenter.onStart();
    }

    @Override
    public void showError(String msg, Throwable e) {

    }
}
