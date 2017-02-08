package com.bing.lan.jdmall.db;

import com.bing.lan.comm.utils.RealmManager;
import com.bing.lan.jdmall.bean.LoginUserInfo;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * @author 蓝兵
 * @time 2017/2/7  18:35
 */
public class JDUserInfoDao {


    public static LoginUserInfo queryUserInfo() {
        return RealmManager.getInstance()
                .getMainRealm()
                .where(LoginUserInfo.class)
//                .equalTo("name", name)
                .findFirst();
    }

    public static RealmResults<LoginUserInfo> queryUserInfoAll() {
        return RealmManager.getInstance()
                .getMainRealm()
                .where(LoginUserInfo.class)
                .findAll();
    }

    public static long queryUserInfoCount() {
        return RealmManager.getInstance()
                .getMainRealm()
                .where(LoginUserInfo.class)
                .count();
    }

    public static void saveLoginUserInfo(LoginUserInfo loginUserInfo) {
        Realm mainRealm = RealmManager.getInstance().getMainRealm();
        mainRealm.beginTransaction();
        mainRealm.copyToRealm(loginUserInfo);
        mainRealm.commitTransaction();
    }

    public static boolean deleteAllLoginUserInfo() {
        Realm mainRealm = RealmManager.getInstance().getMainRealm();
        mainRealm.beginTransaction();
        RealmResults<LoginUserInfo> results = mainRealm.where(LoginUserInfo.class).findAll();
        boolean b = results.deleteAllFromRealm();
        mainRealm.commitTransaction();
        return b;

    }


}
