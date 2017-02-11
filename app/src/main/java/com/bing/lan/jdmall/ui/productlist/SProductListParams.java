package com.bing.lan.jdmall.ui.productlist;

public class SProductListParams {

    /**
     * 排序类型
     */
    public static final int FILTERTYPE_ALL = 1;
    public static final int FILTERTYPE_NEW = 2;
    public static final int filterType_COMMENT = 3;

    /**
     * 排序条件
     */
    public static final int SORTTYPE_DEFAULT = 0;
    public static final int SORTTYPE_SALE = 1;
    public static final int SORTTYPE_PRICE_UP2DOWN = 2;
    public static final int SORTTYPE_PRICE_DOWN2UP = 3;

    public int categoryId;//3级分类id
    public int filterType = FILTERTYPE_ALL;//1-综合 2-新品 3-评价
    public int sortType = SORTTYPE_DEFAULT;

    public int deliverChoose;//选择服务
    public int minPrice;
    public int maxPrice;
    public long brandId;

    public void changePriceSortType() {
        /*sortType == SORTTYPE_DEFAULT || sortType == SORTTYPE_SALE ||*/
        if (sortType == SORTTYPE_PRICE_DOWN2UP) {

            sortType = SORTTYPE_PRICE_UP2DOWN;
        } else if (sortType == SORTTYPE_PRICE_UP2DOWN) {

            sortType = SORTTYPE_PRICE_DOWN2UP;
        }
    }
}
