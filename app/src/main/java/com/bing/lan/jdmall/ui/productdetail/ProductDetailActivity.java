package com.bing.lan.jdmall.ui.productdetail;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.jdmall.R;

import butterknife.BindView;

import static com.bing.lan.jdmall.R.id.fab;

public class ProductDetailActivity extends BaseActivity<IProductDetailContract.IProductDetailPresenter>
        implements IProductDetailContract.IProductDetailView {

    // @Override
    // protected void onCreate(Bundle savedInstanceState) {
    //     super.onCreate(savedInstanceState);
    //     setContentView(R.layout.activity_product_detail);
    //     Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //     setSupportActionBar(toolbar);
    //
    //     FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    //     fab.setOnClickListener(new View.OnClickListener() {
    //         @Override
    //         public void onClick(View view) {
    //             Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
    //                     .setAction("Action", null).show();
    //         }
    //     });
    // }

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(fab)
    FloatingActionButton mFab;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void readyStartPresenter() {
        init();
    }

    private void init() {
        setSupportActionBar(mToolbar);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
