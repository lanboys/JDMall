package com.bing.lan.comm.base.mvp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.DaggerFragmentComponent;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.di.FragmentModule;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.comm.view.LoadPageView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author 蓝兵
 * @time 2017/1/10  18:36
 */
public abstract class BaseFragment<T extends IBaseFragmentContract.IBaseFragmentPresenter>
        extends Fragment
        implements IBaseFragmentContract.IBaseFragmentView<T>,
        LoadPageView.OnErrorButtonListener {

    @Inject
    protected LogUtil log/* = LogUtil.getLogUtil(getClass(), 1)*/;
    @Inject
    protected T mPresenter;
    protected LayoutInflater mLayoutInflater;
    private LoadPageView mLoadPage;
    private Unbinder mViewBind;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //启动di,可能第二次执行生命周期,故需要做个非空判断
        if (mPresenter == null) {
            //必须在子类注入,因为要注入的类型是泛型,只有在实现类才能确定
            startInject(getFragmentComponent());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initWindowUI(inflater, container, savedInstanceState);
        return mLoadPage;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //初始化数据
        initData(getActivity().getIntent());
        //准备启动p层逻辑
        readyStartPresenter();
    }

    protected void initData(Intent intent) {

    }

    @Override
    public void onStop() {
        super.onStop();
        //停止更新
        stopUpdate();
    }

    @Override
    public void onDestroy() {
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
     * 停止更新,释放一些正在进行的任务
     */
    public void stopUpdate() {
        if (mPresenter != null)
            mPresenter.stopUpdate();
    }

    @Override
    public void reStartUpdate() {
        if (mPresenter != null)
            mPresenter.reStartUpdate();
    }

    protected abstract void readyStartPresenter();

    private void initWindowUI(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mLoadPage == null) {
            mLayoutInflater = inflater;
            mLoadPage = new LoadPageView(getActivity()) {
                @Override
                protected View initSuccessView(LayoutInflater inflater, LoadPageView parent) {
                    return BaseFragment.this.initSuccessView(inflater, parent);
                }
            };
            //点击错误页面的的加载按钮重新加载
            mLoadPage.setErrorButtonListener(this);
            mViewBind = ButterKnife.bind(this, mLoadPage);
            initView();
        } else {
            ViewGroup parent = (ViewGroup) mLoadPage.getParent();
            if (parent != null) {
                parent.removeView(mLoadPage);
            }
        }

        //绑定空间
        if (mViewBind == null) {
            mViewBind = ButterKnife.bind(this, mLoadPage);
            //            mViewBind = ButterKnife.bind(mLoadPage);
        }
    }

    protected abstract void initView();

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(this, getArguments()))
                .build();
    }

    protected abstract void startInject(FragmentComponent fragmentComponent);

    @Override
    public void setViewState2LoadPage(LoadPageView.LoadDataResult loadDataResult) {
        mLoadPage.setViewState(loadDataResult);
    }

    @Override
    public void resetErrorCount() {
        mLoadPage.resetErrorCount();
    }

    public void showToast(String msg) {
        // TODO: 2017/2/8 记得更新
        Toast.makeText(AppUtil.getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }

    protected View initSuccessView(LayoutInflater layoutInflater, LoadPageView parent) {
        return layoutInflater.inflate(getLayoutResId(), parent, false);
    }

    protected abstract int getLayoutResId();

    @Override
    public void OnErrorButtonClick(View v) {
        //重新获取
        errorReloadData();
    }

    public void startActivity(Class<? extends BaseActivity> clazz, boolean isFinish) {
        AppUtil.startActivity(getActivity(), clazz, isFinish);
    }

    /**
     * 默认false
     *
     * @param clazz
     */
    public void startActivity(Class<? extends BaseActivity> clazz) {
        startActivity(clazz, false);
    }

    /**
     * 加载图片
     *
     * @param path
     * @param imageView
     */
    protected void loadImage(Object path, ImageView imageView) {
        mPresenter.loadImage(path, imageView);
    }

    protected void errorReloadData() {

    }

    public T getPresenter() {
        return mPresenter;
    }

    @Override
    public void showError(String msg, Throwable e) {

    }

    @Override
    public void showDialog(String msg) {

    }

    @Override
    public void updateTitle(String title) {

    }
}
