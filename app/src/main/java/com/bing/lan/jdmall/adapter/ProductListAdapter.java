package com.bing.lan.jdmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bing.lan.comm.api.ApiService;
import com.bing.lan.jdmall.R;
import com.bing.lan.jdmall.bean.ProductListResultBean;
import com.loopj.android.image.SmartImageView;

public class ProductListAdapter extends JDBaseAdapter<ProductListResultBean.ProductListInfo.ProductInfo> {

    public ProductListAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.product_lv_item, parent,
                    false);

            holder = new ViewHolder();
            holder.smiv = (SmartImageView) convertView
                    .findViewById(R.id.product_iv);
            holder.nameTv = (TextView) convertView.findViewById(R.id.name_tv);
            holder.normalpriceTv = (TextView) convertView
                    .findViewById(R.id.price_tv);
            holder.commrateTv = (TextView) convertView
                    .findViewById(R.id.commrate_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ProductListResultBean.ProductListInfo.ProductInfo bean = mDatas.get(position);
        holder.smiv.setImageUrl(ApiService.BASE_URL + bean.getIconUrl());
        holder.nameTv.setText(bean.getName());
        holder.normalpriceTv.setText(" ¥ " + bean.getPrice() + " ");
        holder.commrateTv.setText(bean.getCommentCount() + "条评价 好评率"
                + bean.getFavcomRate() + "%");

        return convertView;
    }

    /**
     * 获取商品的id
     */
    @Override
    public long getItemId(int position) {
        return mDatas != null ? mDatas.get(position).getId() : 0;
    }

    class ViewHolder {

        SmartImageView smiv;
        TextView nameTv;
        TextView normalpriceTv;
        TextView commrateTv;
    }
}
