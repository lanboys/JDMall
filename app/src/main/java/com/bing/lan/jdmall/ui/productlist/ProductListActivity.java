package com.bing.lan.jdmall.ui.productlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.FixedViewUtil;
import com.bing.lan.comm.view.pop.IPopProtocal;
import com.bing.lan.comm.view.pop.ProductSortPop;
import com.bing.lan.jdmall.R;
import com.bing.lan.jdmall.adapter.BrandAdapter;
import com.bing.lan.jdmall.adapter.ProductListAdapter;
import com.bing.lan.jdmall.bean.BrandResultBean;
import com.bing.lan.jdmall.bean.ProductListResultBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListActivity extends
        BaseActivity<IProductListContract.IProductListPresenter>
        implements IProductListContract.IProductListView,
        AdapterView.OnItemClickListener, View.OnClickListener,
        IPopProtocal.IProductSortChangeListener {

    public static final String CATEGORYID_KEY = "category_id";
    public static final String TOP_CATEGORYID_KEY = "topCategoryId";
    @BindView(R.id.brand_gv)
    GridView mBrandGv;
    @BindView(R.id.product_lv)
    ListView mProductLv;
    @BindView(R.id.jd_take_tv)
    TextView mJdTakeTv;
    @BindView(R.id.paywhenreceive_tv)
    TextView mPayWhenReceiveTv;
    @BindView(R.id.justhasstock_tv)
    TextView mJustHashStockTv;
    @BindView(R.id.minPrice_et)
    EditText mMinPriceEt;
    @BindView(R.id.maxPrice_et)
    EditText mMaxPriceEt;
    @BindView(R.id.all_indicator)
    TextView mAllSortTv;// 综合排序
    @BindView(R.id.choose_indicator)
    TextView mIndicator;// 筛选
    @BindView(R.id.sale_indicator)
    TextView mSaleSortTv;
    @BindView(R.id.price_indicator)
    View mPriceSortTv;
    @BindView(R.id.drawerlayout)
    DrawerLayout mContainerDl;
    @BindView(R.id.slide_view)
    View mSlideView;
    @BindView(R.id.tv_chooseSearch)
    TextView mTvChooseSearch;
    @BindView(R.id.tv_resetSearch)
    TextView mTvResetSearch;
    private SProductListParams mSendParams;
    // private ProductListAdapter mProductListAdapter;
    private int mTopCategoryId;// 1级分类的id
    private int mCategoryId;// 3级分类的id
    private ProductListAdapter mProductListAdapter;
    private ProductSortPop mProductSortPop;
    private BrandAdapter mBrandAdapter;

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
        initSlideView();
        initListView();
    }

    private void initSlideView() {
        mBrandAdapter = new BrandAdapter(this);
        mBrandGv.setAdapter(mBrandAdapter);
        mBrandGv.setOnItemClickListener(this);

        mJdTakeTv.setOnClickListener(this);
        mPayWhenReceiveTv.setOnClickListener(this);
        mJustHashStockTv.setOnClickListener(this);

        mTvChooseSearch.setOnClickListener(this);
        mTvResetSearch.setOnClickListener(this);
    }

    private void initListView() {
        mProductListAdapter = new ProductListAdapter(this);
        mProductLv.setAdapter(mProductListAdapter);
        mProductLv.setOnItemClickListener(new ProductListItemClickListener());

        mIndicator.setOnClickListener(this);
        mAllSortTv.setOnClickListener(this);
        mSaleSortTv.setOnClickListener(this);
        mPriceSortTv.setOnClickListener(this);
        //初始化一个pop
        mProductSortPop = new ProductSortPop(this);
        mProductSortPop.setIProductSortChangeListener(this);
    }

    @Override
    protected void initData(Intent intent) {

        mCategoryId = intent.getIntExtra(CATEGORYID_KEY, 0);
        //初始化参数包装类
        mSendParams = new SProductListParams();
        mSendParams.categoryId = mCategoryId;
    }

    @Override
    protected void readyStartPresenter() {
        if (mCategoryId == 0 || mTopCategoryId == 0) {
            // finish();
            // return;
        }

        mPresenter.onStart(mSendParams);
    }

    @Override
    public void updateBrandGridView(List<BrandResultBean.BrandInfo> datas) {
        mBrandAdapter.setDatas(datas);
        mBrandAdapter.notifyDataSetChanged();
        FixedViewUtil.setGridViewHeightBasedOnChildren(mBrandGv, 3);
    }

    @Override
    public void updateProductList(List<ProductListResultBean.ProductListInfo> datas) {
        mProductListAdapter.setDatas(datas);
        mProductListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mBrandAdapter.tabItem(position);
    }

    /**
     * 确定搜索
     */
    public void chooseSearchClick(View v) {
        //计算抽屉参数
        try {
            calculateParams();
        } catch (Exception e) {
            showToast(e.getLocalizedMessage());
            return;
        }
        loadData();
        //关闭抽屉
        mContainerDl.closeDrawer(mSlideView);
    }

    //计算抽屉参数
    private void calculateParams() throws Exception {
        //获取服务里面的信息
        calculateDeliverChoose();
        //价格
        calculatePrice();
        //获取品牌信息
        calculatorBrand();
    }

    /**
     * 重置搜索
     */
    public void resetClick(View v) {
        resetSlideView();
        try {
            calculateParams();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resetSlideView() {
        //重置品牌
        mBrandAdapter.tabItem(-1);
        //重置价格
        mMinPriceEt.setText("");
        mMaxPriceEt.setText("");
        //重置配送服务
        mJdTakeTv.setSelected(false);
        mPayWhenReceiveTv.setSelected(false);
        mJustHashStockTv.setSelected(false);
    }

    // private void initSendParams() {
    //     mSendParams = new SProductListParams();
    //     mSendParams.categoryId = mCategoryId;
    // }

    private void calculateDeliverChoose() {
        int deliverChoose = 0;
        if (mJdTakeTv.isSelected()) {
            deliverChoose += 1;
        }
        if (mPayWhenReceiveTv.isSelected()) {
            deliverChoose += 2;
        }
        if (mJustHashStockTv.isSelected()) {
            deliverChoose += 4;
        }
        mSendParams.deliverChoose = deliverChoose;
    }

    private void calculatePrice() throws Exception {
        String minPriceStr = mMinPriceEt.getText().toString();
        String maxPriceStr = mMaxPriceEt.getText().toString();
        if (!TextUtils.isEmpty(minPriceStr) && !TextUtils.isEmpty(maxPriceStr)) {
            int minPr = Integer.parseInt(minPriceStr);
            int maxPr = Integer.parseInt(maxPriceStr);
            if (minPr > maxPr) {
                throw new Exception("价格区间设置有误,请重新设置");
            }
            mSendParams.minPrice = minPr;
            mSendParams.maxPrice = maxPr;
        }
    }

    private void calculatorBrand() {
        if (mBrandAdapter.mCurrentTabIndex != -1) {
            long itemId = mBrandAdapter.getItemId(mBrandAdapter.mCurrentTabIndex);
            mSendParams.brandId = itemId;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //筛选抽屉内部按钮
            case R.id.jd_take_tv:
            case R.id.paywhenreceive_tv:
            case R.id.justhasstock_tv:
                //取反选择状态
                v.setSelected(!v.isSelected());
                break;
            case R.id.tv_chooseSearch:
                //确定搜索
                chooseSearchClick(v);
                break;
            case R.id.tv_resetSearch:
                //重置参数
                resetClick(v);
                // TODO: 2017/2/11
                break;
            //筛选按钮
            case R.id.choose_indicator:
                //打开抽屉
                mContainerDl.openDrawer(mSlideView);
                break;
            //点击综合
            case R.id.all_indicator:
                //显示弹窗
                mProductSortPop.onShow(v);
                break;
            // 点击销量
            case R.id.sale_indicator:
                mSendParams.sortType = SProductListParams.SORTTYPE_SALE;
                loadData();
                break;
            // 点击价格排序
            case R.id.price_indicator:
                // 点击价格:默认/销量/从低到高----->从高到低
                // 点击价格:从高到低----->从低到高
                mSendParams.changePriceSortType();
                loadData();
                break;
        }
    }

    public void loadData() {
        mPresenter.onStart(mSendParams);
    }

    //popwindow点击监听事件
    @Override
    public void onSortChanged(String text) {

        mAllSortTv.setText(text);
        if (text.equals("综合")) {
            mSendParams.filterType = SProductListParams.FILTERTYPE_ALL;
        } else if (text.equals("新品")) {
            mSendParams.filterType = SProductListParams.FILTERTYPE_NEW;
        } else if (text.equals("评价")) {
            mSendParams.filterType = SProductListParams.filterType_COMMENT;
        }
        loadData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
