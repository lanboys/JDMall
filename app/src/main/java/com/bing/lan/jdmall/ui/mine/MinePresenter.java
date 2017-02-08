package com.bing.lan.jdmall.ui.mine;

import com.bing.lan.comm.base.mvp.fragment.BaseFragmentPresenter;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.view.LoadPageView;
import com.bing.lan.jdmall.bean.LoginResultBean;
import com.bing.lan.jdmall.cons.Constants;
import com.bing.lan.jdmall.ui.login.LoginActivity;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:27
 */
public class MinePresenter extends
        BaseFragmentPresenter<IMineContract.IMineView, IMineContract.IMineModule>
        implements IMineContract.IMinePresenter {
    @Override
    public void stopUpdate() {

    }


    @Override
    public void onStart() {
        LoginResultBean.UserInfoBean userInfoBean = AppUtil.getGlobal(Constants.USER_INFO);
        if (userInfoBean != null) {
            mView.updateViewData(userInfoBean);
        }


        mView.setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_SUCCESS);
    }

    @Override
    public void logout() {
        mModule.logout();
        mView.startActivity(LoginActivity.class, true);
    }

    @Override
    public void onSuccess(int action, Object data) {

    }

    @Override
    public void onError(int action, Throwable e) {

    }
}
