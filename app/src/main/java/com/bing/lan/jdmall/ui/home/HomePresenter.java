package com.bing.lan.jdmall.ui.home;

import android.widget.ImageView;

import com.bing.lan.comm.base.mvp.fragment.BaseFragmentPresenter;
import com.bing.lan.comm.view.LoadPageView;
import com.bing.lan.jdmall.bean.SecKillResultBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:27
 */
public class HomePresenter extends
        BaseFragmentPresenter<IHomeContract.IHomeView, IHomeContract.IHomeModule>
        implements IHomeContract.IHomePresenter {

    private static final int NAVIGATION_BANNER = 1;
    private static final int AD_BANNER = 2;

    private static final int LOAD_BANNER = 1;
    private static final int LOAD_SECKILL = 2;

    @Override
    public void stopUpdate() {

    }

    public void loadImage(Object path, ImageView imageView) {
        mModule.loadImage(path, imageView);
    }

    @Override
    public void onStart() {
        mView.setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_SUCCESS);
        updateData();
    }

    protected void updateData() {
        mModule.loadBanner(LOAD_BANNER, NAVIGATION_BANNER, this);
        mModule.loadSecKill(LOAD_SECKILL,   this);
    }

    @Override
    public void onSuccess(int action, Object data) {
        switch (action) {
            case LOAD_BANNER:
                mView.showBanner(true);
                mView.updateBanner((ArrayList<String>) data);
                break;
            case LOAD_SECKILL:
                mView.updateSecKill((List<SecKillResultBean.SecKillInfoBean.RowsBean>) data);
                break;
        }
    }

    @Override
    public void onError(int action, Throwable e) {

    }
}
