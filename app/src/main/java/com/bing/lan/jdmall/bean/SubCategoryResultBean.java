package com.bing.lan.jdmall.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/10  19:39
 */
public class SubCategoryResultBean extends ResultBean<List<SubCategoryResultBean.SubCategoryInfoBean>> {

    public static class SubCategoryInfoBean {

        /**
         * id : 13
         * name : 裙装
         * thirdCategory : [{"id":7,"bannerUrl":"/img/lyq.jpg","name":"连衣裙"}]
         */

        private int id;
        private String name;
        private List<ThirdCategoryBean> thirdCategory;

        public static SubCategoryInfoBean objectFromData(String str) {

            return new Gson().fromJson(str, SubCategoryInfoBean.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ThirdCategoryBean> getThirdCategory() {
            return thirdCategory;
        }

        public void setThirdCategory(List<ThirdCategoryBean> thirdCategory) {
            this.thirdCategory = thirdCategory;
        }

        public static class ThirdCategoryBean {

            /**
             * id : 7
             * bannerUrl : /img/lyq.jpg
             * name : 连衣裙
             */

            private int id;
            private String bannerUrl;
            private String name;

            public static ThirdCategoryBean objectFromData(String str) {

                return new Gson().fromJson(str, ThirdCategoryBean.class);
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
        }
    }
}
