package com.bing.lan.jdmall.ui.category;

import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.bing.lan.comm.base.BaseViewHolder;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.comm.utils.FixedViewUtil;
import com.bing.lan.jdmall.R;
import com.bing.lan.jdmall.bean.SubCategoryResultBean;

import butterknife.BindView;

/**
 * @author 蓝兵
 * @time 2017/2/11  11:15
 */
public class CategoryViewHolder extends BaseViewHolder<SubCategoryResultBean.SubCategoryInfoBean> {

    @BindView(R.id.subcategory_name)
    TextView mSubcategoryName;
    @BindView(R.id.subcategory_lv)
    GridView mSubcategoryLv;

    public CategoryViewHolder(View view) {
        super(view);
    }

    @Override
    public void refreshViewData(SubCategoryResultBean.SubCategoryInfoBean bean, int position) {
        log.d("refreshViewData(): " + bean.toString());
        mSubcategoryName.setText(bean.getName());

        CategroyGvAdapter adapter = new CategroyGvAdapter(AppUtil.getAppContext());
        mSubcategoryLv.setAdapter(adapter);
        adapter.setDatas(bean.getThirdCategory());
        adapter.notifyDataSetChanged();
        FixedViewUtil.setGridViewHeightBasedOnChildren(mSubcategoryLv, 3);
    }
}
