package com.bing.lan.jdmall.bean;

import com.google.gson.Gson;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/9  19:55
 */
public class GetYourLikeResultBean extends ResultBean<GetYourLikeResultBean.GetYourLikeInfoBean> {

    public static class GetYourLikeInfoBean {

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

        public static class RowsBean {

            /**
             * price : 商品价格
             * name : 商品名称
             * iconUrl : 商品图片
             * productId : 商品id
             */

            private double price;
            private String name;
            private String iconUrl;
            private long productId;

            public static RowsBean objectFromData(String str) {

                return new Gson().fromJson(str, RowsBean.class);
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
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

            public long getProductId() {
                return productId;
            }

            public void setProductId(long productId) {
                this.productId = productId;
            }
        }
    }
}
