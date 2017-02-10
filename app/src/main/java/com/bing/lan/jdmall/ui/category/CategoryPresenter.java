package com.bing.lan.jdmall.ui.category;

import com.bing.lan.comm.base.mvp.fragment.BaseFragmentPresenter;
import com.bing.lan.jdmall.bean.CategoryResultBean;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:27
 */
public class CategoryPresenter extends
        BaseFragmentPresenter<ICategoryContract.ICategoryView, ICategoryContract.ICategoryModule>
        implements ICategoryContract.ICategoryPresenter {

    public static final int LOAD_TOPCATEGORYINFO = 1;

    @Override
    public void onStart() {
        mModule.loadData(LOAD_TOPCATEGORYINFO, this);
    }

    @Override
    public void onSuccess(int action, Object data) {
        switch (action) {
            case LOAD_TOPCATEGORYINFO:
                mView.updateSlideMenu((List<CategoryResultBean.TopCategoryInfoBean>) data);
                log.d("onSuccess(): " + ((List<CategoryResultBean.TopCategoryInfoBean>) data).toString());
                break;
        }
    }

    @Override
    public void onError(int action, Throwable e) {

    }
}
