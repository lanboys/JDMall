package com.bing.lan.jdmall.ui.category;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bing.lan.comm.api.ApiService;
import com.bing.lan.jdmall.R;
import com.bing.lan.jdmall.adapter.JDBaseAdapter;
import com.bing.lan.jdmall.bean.SubCategoryResultBean;
import com.bing.lan.jdmall.ui.productlist.ProductListActivity;
import com.loopj.android.image.SmartImageView;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class CategroyGvAdapter extends
        JDBaseAdapter<SubCategoryResultBean.SubCategoryInfoBean.ThirdCategoryBean> implements View.OnClickListener {

    public CategroyGvAdapter(Context context) {
        super(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.category_gv_item, parent,
                    false);
            holder = new ViewHolder();
            holder.smiv = (SmartImageView) convertView
                    .findViewById(R.id.image_iv);
            holder.nameTv = (TextView) convertView
                    .findViewById(R.id.name_tv);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        SubCategoryResultBean.SubCategoryInfoBean.ThirdCategoryBean bean = mDatas.get(position);
        holder.smiv.setImageUrl(ApiService.BASE_URL + bean.getBannerUrl());
        holder.smiv.setTag(bean.getId());
        holder.smiv.setOnClickListener(this);

        holder.nameTv.setText(bean.getName());
        return convertView;
    }

    @Override
    public void onClick(View v) {
        Context context = v.getContext();
        Intent intent = new Intent(context, ProductListActivity.class);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(ProductListActivity.CATEGORYID_KEY, (Integer) v.getTag());
        context.startActivity(intent);
    }

    static class ViewHolder {

        SmartImageView smiv;
        TextView nameTv;
    }
}
