package com.bing.lan.jdmall.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bing.lan.comm.api.ApiService;
import com.bing.lan.jdmall.R;
import com.bing.lan.jdmall.bean.SecKillResultBean;
import com.loopj.android.image.SmartImageView;


public class SecondKillAdapter extends JDBaseAdapter<SecKillResultBean.SecKillInfoBean.RowsBean> {

    public SecondKillAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.home_seckill_item, parent,
                    false);
            holder = new ViewHolder();
            holder.smiv = (SmartImageView) convertView
                    .findViewById(R.id.image_iv);
            holder.nowpriceTv = (TextView) convertView
                    .findViewById(R.id.nowprice_tv);
            holder.normalpriceTv = (TextView) convertView
                    .findViewById(R.id.normalprice_tv);
            holder.normalpriceTv.getPaint().setFlags(
                    Paint.STRIKE_THRU_TEXT_FLAG);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SecKillResultBean.SecKillInfoBean.RowsBean bean = mDatas.get(position);
        holder.smiv.setImageUrl(ApiService.BASE_URL + bean.getIconUrl());
        holder.nowpriceTv.setText("¥ " + bean.getPointPrice());
        holder.normalpriceTv.setText(" ¥ " + bean.getAllPrice() + " ");

        return convertView;
    }

    class ViewHolder {

        SmartImageView smiv;
        TextView normalpriceTv;
        TextView nowpriceTv;
    }
}
