package com.bing.lan.jdmall.ui.home;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.utils.FixedViewUtil;
import com.bing.lan.comm.view.HorizontalListView;
import com.bing.lan.jdmall.R;
import com.bing.lan.jdmall.adapter.RecommandAdapter;
import com.bing.lan.jdmall.adapter.SecondKillAdapter;
import com.bing.lan.jdmall.bean.GetYourLikeResultBean;
import com.bing.lan.jdmall.bean.SecKillResultBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import butterknife.BindView;

/**
 *
 */
public class HomeFragment extends BaseFragment<IHomeContract.IHomePresenter>
        implements IHomeContract.IHomeView {

    @BindView(R.id.item_banner)
    Banner mBanner;
    @BindView(R.id.horizon_listview)
    HorizontalListView mSecondKillLv;
    @BindView(R.id.recommend_gv)
    GridView mRecommandGv;

    private SecondKillAdapter mSecondKillAdapter;
    private RecommandAdapter mRecommandAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void startInject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void readyStartPresenter() {
        init();
        mPresenter.onStart();
    }

    @Override
    protected void initView() {

    }

    private void init() {
        initListView();
        initGridView();
        initBanner();
    }

    private void initGridView() {
        mRecommandAdapter = new RecommandAdapter(getActivity());
        mRecommandGv.setAdapter(mRecommandAdapter);

    }

    private void initListView() {
        //设置秒杀模块
        mSecondKillAdapter = new SecondKillAdapter(getActivity());
        mSecondKillLv.setAdapter(mSecondKillAdapter);
    }

    protected void initBanner() {
        showBanner(false);
        //设置图片加载器(低版本没有此方法)
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                //加载图片
                loadImage(path, imageView);
            }
        });
    }

    @Override
    public void updateSecKill(List<SecKillResultBean.SecKillInfoBean.RowsBean> rowsBeen) {
        mSecondKillAdapter.setDatas(rowsBeen);
        mSecondKillAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateGetYourLike(List<GetYourLikeResultBean.GetYourLikeInfoBean.RowsBean> rowsBeen) {
        mRecommandAdapter.setDatas(rowsBeen);
        mRecommandAdapter.notifyDataSetChanged();
        //必须在加载数据后进行测量
        FixedViewUtil.setGridViewHeightBasedOnChildren(mRecommandGv, 2);
    }

    @Override
    public void updateBanner(List<String> imageUrls) {
        mBanner.setImages(imageUrls);
        mBanner.start();
    }

    @Override
    public void showBanner(boolean isShow) {
        mBanner.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

}
