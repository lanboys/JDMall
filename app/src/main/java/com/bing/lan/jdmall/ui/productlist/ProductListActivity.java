package com.bing.lan.jdmall.ui.productlist;

import android.content.Intent;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.jdmall.R;

public class ProductListActivity extends BaseActivity<IProductListContract.IProductListPresenter>
        implements IProductListContract.IProductListView {

    public static final String CATEGORYID_KEY = "category_id";

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_product_list;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        int stringExtra = intent.getIntExtra(CATEGORYID_KEY, 0);
        log.d("initView(): " + stringExtra);
    }

    @Override
    protected void readyStartPresenter() {

    }
}
