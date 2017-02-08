package com.bing.lan.jdmall.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/8  16:36
 */
public class SecKillResultBean extends ResultBean<SecKillResultBean.SecKillInfoBean> {

    public static class SecKillInfoBean {

        private int total;
        private List<RowsBean> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        @Override
        public String toString() {
            return "SecKillInfoBean{" +
                    "rows=" + rows +
                    ", total='" + total + '\'' +
                    '}';
        }

        public static class RowsBean {


            private long productId;
            private int type;// 1抢年货，2超值，3热卖
            private double allPrice;// 原价
            private double pointPrice;// 秒杀价
            private String iconUrl;// 商品图片路径
            private int timeLeft;// 分钟

            public static RowsBean objectFromData(String str) {

                return new Gson().fromJson(str, RowsBean.class);
            }

            public double getAllPrice() {
                return allPrice;
            }

            public void setAllPrice(double allPrice) {
                this.allPrice = allPrice;
            }

            public double getPointPrice() {
                return pointPrice;
            }

            public void setPointPrice(double pointPrice) {
                this.pointPrice = pointPrice;
            }

            public String getIconUrl() {
                return iconUrl;
            }

            public void setIconUrl(String iconUrl) {
                this.iconUrl = iconUrl;
            }

            public int getTimeLeft() {
                return timeLeft;
            }

            public void setTimeLeft(int timeLeft) {
                this.timeLeft = timeLeft;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public long getProductId() {
                return productId;
            }

            public void setProductId(long productId) {
                this.productId = productId;
            }

            @Override
            public String toString() {
                return "RowsBean{" +
                        "allPrice=" + allPrice +
                        ", productId=" + productId +
                        ", type=" + type +
                        ", pointPrice=" + pointPrice +
                        ", iconUrl='" + iconUrl + '\'' +
                        ", timeLeft=" + timeLeft +
                        '}';
            }
        }
    }
}
