package com.bing.lan.jdmall.ui.productdetail.introduce;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.jdmall.R;

/**
 *
 */
public class ProductIntroduceFragment extends BaseFragment<IProductIntroduceContract.IProductIntroducePresenter>
        implements IProductIntroduceContract.IProductIntroduceView {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_productdetails;
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
