package com.bing.lan.comm.di;

import com.bing.lan.jdmall.ui.category.CategoryFragment;
import com.bing.lan.jdmall.ui.home.HomeFragment;
import com.bing.lan.jdmall.ui.mine.MineFragment;
import com.bing.lan.jdmall.ui.productdetail.comment.ProductCommentFragment;
import com.bing.lan.jdmall.ui.productdetail.details.ProductDetailFragment;
import com.bing.lan.jdmall.ui.productdetail.introduce.ProductIntroduceFragment;

import dagger.Component;



@Component(modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(MineFragment mineFragment);

    void inject(HomeFragment homeFragment);

    void inject(CategoryFragment categoryFragment);

    void inject(ProductIntroduceFragment productIntroduceFragment);

    void inject(ProductDetailFragment productDetailFragment);

    void inject(ProductCommentFragment productCommentFragment);

    // <LISTVIEWBEAN> void inject(AbsRefreshFragment listviewbeanAbsRefreshFragment);

}