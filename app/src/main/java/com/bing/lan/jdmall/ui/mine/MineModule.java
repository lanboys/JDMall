package com.bing.lan.jdmall.ui.mine;

import com.bing.lan.comm.base.mvp.fragment.BaseFragmentModule;
import com.bing.lan.jdmall.db.JDUserInfoDao;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public class MineModule extends BaseFragmentModule
        implements IMineContract.IMineModule {
    @Override
    public void logout() {
// TODO: 2017/2/8 清空数据库
        JDUserInfoDao.deleteAllLoginUserInfo();
    }
}