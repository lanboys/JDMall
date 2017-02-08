package com.bing.lan.jdmall.ui.home;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.fragment.IBaseFragmentContract;
import com.bing.lan.jdmall.bean.SecKillResultBean;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public interface IHomeContract {

    interface IHomeView extends IBaseFragmentContract.IBaseFragmentView<IHomePresenter> {

        void updateBanner(List<String> list);
        void updateSecKill(List<SecKillResultBean.SecKillInfoBean.RowsBean> rowsBeen);

        void showBanner(boolean isShow);
    }

    interface IHomePresenter extends
            IBaseFragmentContract.IBaseFragmentPresenter<IHomeView, IHomeModule> {

    }

    interface IHomeModule extends IBaseFragmentContract.IBaseFragmentModule {

        void loadBanner(int action, int adKind, IBaseContract.OnDataChangerListener listener);

        void loadSecKill(int action, IBaseContract.OnDataChangerListener listener);

    }
}
