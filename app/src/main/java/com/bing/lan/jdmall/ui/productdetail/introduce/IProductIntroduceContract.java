package com.bing.lan.jdmall.ui.productdetail.introduce;

import com.bing.lan.comm.base.mvp.fragment.IBaseFragmentContract;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public interface IProductIntroduceContract {

    interface IProductIntroduceView extends IBaseFragmentContract.IBaseFragmentView<IProductIntroducePresenter> {

        void updateBanner(List<String> imageUrls);
    }

    interface IProductIntroducePresenter extends
            IBaseFragmentContract.IBaseFragmentPresenter<IProductIntroduceView, IProductIntroduceModule> {

    }

    interface IProductIntroduceModule extends IBaseFragmentContract.IBaseFragmentModule {

    }
}
