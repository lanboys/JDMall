package com.bing.lan.jdmall.ui.productdetail.introduce;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.bing.lan.comm.api.ApiService;
import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.view.LoadPageView;
import com.bing.lan.jdmall.R;
import com.bing.lan.jdmall.ui.productdetail.ProductInfoActivity;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *
 */
public class ProductIntroduceFragment extends
        BaseFragment<IProductIntroduceContract.IProductIntroducePresenter>
        implements IProductIntroduceContract.IProductIntroduceView {

    @BindView(R.id.introduce_banner)
    Banner mBanner;
    private long mProductId;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_product_introduce;
    }

    @Override
    protected void startInject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void initData(Intent intent) {
        mProductId = intent.getLongExtra(ProductInfoActivity.PID_KEY, 0);
    }

    @Override
    protected void readyStartPresenter() {
        setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_SUCCESS);
        mPresenter.onStart(mProductId);
    }

    @Override
    protected void initView() {
        //设置图片加载器(低版本没有此方法)
        mBanner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                //加载图片
                loadImage(path, imageView);
            }
        });
        mBanner.setVisibility(View.GONE);
    }

    public void updateBanner(List<String> imageUrls) {
        ArrayList<String> imageUrl = new ArrayList<>();
        String url;
        for (String s : imageUrls) {
            url = ApiService.BASE_URL + s;
            imageUrl.add(url);
            log.d("updateBanner(): " + url);
        }

        if (mBanner != null && imageUrls != null && imageUrls.size() > 0) {
            mBanner.setImages(imageUrl);
            mBanner.setVisibility(View.VISIBLE);
            mBanner.start();
        }
    }
}
