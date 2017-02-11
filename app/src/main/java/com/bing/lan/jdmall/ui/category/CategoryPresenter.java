package com.bing.lan.jdmall.ui.category;

import com.bing.lan.comm.base.mvp.fragment.BaseFragmentPresenter;
import com.bing.lan.comm.view.LoadPageView;
import com.bing.lan.jdmall.bean.CategoryResultBean;
import com.bing.lan.jdmall.bean.SubCategoryResultBean;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:27
 */
public class CategoryPresenter extends
        BaseFragmentPresenter<ICategoryContract.ICategoryView, ICategoryContract.ICategoryModule>
        implements ICategoryContract.ICategoryPresenter {

    public static final int LOAD_TOP_CATEGORYINFO = 1;
    public static final int LOAD_SUB_CATEGORYINFO = 2;

    private boolean mIsListViewHaveData = false;

    @Override
    public void onStart(Object... params) {

        if (!mIsListViewHaveData) {
            mView.setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_LOADING);
            // //重新点击进入页面,重置错误计数为0
            mView.resetErrorCount();
            loadData(LOAD_TOP_CATEGORYINFO);
        }
    }

    @Override
    public void loadData(int action, Object... parameter) {
        mModule.loadData(action, this, parameter);
    }

    @Override
    public void onSuccess(int action, Object data) {
        switch (action) {
            case LOAD_TOP_CATEGORYINFO:
                mView.setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_SUCCESS);
                mIsListViewHaveData = true;
                mView.updateSlideMenu((List<CategoryResultBean.TopCategoryInfoBean>) data);
                break;
            case LOAD_SUB_CATEGORYINFO:
                mView.updateListView((List<SubCategoryResultBean.SubCategoryInfoBean>) data);
                break;
        }
    }

    @Override
    public void onError(int action, Throwable e) {
        mIsListViewHaveData = false;
    }
}
