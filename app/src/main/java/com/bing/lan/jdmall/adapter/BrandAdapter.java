package com.bing.lan.jdmall.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bing.lan.jdmall.R;
import com.bing.lan.jdmall.bean.BrandResultBean;

public class BrandAdapter extends JDBaseAdapter<BrandResultBean.BrandInfo> {

    //当前点击的索引
    public int mCurrentTabIndex = -1;

    public BrandAdapter(Context context) {
        super(context);
    }

    /**
     * 点击列表某个item
     */
    public void tabItem(int index) {
        mCurrentTabIndex = index;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView brandTv = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.brand_lv_item_layout, parent,
                    false);
            brandTv = (TextView) convertView.findViewById(R.id.brand_tv);
            convertView.setTag(brandTv);
        } else {
            brandTv = (TextView) convertView.getTag();
        }

        BrandResultBean.BrandInfo brandInfo = mDatas.get(position);
        brandTv.setText(brandInfo.getName());
        brandTv.setSelected(position == mCurrentTabIndex);
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return mDatas != null ? mDatas.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return mDatas != null ? mDatas.get(position).getId() : 0;
    }
}
