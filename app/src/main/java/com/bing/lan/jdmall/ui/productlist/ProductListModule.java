package com.bing.lan.jdmall.ui.productlist;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.activity.BaseActivityModule;
import com.bing.lan.comm.utils.RxJavaUtils;
import com.bing.lan.jdmall.bean.BrandResultBean;
import com.bing.lan.jdmall.bean.ProductListResultBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.bing.lan.jdmall.ui.productlist.ProductListPresenter.BRAND_ACTION;
import static com.bing.lan.jdmall.ui.productlist.ProductListPresenter.PRODUCT_LIST_ACTION;

/**
 * @author 蓝兵
 * @time 2017/2/6  19:12
 */
public class ProductListModule extends BaseActivityModule
        implements IProductListContract.IProductListModule {

    private Subscription mBrandSubscribe;
    private Subscription mProductListSubscribe;

    @Override
    public void releaseTask() {
        RxJavaUtils.releaseSubscribe(mBrandSubscribe);
        RxJavaUtils.releaseSubscribe(mProductListSubscribe);
    }

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {
        switch (action) {

            case BRAND_ACTION:
                log.d("loadData():BRAND_ACTION " + "加载品牌信息" + parameter);
                loadBrand(action, listener, (int) parameter[0]);

                break;
            case PRODUCT_LIST_ACTION:
                log.d("loadData(): PRODUCT_LIST_ACTION" + "加载产品列表" + parameter);
                loadProductList(action, listener, (SProductListParams) parameter[0]);
                break;
        }
    }

    @Override
    public void loadBrand(final int action, final IBaseContract.OnDataChangerListener listener, int categoryId) {

        mBrandSubscribe = mApiService.loadBrand(categoryId)
                .filter(new Func1<BrandResultBean, Boolean>() {
                    @Override
                    public Boolean call(BrandResultBean bannerResultBean) {
                        int size = bannerResultBean.getResult().size();
                        if (size > 0) {
                            return true;
                        } else {
                            // 此处抛出的异常也会走onError
                            throw new RuntimeException(bannerResultBean.getErrorMsg() + "品牌数量为零");
                        }
                    }
                })
                .map(new Func1<BrandResultBean, List<BrandResultBean.BrandInfo>>() {
                    @Override
                    public List<BrandResultBean.BrandInfo> call(BrandResultBean brandResultBean) {
                        return brandResultBean.getResult();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<BrandResultBean.BrandInfo>>() {

                    @Override
                    public void onNext(List<BrandResultBean.BrandInfo> data) {
                        listener.onSuccess(action, data);
                        log.d("onNext(): 品牌数量" + data.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(action, e);
                        log.e("onError(): 加载品牌失败 " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onCompleted() {
                        log.i("onCompleted(): 加载品牌完成");
                    }
                });
    }

    @Override
    public void loadProductList(final int action, final IBaseContract.OnDataChangerListener listener, SProductListParams paramsBean) {
        HashMap<String, String> hashMap = buildProductListSendParams(paramsBean);
        mProductListSubscribe = mApiService.loadProductList(hashMap)
                .filter(new Func1<ProductListResultBean, Boolean>() {
                    @Override
                    public Boolean call(ProductListResultBean bannerResultBean) {
                        int size = bannerResultBean.getResult().getTotal();
                        if (size > 0) {
                            return true;
                        } else {
                            // 此处抛出的异常也会走onError
                            throw new RuntimeException(bannerResultBean.getErrorMsg() + "产品数量为零");
                        }
                    }
                })
                .map(new Func1<ProductListResultBean, List<ProductListResultBean.ProductListInfo.ProductInfo>>() {
                    @Override
                    public List<ProductListResultBean.ProductListInfo.ProductInfo> call(ProductListResultBean productListResultBean) {
                        return productListResultBean.getResult().getRows();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ProductListResultBean.ProductListInfo.ProductInfo>>() {

                    @Override
                    public void onNext(List<ProductListResultBean.ProductListInfo.ProductInfo> data) {
                        listener.onSuccess(action, data);
                        log.d("onNext(): 产品数量" + data.size());
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

    private HashMap<String, String> buildProductListSendParams(
            SProductListParams paramsBean) { 
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("categoryId", paramsBean.categoryId + "");
        paramsMap.put("filterType", paramsBean.filterType + "");
        if (paramsBean.sortType != 0) {
            paramsMap.put("sortType", paramsBean.sortType + "");
        }
        paramsMap.put("deliverChoose", paramsBean.deliverChoose + "");
        if (paramsBean.minPrice != 0 && paramsBean.maxPrice != 0) {
            paramsMap.put("minPrice", paramsBean.minPrice + "");
            paramsMap.put("maxPrice", paramsBean.maxPrice + "");
        }
        if (paramsBean.brandId != 0) {
            paramsMap.put("brandId", paramsBean.brandId + "");
        }

        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            log.d("buildProductListSendParams(): " + entry.getKey());
            log.d("buildProductListSendParams(): " + entry.getValue());
        }

        return paramsMap;
    }
}
