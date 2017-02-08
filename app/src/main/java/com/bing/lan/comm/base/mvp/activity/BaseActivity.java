package com.bing.lan.comm.base.mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract.IBaseActivityPresenter;
import com.bing.lan.comm.base.mvp.activity.IBaseActivityContract.IBaseActivityView;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.di.ActivityModule;
import com.bing.lan.comm.di.DaggerActivityComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.LogUtil;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 蓝兵
 * @time 2017/1/9  18:36
 */
public abstract class BaseActivity<T extends IBaseActivityPresenter>
        extends AppCompatActivity
        implements IBaseActivityView<T> {

    // protected LogUtil log = LogUtil.getLogUtil(getClass(), 1);
    protected Unbinder mViewBind;

    @Inject
    protected LogUtil log;
    @Inject
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化布局
        initView();
        //启动di
        startInject(getActivityComponent());
        //获取权限
        requestPermissions();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mViewBind != null) {
            mViewBind.unbind();
            mViewBind = null;
        }
        //解绑
        if (mPresenter != null) {
            mPresenter.onDetachView();
        }

        AppUtil.MemoryLeakCheck(this);
    }

    protected void initView() {
        //初始化布局
        setContentView(getLayoutResId());
        //绑定控件
        mViewBind = ButterKnife.bind(this);
    }

    protected abstract int getLayoutResId();

    protected abstract void startInject(ActivityComponent activityComponent);

    /**
     * 权限请求成功时调用
     */
    protected abstract void readyStartPresenter();

    public void requestPermissions() {
        // TODO: 2017/1/12 获取权限的操作
        readyStartPresenter();
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this, getIntent()))
                .build();
    }


    public void startActivity(Class<? extends BaseActivity> clazz, boolean isFinish) {
        AppUtil.startActivity(this, clazz, isFinish);
    }

    /**
     * 默认false
     *
     * @param clazz
     */
    public void startActivity(Class<? extends BaseActivity> clazz) {
        startActivity(clazz, false);
    }


    @Override
    public void showError(String msg, Throwable e) {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(AppUtil.getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog(String msg) {
//        new
    }

    @Override
    public T getPresenter() {
        return mPresenter;
    }
}
