package com.bing.lan.jdmall.bean;

import com.google.gson.Gson;

import java.util.List;

public class BannerResultBean extends ResultBean<List<BannerResultBean.BannerInfoBean>> {


    public static class BannerInfoBean {


        /**
         * id : 广告id
         * type : 跳转类型（1跳转到网页，2跳转到商品详情，3跳转到分类去）
         * adUrl : 图片路径
         * webUrl : 如果是跳转网页类型，则返回网页地址
         * adKind : 广告类型（1为导航banner，2为广告banner）
         */

        private long id;
        private int type;// 1跳转到网页，2跳转到商品详情，3跳转到分类去
        private String adUrl;//图片路径
        private String webUrl;//type=1的网址
        private String adKind;


        public static BannerInfoBean objectFromData(String str) {

            return new Gson().fromJson(str, BannerInfoBean.class);
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getAdUrl() {
            return adUrl;
        }

        public void setAdUrl(String adUrl) {
            this.adUrl = adUrl;
        }

        public String getWebUrl() {
            return webUrl;
        }

        public void setWebUrl(String webUrl) {
            this.webUrl = webUrl;
        }

        public String getAdKind() {
            return adKind;
        }

        public void setAdKind(String adKind) {
            this.adKind = adKind;
        }
    }
}