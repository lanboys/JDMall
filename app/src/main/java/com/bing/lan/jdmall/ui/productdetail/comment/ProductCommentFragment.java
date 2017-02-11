package com.bing.lan.jdmall.ui.productdetail.comment;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.jdmall.R;

/**
 *
 */
public class ProductCommentFragment extends BaseFragment<IProductCommentContract.IProductCommentPresenter>
        implements IProductCommentContract.IProductCommentView {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_product_comment;
    }

    @Override
    protected void startInject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void readyStartPresenter() {

    }

    @Override
    protected void initView() {

    }
}
