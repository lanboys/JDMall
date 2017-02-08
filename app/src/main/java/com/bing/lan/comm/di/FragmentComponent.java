package com.bing.lan.comm.di;

import com.bing.lan.jdmall.ui.category.CategoryFragment;
import com.bing.lan.jdmall.ui.home.HomeFragment;
import com.bing.lan.jdmall.ui.mine.MineFragment;

import dagger.Component;



@Component(modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(MineFragment mineFragment);

    void inject(HomeFragment homeFragment);

    void inject(CategoryFragment categoryFragment);

}