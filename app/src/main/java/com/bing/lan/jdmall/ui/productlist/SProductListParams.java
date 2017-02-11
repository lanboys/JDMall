package com.bing.lan.jdmall.ui.productlist;

public class SProductListParams {

    /**
     * 排序类型
     */
    public static final int FILTER_TYPE_ALL = 1;
    public static final int FILTER_TYPE_NEW = 2;
    public static final int FILTER_TYPE_COMMENT = 3;

    /**
     * 排序条件
     */
    public static final int SORT_TYPE_DEFAULT = 0;
    public static final int SORT_TYPE_SALE = 1;
    public static final int SORT_TYPE_PRICE_UP2DOWN = 2;
    public static final int SORT_TYPE_PRICE_DOWN2UP = 3;

    public int categoryId;//3级分类id
    public int filterType = FILTER_TYPE_ALL;//1-综合 2-新品 3-评价
    public int sortType = SORT_TYPE_DEFAULT;

    public int deliverChoose;//选择服务
    public int minPrice;
    public int maxPrice;
    public long brandId;

    public void changePriceSortType() {

        if (sortType == SORT_TYPE_DEFAULT
                || sortType == SORT_TYPE_SALE
                || sortType == SORT_TYPE_PRICE_DOWN2UP) {

            sortType = SORT_TYPE_PRICE_UP2DOWN;
        } else if (sortType == SORT_TYPE_PRICE_UP2DOWN) {

            sortType = SORT_TYPE_PRICE_DOWN2UP;
        }
    }
}
