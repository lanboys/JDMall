package com.bing.lan.comm.base.mvp.fragment.test;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.fragment.BaseFragmentModule;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public class HomeModule extends BaseFragmentModule
        implements IHomeContract.IHomeModule {

    @Override
    public void releaseTask() {

    }

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {

    }
}
