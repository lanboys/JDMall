package com.bing.lan.comm.base.mvp.fragment.test;


import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.jdmall.R;

/**
 *
 */
public class HomeFragment extends BaseFragment<IHomeContract.IHomePresenter>
        implements IHomeContract.IHomeView {
    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void startInject(FragmentComponent fragmentComponent) {
//        fragmentComponent.inject(this);
    }

    @Override
    protected void readyStartPresenter() {

    }
}
