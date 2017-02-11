package com.bing.lan.jdmall.ui.category;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bing.lan.comm.api.ApiService;
import com.bing.lan.comm.base.BaseViewHolder;
import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.utils.CastUtil;
import com.bing.lan.comm.utils.ToastUtil;
import com.bing.lan.jdmall.R;
import com.bing.lan.jdmall.bean.CategoryResultBean;
import com.bing.lan.jdmall.bean.SubCategoryResultBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.bing.lan.jdmall.ui.category.CategoryFragment.LoadDataResult.LOAD_EMPTY;
import static com.bing.lan.jdmall.ui.category.CategoryFragment.LoadDataResult.LOAD_ERROR;
import static com.bing.lan.jdmall.ui.category.CategoryFragment.LoadDataResult.LOAD_LOADING;
import static com.bing.lan.jdmall.ui.category.CategoryPresenter.LOAD_SUB_CATEGORYINFO;

/**
 *
 */
public class CategoryFragment
        extends BaseFragment<ICategoryContract.ICategoryPresenter>
        implements ICategoryContract.ICategoryView, View.OnClickListener,
        SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener,
        AdapterView.OnItemClickListener {

    @BindView(R.id.pull_refresh_view)
    protected SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.lv_refresh)
    protected ListView mRefreshListView;
    @BindView(R.id.scrollview)
    ScrollView mScrollView;
    @BindView(R.id.category_container)
    LinearLayout mLinearLayout;
    private List<String> titles;
    //装装ScrollView的item的TextView的数组
    private ArrayList<TextView> textViewArray;
    //装ScrollView的item的数组
    private ArrayList<View> views;
    private CategoryAdapter mRefreshAdapter;
    //
    private LoadMoreHolder mLoadMoreHolder;
    private Banner mBanner;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_category;
    }

    @Override
    protected void startInject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void initView() {

        //        View view = View.inflate(AppUtil.getAppContext(), R.layout.fragment_list_view_refresh, null);
        //         ButterKnife.bind(this, view);
        log.d("initView(): ");
        if (isOpenHeaderBannerView()) {
            mRefreshListView.addHeaderView(addHeaderView());
        }
        if (isOpenFooterLoadMoreView()) {
            mRefreshListView.addFooterView(addFooterView());
        }

        mRefreshAdapter = new CategoryAdapter();
        mRefreshListView.setAdapter(mRefreshAdapter);
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setEnabled(false);

        mRefreshListView.setOnScrollListener(this);
        mRefreshListView.setOnItemClickListener(this);
    }

    @Override
    protected void readyStartPresenter() {
        // setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_SUCCESS);
        // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new FragmentOne()).commit();
        mPresenter.onStart();
    }

    protected View addFooterView() {
        View footerView = mLayoutInflater.inflate(R.layout.item_load_more, null);
        mLoadMoreHolder = new LoadMoreHolder(footerView);
        return footerView;
    }

    public boolean isOpenHeaderBannerView() {
        return true;
    }

    public boolean isOpenFooterLoadMoreView() {
        return false;
    }

    protected View addHeaderView() {
        View headerView = mLayoutInflater.inflate(R.layout.item_banner_layout, null);
        mBanner = (Banner) headerView.findViewById(R.id.item_banner);

        int screenHeight = 800;
        AbsListView.LayoutParams layoutParams =
                new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, screenHeight / 3);  //设置轮播图高度
        mBanner.setLayoutParams(layoutParams);
        mBanner.setVisibility(View.GONE);

        //设置图片加载器(低版本没有此方法)
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {

                getBannerImageSetting(imageView);
                //加载图片
                loadImage(path, imageView);
            }
        });
        List<?> imageUrls = getBannerImageUrls();

        mBanner.setImages(imageUrls);
        //设置方法全部调用完毕时最后调用
        mBanner.start();

        return headerView;
    }

    public void updateHeaderBanner(List<?> imageUrls) {
        if (mBanner != null && imageUrls != null && imageUrls.size() > 0) {
            mBanner.setImages(imageUrls);
            mBanner.setVisibility(View.VISIBLE);
            mBanner.start();
        }
    }

    protected List<?> getBannerImageUrls() {
        //默认图片地址
        List<Object> imageUrls = new ArrayList<>();
        imageUrls.add("");
        return imageUrls;
    }

    protected void getBannerImageSetting(ImageView imageView) {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
    }

    // @Override
    public void onClick(View v) {
        if (mRefreshAdapter != null) {
            mRefreshAdapter.clearAllData();
        }
        CategoryResultBean.TopCategoryInfoBean topCategoryInfoBean = CastUtil.cast(v.getTag());

        log.d("onClick(): " + topCategoryInfoBean.getId());
        mPresenter.loadData(LOAD_SUB_CATEGORYINFO, topCategoryInfoBean.getId());

        updateBanner(topCategoryInfoBean);

        ToastUtil.showToast(getActivity(), titles.get(v.getId()));
        changeTextColor(v.getId());
        changeTextLocation(v.getId());
    }

    private void updateBanner(CategoryResultBean.TopCategoryInfoBean topCategoryInfoBean) {
        mBanner.setVisibility(View.GONE);
        ArrayList<String> imageUrl = new ArrayList<>();
        String url = ApiService.BASE_URL + topCategoryInfoBean.getBannerUrl();
        imageUrl.add(url);
        updateHeaderBanner(imageUrl);
    }

    /**
     * 改变textView的颜色
     *
     * @param id
     */
    private void changeTextColor(int id) {
        for (int i = 0; i < textViewArray.size(); i++) {
            if (i != id) {
                textViewArray.get(i).setBackgroundResource(android.R.color.transparent);
                textViewArray.get(i).setTextColor(0xff000000);
            } else {
                textViewArray.get(i).setBackgroundResource(android.R.color.white);
                textViewArray.get(i).setTextColor(0xffff5d5e);
            }
        }
    }

    /**
     * 改变栏目位置
     */
    private void changeTextLocation(int index) {

        //views[clickPosition].getTop()针对其父视图的顶部相对位置
        int x = (views.get(index).getTop() - mScrollView.getHeight() / 2);
        mScrollView.smoothScrollTo(0, x);
    }

    public void setSwipeRefreshLayoutEnabled(boolean b) {
        if (mRefreshLayout != null) {
            mRefreshLayout.setEnabled(b);
        }
    }

    @Override
    public void updateSlideMenu(List<CategoryResultBean.TopCategoryInfoBean> list) {
        titles = new ArrayList<>();
        textViewArray = new ArrayList<>();
        views = new ArrayList<>();

        View view;

        for (int i = 0; i < list.size(); i++) {
            CategoryResultBean.TopCategoryInfoBean topCategoryInfoBean = list.get(i);
            String name = topCategoryInfoBean.getName();

            view = View.inflate(getActivity(), R.layout.item_scrollview, null);
            view.setId(i);
            view.setOnClickListener(this);
            view.setTag(topCategoryInfoBean);

            TextView tv = (TextView) view.findViewById(R.id.textview);
            tv.setText(name);
            mLinearLayout.addView(view);

            titles.add(name);
            textViewArray.add(tv);
            views.add(view);
        }

        changeTextColor(0);
    }

    @Override
    public void updateListView(List<SubCategoryResultBean.SubCategoryInfoBean> list) {
        mRefreshAdapter.setDataAndRefresh(list);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public enum LoadDataResult {

        LOAD_HIDE_ALL,
        LOAD_ERROR,
        LOAD_EMPTY,
        LOAD_LOADING

    }

    class LoadMoreHolder extends BaseViewHolder<LoadDataResult> {

        protected LoadDataResult isVisible;

        @BindView(R.id.item_loadmore_container_loading)
        LinearLayout itemLoadMoreContainerLoading;
        @BindView(R.id.item_loadmore_container_retry)
        LinearLayout itemLoadMoreContainerRetry;
        @BindView(R.id.item_loadmore_tv_retry)
        TextView itemLoadMoreTvRetry;
        @BindView(R.id.item_loadmore_container_empty)
        LinearLayout itemLoadMoreContainerEmpty;

        public LoadMoreHolder(View viewHolder) {
            super(viewHolder);

            itemLoadMoreTvRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // mPresenter.loadMoreData();
                }
            });
        }

        @Override
        public void refreshViewData(LoadDataResult result, int position) {

            itemLoadMoreContainerEmpty.setVisibility(View.GONE);
            itemLoadMoreContainerRetry.setVisibility(View.GONE);
            itemLoadMoreContainerLoading.setVisibility(View.GONE);

            switch (result) {
                case LOAD_LOADING:
                    itemLoadMoreContainerLoading.setVisibility(View.VISIBLE);
                    isVisible = LOAD_LOADING;
                    break;
                case LOAD_EMPTY:
                    itemLoadMoreContainerEmpty.setVisibility(View.VISIBLE);
                    isVisible = LOAD_EMPTY;
                    break;
                case LOAD_ERROR:
                    isVisible = LOAD_ERROR;
                    itemLoadMoreContainerRetry.setVisibility(View.VISIBLE);
                    break;
                case LOAD_HIDE_ALL:
                    //正在加载页面是不能隐藏的
                    if (isVisible == LOAD_LOADING) {
                        itemLoadMoreContainerLoading.setVisibility(View.VISIBLE);
                    } else {
                        isVisible = null;
                    }
                    break;
            }
        }
    }
}
