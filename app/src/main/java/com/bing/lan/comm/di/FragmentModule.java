package com.bing.lan.comm.di;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.bing.lan.comm.utils.LogUtil;
import com.bing.lan.jdmall.ui.category.CategoryFragment;
import com.bing.lan.jdmall.ui.category.CategoryModule;
import com.bing.lan.jdmall.ui.category.CategoryPresenter;
import com.bing.lan.jdmall.ui.category.ICategoryContract;
import com.bing.lan.jdmall.ui.home.HomeFragment;
import com.bing.lan.jdmall.ui.home.HomeModule;
import com.bing.lan.jdmall.ui.home.HomePresenter;
import com.bing.lan.jdmall.ui.home.IHomeContract;
import com.bing.lan.jdmall.ui.mine.IMineContract;
import com.bing.lan.jdmall.ui.mine.MineFragment;
import com.bing.lan.jdmall.ui.mine.MineModule;
import com.bing.lan.jdmall.ui.mine.MinePresenter;
import com.bing.lan.jdmall.ui.productdetail.comment.IProductCommentContract;
import com.bing.lan.jdmall.ui.productdetail.comment.ProductCommentFragment;
import com.bing.lan.jdmall.ui.productdetail.comment.ProductCommentModule;
import com.bing.lan.jdmall.ui.productdetail.comment.ProductCommentPresenter;
import com.bing.lan.jdmall.ui.productdetail.details.IProductDetailContract;
import com.bing.lan.jdmall.ui.productdetail.details.ProductDetailFragment;
import com.bing.lan.jdmall.ui.productdetail.details.ProductDetailModule;
import com.bing.lan.jdmall.ui.productdetail.details.ProductDetailPresenter;
import com.bing.lan.jdmall.ui.productdetail.introduce.IProductIntroduceContract;
import com.bing.lan.jdmall.ui.productdetail.introduce.ProductIntroduceFragment;
import com.bing.lan.jdmall.ui.productdetail.introduce.ProductIntroduceModule;
import com.bing.lan.jdmall.ui.productdetail.introduce.ProductIntroducePresenter;
import com.bing.lan.jdmall.ui.shopcar.IShopcarContract;
import com.bing.lan.jdmall.ui.shopcar.ShopcarFragment;
import com.bing.lan.jdmall.ui.shopcar.ShopcarModule;
import com.bing.lan.jdmall.ui.shopcar.ShopcarPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 520 on 2017/1/11.
 */
@Module
public class FragmentModule {

    private Fragment mFragment;
    private Bundle initParams;

    public FragmentModule(Fragment fragment, Bundle initParams) {
        this.mFragment = fragment;
        this.initParams = initParams;
    }

    @Provides
    public LogUtil provideLogCat() {
        return LogUtil.getLogUtil(mFragment.getClass(), 1);
    }

    @Provides
    public IMineContract.IMinePresenter provideMinePresenter() {
        MinePresenter minePresenter = new MinePresenter();
        minePresenter.setParams(initParams);
        minePresenter.setModule(new MineModule());
        minePresenter.onAttachView((MineFragment) mFragment);
        return minePresenter;
    }

    @Provides
    public IHomeContract.IHomePresenter provideHomePresenter() {
        HomePresenter homePresenter = new HomePresenter();
        homePresenter.setParams(initParams);
        homePresenter.setModule(new HomeModule());
        homePresenter.onAttachView((HomeFragment) mFragment);
        return homePresenter;
    }

    @Provides
    public ICategoryContract.ICategoryPresenter provideCategoryPresenter() {
        CategoryPresenter categoryPresenter = new CategoryPresenter();
        categoryPresenter.setParams(initParams);
        categoryPresenter.setModule(new CategoryModule());
        categoryPresenter.onAttachView((CategoryFragment) mFragment);
        return categoryPresenter;
    }

    @Provides
    public IShopcarContract.IShopcarPresenter provideShopcarPresenter() {
        ShopcarPresenter shopcarPresenter = new ShopcarPresenter();
        shopcarPresenter.setParams(initParams);
        shopcarPresenter.setModule(new ShopcarModule());
        shopcarPresenter.onAttachView((ShopcarFragment) mFragment);
        return shopcarPresenter;
    }
    // void inject(ProductIntroduceFragment productIntroduceFragment);
    //
    // void inject(ProductDetailFragment productDetailFragment);
    //
    // void inject(ProductCommentFragment productCommentFragment);

    @Provides
    public IProductDetailContract.IProductDetailPresenter provideProductDetailPresenter() {
        ProductDetailPresenter shopcarPresenter = new ProductDetailPresenter();
        shopcarPresenter.setParams(initParams);
        shopcarPresenter.setModule(new ProductDetailModule());
        shopcarPresenter.onAttachView((ProductDetailFragment) mFragment);
        return shopcarPresenter;
    }

    @Provides
    public IProductCommentContract.IProductCommentPresenter provideProductCommentPresenter() {
        ProductCommentPresenter shopcarPresenter = new ProductCommentPresenter();
        shopcarPresenter.setParams(initParams);
        shopcarPresenter.setModule(new ProductCommentModule());
        shopcarPresenter.onAttachView((ProductCommentFragment) mFragment);
        return shopcarPresenter;
    }

    @Provides
    public IProductIntroduceContract.IProductIntroducePresenter provideProductIntroducePresenter() {
        ProductIntroducePresenter shopcarPresenter = new ProductIntroducePresenter();
        shopcarPresenter.setParams(initParams);
        shopcarPresenter.setModule(new ProductIntroduceModule());
        shopcarPresenter.onAttachView((ProductIntroduceFragment) mFragment);
        return shopcarPresenter;
    }
}
