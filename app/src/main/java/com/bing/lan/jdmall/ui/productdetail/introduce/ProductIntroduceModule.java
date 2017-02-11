package com.bing.lan.jdmall.ui.productdetail.introduce;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.fragment.BaseFragmentModule;
import com.bing.lan.jdmall.bean.ProductInfoResultBean;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public class ProductIntroduceModule extends BaseFragmentModule
        implements IProductIntroduceContract.IProductIntroduceModule {

    @Override
    public void releaseTask() {

    }

    @Override
    public void loadData(final int action, final IBaseContract.OnDataChangerListener listener, Object... parameter) {
        mApiService.loadProductInfo((Long) parameter[0])
                .filter(new Func1<ProductInfoResultBean, Boolean>() {
                    @Override
                    public Boolean call(ProductInfoResultBean productDetailResultBean) {
                        return productDetailResultBean.getResult() != null;
                    }
                })
                .map(new Func1<ProductInfoResultBean, ProductInfoResultBean.ProductInfo>() {
                    @Override
                    public ProductInfoResultBean.ProductInfo call(ProductInfoResultBean productInfoResultBean) {
                        return productInfoResultBean.getResult();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProductInfoResultBean.ProductInfo>() {
                    @Override
                    public void onNext(ProductInfoResultBean.ProductInfo productInfo) {
                        listener.onSuccess(action, productInfo);
                        log.d("onNext(): 产品数量" + productInfo.getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(action, e);
                        log.e("onError(): 加载产品失败 " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onCompleted() {
                        log.i("onCompleted(): 加载产品完成");
                    }
                });
    }
}
