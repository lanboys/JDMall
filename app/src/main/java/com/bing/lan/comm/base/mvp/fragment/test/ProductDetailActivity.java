package com.bing.lan.comm.base.mvp.fragment.test;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.jdmall.R;

public class ProductDetailActivity extends BaseActivity<IProductDetailContract.IProductDetailPresenter>
        implements IProductDetailContract.IProductDetailView {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        // activityComponent.inject(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void readyStartPresenter() {

    }
}
