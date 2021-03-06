package com.bing.lan.jdmall.ui.productdetail;

import android.content.Intent;
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
import com.bing.lan.jdmall.ui.productdetail.comment.SampleFragment;
import com.bing.lan.jdmall.ui.productdetail.introduce.ProductIntroduceFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class ProductInfoActivity extends BaseActivity<IProductInfoContract.IProductInfoPresenter>
        implements IProductInfoContract.IProductInfoView, View.OnClickListener {

    public static final String PID_KEY = "pid";
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.id_tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.id_viewpager)
    ViewPager mViewPager;

    private int mTabCount = 3;
    private String[] mTabTitle;
    private long mProductId;
    private ArrayList<Fragment> mFragments;

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
        initFragment();
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        ProductIntroduceFragment introduceFragment = new ProductIntroduceFragment();
        introduceFragment.setAllowEnterTransitionOverlap(true);

        mFragments.add(introduceFragment);
        // mFragments.add(new ProductDetailFragment());
        // mFragments.add(new ProductCommentFragment());
        mFragments.add(new SampleFragment());
        mFragments.add(new SampleFragment());
    }

    @Override
    protected void initData(Intent intent) {
        //1.拿到商品的id
        mProductId = intent.getLongExtra(PID_KEY, 0);
        log.d("initData(): " + mProductId);

        mTabTitle = AppUtil.getStrArr(R.array.product_tab_title);
        FragmentPagerAdapter productAdapter = new ProductFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(productAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
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
            return mFragments.get(position);
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
