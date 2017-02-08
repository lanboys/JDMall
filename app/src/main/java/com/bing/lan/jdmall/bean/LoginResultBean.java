package com.bing.lan.jdmall.bean;

import com.google.gson.Gson;

public class LoginResultBean extends ResultBean<LoginResultBean.UserInfoBean> {


    public static class UserInfoBean {


        private String id;
        private String userName;
        private String userIcon;
        private String waitPayCount;
        private String waitReceiveCount;
        //        private String userLevel;
        private int userLevel;

        public static UserInfoBean objectFromData(String str) {

            return new Gson().fromJson(str, UserInfoBean.class);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserIcon() {
            return userIcon;
        }

        public void setUserIcon(String userIcon) {
            this.userIcon = userIcon;
        }

        public String getWaitPayCount() {
            return waitPayCount;
        }

        public void setWaitPayCount(String waitPayCount) {
            this.waitPayCount = waitPayCount;
        }

        public String getWaitReceiveCount() {
            return waitReceiveCount;
        }

        public void setWaitReceiveCount(String waitReceiveCount) {
            this.waitReceiveCount = waitReceiveCount;
        }

        public int getUserLevel() {
            return userLevel;
        }

        public void setUserLevel(int userLevel) {
            this.userLevel = userLevel;
        }

//        public String getUserLevel() {
//            return userLevel;
//        }
//
//        public void setUserLevel(String userLevel) {
//            this.userLevel = userLevel;
//        }

        public String getUserLevelStr() {
            switch (userLevel) {
                case 1:
                    return "注册会员";
                case 2:
                    return "铜牌会员";
                case 3:
                    return "银牌会员";
                case 4:
                    return "金牌会员";
                case 5:
                    return "钻石会员";
                default:
                    return "";
            }
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "id='" + id + '\'' +
                    ", userName='" + userName + '\'' +
                    ", userIcon='" + userIcon + '\'' +
                    ", waitPayCount='" + waitPayCount + '\'' +
                    ", waitReceiveCount='" + waitReceiveCount + '\'' +
                    ", userLevel='" + userLevel + '\'' +
                    '}';
        }
    }
}