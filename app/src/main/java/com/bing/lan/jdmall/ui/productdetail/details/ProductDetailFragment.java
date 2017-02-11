package com.bing.lan.jdmall.ui.productdetail.details;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.jdmall.R;

/**
 *
 */
public class ProductDetailFragment extends BaseFragment<IProductDetailContract.IProductDetailPresenter>
        implements IProductDetailContract.IProductDetailView {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_product_details;
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
