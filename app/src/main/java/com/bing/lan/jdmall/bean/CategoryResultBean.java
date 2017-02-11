package com.bing.lan.jdmall.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/10  8:49
 */
public class CategoryResultBean extends ResultBean<List<CategoryResultBean.TopCategoryInfoBean>> {

    public static class TopCategoryInfoBean {

        /**
         * id : 分类id
         * bannerUrl : 分类图片路径
         * name : 分类名称
         */

        private int id;
        private String bannerUrl;
        private String name;

        public static TopCategoryInfoBean objectFromData(String str) {

            return new Gson().fromJson(str, TopCategoryInfoBean.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getBannerUrl() {
            return bannerUrl;
        }

        public void setBannerUrl(String bannerUrl) {
            this.bannerUrl = bannerUrl;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "TopCategoryInfoBean{" +
                    "bannerUrl='" + bannerUrl + '\'' +
                    ", id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
