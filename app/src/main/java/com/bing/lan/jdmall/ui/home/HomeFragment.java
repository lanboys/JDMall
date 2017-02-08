package com.bing.lan.jdmall.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.view.HorizontalListView;
import com.bing.lan.jdmall.R;
import com.bing.lan.jdmall.adapter.SecondKillAdapter;
import com.bing.lan.jdmall.bean.SecKillResultBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 */
public class HomeFragment extends BaseFragment<IHomeContract.IHomePresenter>
        implements IHomeContract.IHomeView {

    @BindView(R.id.item_banner)
    Banner mBanner;
    @BindView(R.id.horizon_listview)
    HorizontalListView mSecondKillLv;

    private SecondKillAdapter mSecondKillAdapter;

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

    private void init() {
        initListView();
        initBanner();
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
    public void updateBanner(List<String> imageUrls) {
        mBanner.setImages(imageUrls);
        mBanner.start();
    }

    @Override
    public void showBanner(boolean isShow) {
        mBanner.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
