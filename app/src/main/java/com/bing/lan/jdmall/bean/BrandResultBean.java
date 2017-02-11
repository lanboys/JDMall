package com.bing.lan.jdmall.bean;

import java.util.List;

/**
 * @author 蓝兵
 * @time 2017/2/11  12:46
 */
public class BrandResultBean extends ResultBean<List<BrandResultBean.BrandInfo>> {

    public static class BrandInfo {

        private long id;
        private String name;

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
    }
}
