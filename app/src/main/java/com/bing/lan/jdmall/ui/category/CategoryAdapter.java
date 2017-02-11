package com.bing.lan.jdmall.ui.category;

import android.view.View;

import com.bing.lan.comm.base.BaseViewHolder;
import com.bing.lan.comm.base.adapter.MyBaseAdapter;
import com.bing.lan.jdmall.R;
import com.bing.lan.jdmall.bean.SubCategoryResultBean;

/**
 * @author 蓝兵
 * @time 2017/2/11  11:17
 */
public class CategoryAdapter extends MyBaseAdapter<SubCategoryResultBean.SubCategoryInfoBean> {

    @Override
    public BaseViewHolder<SubCategoryResultBean.SubCategoryInfoBean> getViewHolder(int itemViewType, View convertView) {
        return new CategoryViewHolder(convertView);
    }

    @Override
    public int getItemLayoutId(int itemViewType) {
        return R.layout.item_catagory_lv;
    }
}