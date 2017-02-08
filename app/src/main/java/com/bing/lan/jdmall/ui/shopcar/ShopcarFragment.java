package com.bing.lan.jdmall.ui.shopcar;


import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.jdmall.R;

/**
 *
 */
public class ShopcarFragment extends BaseFragment<IShopcarContract.IShopcarPresenter>
        implements IShopcarContract.IShopcarView {
    @Override
    protected void readyStartPresenter() {

    }

    @Override
    protected void startInject(FragmentComponent fragmentComponent) {
//        fragmentComponent.inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }
}
