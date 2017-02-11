package com.bing.lan.jdmall.bean;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/11  12:50
 */
public class ProductListResultBean extends ResultBean<List<ProductListResultBean.ProductListInfo>> {

    public static class ProductListInfo {

        private long id;
        private String name;
        private String iconUrl;
        private double price;
        private int commentCount;
        private int favcomRate;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getCommentCount() {
            return commentCount;
        }

        public void setCommentCount(int commentCount) {
            this.commentCount = commentCount;
        }

        public int getFavcomRate() {
            return favcomRate;
        }

        public void setFavcomRate(int favcomRate) {
            this.favcomRate = favcomRate;
        }
    }
}
