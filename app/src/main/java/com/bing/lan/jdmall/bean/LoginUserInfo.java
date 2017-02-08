package com.bing.lan.jdmall.bean;

import io.realm.RealmObject;

/**
 * @author 蓝兵
 * @time 2017/2/7  18:49
 */

//@RealmClass
public class LoginUserInfo /*implements RealmModel*/ extends RealmObject {

    public String name;
    public String pwd;
    //    @PrimaryKey
//    public long id;


    //必须要有一个无参构造器
    public LoginUserInfo() {

    }

    public LoginUserInfo(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

}
