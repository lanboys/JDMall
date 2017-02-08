package com.bing.lan.jdmall.ui.category;


import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.jdmall.R;

/**
 *
 */
public class CategoryFragment extends BaseFragment<ICategoryContract.ICategoryPresenter>
        implements ICategoryContract.ICategoryView {
    @Override
    protected void readyStartPresenter() {

    }

    @Override
    protected void startInject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }
}
