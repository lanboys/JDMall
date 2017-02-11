package com.bing.lan.jdmall.ui.category;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.fragment.IBaseFragmentContract;
import com.bing.lan.jdmall.bean.CategoryResultBean;
import com.bing.lan.jdmall.bean.SubCategoryResultBean;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public interface ICategoryContract {

    interface ICategoryView extends IBaseFragmentContract.IBaseFragmentView<ICategoryPresenter> {

        void updateSlideMenu(List<CategoryResultBean.TopCategoryInfoBean> list);

        void updateListView(List<SubCategoryResultBean.SubCategoryInfoBean> list);
    }

    interface ICategoryPresenter extends
            IBaseFragmentContract.IBaseFragmentPresenter<ICategoryView, ICategoryModule> {

        void loadData(int action, Object... parameter);
    }

    interface ICategoryModule extends IBaseFragmentContract.IBaseFragmentModule {

        void loadCategory(int action, IBaseContract.OnDataChangerListener listener);
    }
}
