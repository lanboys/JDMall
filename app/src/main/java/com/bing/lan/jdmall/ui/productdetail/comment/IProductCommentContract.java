package com.bing.lan.jdmall.ui.productdetail.comment;

import com.bing.lan.comm.base.mvp.fragment.IBaseFragmentContract;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public interface IProductCommentContract {

    interface IProductCommentView extends IBaseFragmentContract.IBaseFragmentView<IProductCommentPresenter> {

    }

    interface IProductCommentPresenter extends
            IBaseFragmentContract.IBaseFragmentPresenter<IProductCommentView, IProductCommentModule> {

    }

    interface IProductCommentModule extends IBaseFragmentContract.IBaseFragmentModule {

    }
}
