package com.bing.lan.jdmall.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/11  18:34
 */
public class ProductDetailResultBean extends ResultBean<ProductDetailResultBean.ProductDetailInfo> {

    public static class ProductDetailInfo {

        /**
         * id : 1
         * imgUrls : ["/img/p1.jpg","/img/info/pp1.jpg","/img/info/pp2.jpg","/img/info/pp3.jpg"]
         * price : 15
         * ifSaleOneself : false
         * name : 卜珂空气巧克力纯可可脂黑巧克力
         * timeLeft : 30
         * recomProductId : 1
         * stockCount : 0
         * commentCount : 1
         * typeList : ["麦片巧克力","8口\u200b味速融巧克力松露形","空气巧克力"]
         * favcomRate : 12
         * recomProduct : 【京东自营，正品速达】饼干蛋糕、零食薯片联合大促，19.9任选9件,79减25，叠加优惠券，更有每日一元秒，优惠多多，实惠连连。点击抢购~
         */

        private int id;
        private int price;
        private boolean ifSaleOneself;
        private String name;
        private int timeLeft;
        private String recomProductId;
        private int stockCount;
        private int commentCount;
        private int favcomRate;
        private String recomProduct;
        private List<String> imgUrls;
        private List<String> typeList;

        public static ProductDetailResultBean objectFromData(String str) {

            return new Gson().fromJson(str, ProductDetailResultBean.class);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public boolean isIfSaleOneself() {
            return ifSaleOneself;
        }

        public void setIfSaleOneself(boolean ifSaleOneself) {
            this.ifSaleOneself = ifSaleOneself;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getTimeLeft() {
            return timeLeft;
        }

        public void setTimeLeft(int timeLeft) {
            this.timeLeft = timeLeft;
        }

        public String getRecomProductId() {
            return recomProductId;
        }

        public void setRecomProductId(String recomProductId) {
            this.recomProductId = recomProductId;
        }

        public int getStockCount() {
            return stockCount;
        }

        public void setStockCount(int stockCount) {
            this.stockCount = stockCount;
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

        public String getRecomProduct() {
            return recomProduct;
        }

        public void setRecomProduct(String recomProduct) {
            this.recomProduct = recomProduct;
        }

        public List<String> getImgUrls() {
            return imgUrls;
        }

        public void setImgUrls(List<String> imgUrls) {
            this.imgUrls = imgUrls;
        }

        public List<String> getTypeList() {
            return typeList;
        }

        public void setTypeList(List<String> typeList) {
            this.typeList = typeList;
        }
    }
}
