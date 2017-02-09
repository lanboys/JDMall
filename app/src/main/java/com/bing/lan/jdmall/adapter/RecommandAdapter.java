package com.bing.lan.jdmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bing.lan.comm.api.ApiService;
import com.bing.lan.jdmall.R;
import com.bing.lan.jdmall.bean.GetYourLikeResultBean;
import com.loopj.android.image.SmartImageView;

public class RecommandAdapter extends JDBaseAdapter<GetYourLikeResultBean.GetYourLikeInfoBean.RowsBean> {

    public RecommandAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.recommend_gv_item, parent,
                    false);
            holder = new ViewHolder();
            holder.smiv = (SmartImageView) convertView
                    .findViewById(R.id.image_iv);
            holder.nameTv = (TextView) convertView
                    .findViewById(R.id.name_tv);
            holder.priceTv = (TextView) convertView
                    .findViewById(R.id.price_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GetYourLikeResultBean.GetYourLikeInfoBean.RowsBean bean = mDatas.get(position);
        holder.smiv.setImageUrl(ApiService.BASE_URL + bean.getIconUrl());
        holder.nameTv.setText(bean.getName());
        log.d("getView(): "+bean.getName());
        holder.priceTv.setText("¥" + bean.getPrice());

        return convertView;
    }

   static class ViewHolder {

        SmartImageView smiv;
        TextView nameTv;
        TextView priceTv;
    }
}
