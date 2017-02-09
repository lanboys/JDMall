package com.bing.lan.comm.base.mvp.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
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
    /**
     * 沉浸式标志
     */
    private boolean isImmersion;
    /**
     * 状态栏透明
     */
    private boolean isTranslucentStatus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //透明状态栏
        initTranslucentStatus();
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

    /**
     * 请求状态栏透明
     */
    protected boolean isTranslucentStatus() {
        return true;
    }

    /**
     * 请求沉浸式
     */
    protected boolean isImmersion() {
        // isImmersion = true;
        return false;
    }

    private void initTranslucentStatus() {

        if (!isTranslucentStatus())
            return;

        //借用沉浸式来实现透明状态栏
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            //1.设置界面布局全屏
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE  //具体不知道什么用
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY     //实现触摸显示状态栏.导航栏
                            // | View.SYSTEM_UI_FLAG_FULLSCREEN //隐藏状态栏
                            // | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  //隐藏导航栏
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN     //将界面伸到状态栏下面,不隐藏状态栏
                    // | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION //将界面伸到导航栏下面,不隐藏导航栏
            );

            //2.将StatusBar颜色改为透明(注意不是系统StatusBar,系统的在21时,默认为透明了)
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            // getWindow().setStatusBarColor(Color.BLACK);
            return;
        }
        //透明状态栏
        if (Build.VERSION.SDK_INT >= 19) {

            // api 19  1.系统StatusBar渐变半透明
            //         2.界面伸到系统StatusBar下面

            // api 21  1.系统StatusBar灰色透明(类似沉浸式效果颜色)
            //         2.隐藏非系统StatusBar(不会更改颜色)
            //         3.故,界面伸到系统StatusBar下面
            //         4.非全透明,故用上面的方法实现
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // TODO: 2017/2/9 添加一个view 放在 状态栏下面
        }
    }

    private void initImmersion(boolean hasFocus) {
        if (!isImmersion())
            return;
        //必须在这个方法中请求,否则从其他app跳转过来后,沉浸式效果就消失了
        // 系统StatusBar 灰色透明 ,自动隐藏,轻触就显示出来
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE  //具体不知道什么用
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY     //实现触摸显示状态栏.导航栏
                            | View.SYSTEM_UI_FLAG_FULLSCREEN //隐藏状态栏
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION  //隐藏导航栏
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN     //将界面伸到状态栏下面,不隐藏状态栏
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION //将界面伸到导航栏下面,不隐藏导航栏
            );
        }
    }

    /**
     * 获取焦点或失去焦点时调用
     *
     * @param hasFocus 获取焦点返回true,否则返回false
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        initImmersion(hasFocus);
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
