package com.bing.lan.jdmall.ui.productlist;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.jdmall.R;
import com.bing.lan.jdmall.adapter.BrandAdapter;
import com.bing.lan.jdmall.adapter.ProductListAdapter;

import butterknife.BindView;

public class ProductListActivity extends BaseActivity<IProductListContract.IProductListPresenter>
        implements IProductListContract.IProductListView, AdapterView.OnItemClickListener {

    public static final String CATEGORYID_KEY = "category_id";
    public static final String TOP_CATEGORYID_KEY = "topCategoryId";
    @BindView(R.id.brand_gv)
    GridView mBrandGv;
    @BindView(R.id.product_lv)
    ListView mProductLv;
    // private long mTopCategoryId;// 1级分类的id
    // private long mCategoryId;// 3级分类的id
    private TextView mJdTakeTv;
    private TextView mPayWhenReceiveTv;
    private TextView mJustHashStockTv;
    private EditText mMinPriceEt;
    private EditText mMaxPriceEt;
    private BrandAdapter mBrandAdapter;
    private DrawerLayout mContainerDl;
    private View mSlideView;
    // private ProductSortPop mProductSortPop;
    private TextView mAllSortTv;// 综合排序
    private TextView mSaleSortTv;
    // private SProductListParams mSendParams;
    private View mPriceSortTv;
    // private ProductListAdapter mProductListAdapter;
    private int mCategoryId;
    private int mTopCategoryId;
    private ProductListAdapter mProductListAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_product_list;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initView() {

        mBrandGv = (GridView) findViewById(R.id.brand_gv);
        mBrandAdapter = new BrandAdapter(this);
        mBrandGv.setAdapter(mBrandAdapter);
        mBrandGv.setOnItemClickListener(this);

        mProductListAdapter = new ProductListAdapter(this);
        mProductLv.setAdapter(mProductListAdapter);
        mProductLv.setOnItemClickListener(new ProductListItemClickListener());
    }

    @Override
    protected void initData(Intent intent) {
        mCategoryId = intent.getIntExtra(CATEGORYID_KEY, 0);
    }

    @Override
    protected void readyStartPresenter() {
        if (mCategoryId == 0 || mTopCategoryId == 0) {
            // finish();
            // return;
        }

        mPresenter.onStart(mCategoryId);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    class ProductListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                long id) {
            // //1.拿到商品的id
            // long pId = mProductListAdapter.getItemId(position);
            // //2.跳转到商品详情页
            // Intent intent=new Intent(ProductListActivity.this,ProductDetailActivity.class);
            // intent.putExtra(ProductDetailActivity.PID_KEY, pId);
            // startActivity(intent);
        }
    }
}
