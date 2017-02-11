package com.bing.lan.jdmall.ui.productdetail;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.jdmall.R;
import com.bing.lan.jdmall.ui.productdetail.introduce.ProductIntroduceFragment;

import butterknife.BindView;

import static com.bing.lan.jdmall.R.id.fab;

public class ProductDetailActivity extends BaseActivity<IProductDetailContract.IProductDetailPresenter>
        implements IProductDetailContract.IProductDetailView, View.OnClickListener {

    public static final String PID_KEY = "pid";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(fab)
    FloatingActionButton mFab;
    @BindView(R.id.id_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.id_viewpager)
    ViewPager mViewPager;

    private int mTabCount = 3;
    private String[] mTabTitle;
    private long mProductId;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_product_detail;
    }

    @Override
    protected int getMenuId() {
        return R.menu.menu_product_detail_activity;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initView() {

        setToolBar(mToolbar);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mTabTitle = AppUtil.getStrArr(R.array.product_tab_title);
        FragmentPagerAdapter productAdapter = new ProductFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(productAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initData(Intent intent) {
        //1.拿到商品的id
        mProductId = intent.getLongExtra(PID_KEY, 0);
    }

    @Override
    protected void readyStartPresenter() {
        if (mProductId == 0) {
            // finish();
        }
    }

    @Override
    public void onClick(View v) {

    }

    class ProductFragmentPagerAdapter extends FragmentPagerAdapter {

        public ProductFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new ProductIntroduceFragment();
        }

        @Override
        public int getCount() {
            return mTabTitle.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitle[position];
        }
    }
}
