package com.bing.lan.jdmall.ui.home;

import com.bing.lan.comm.api.ApiManager;
import com.bing.lan.comm.api.ApiService;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.fragment.BaseFragmentModule;
import com.bing.lan.jdmall.bean.BannerResultBean;
import com.bing.lan.jdmall.bean.SecKillResultBean;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public class HomeModule extends BaseFragmentModule
        implements IHomeContract.IHomeModule {

    private ApiService mApiService;

    public HomeModule() {
        mApiService = ApiManager.getApiService();
    }

    @Override
    public void loadBanner(final int action, int adKind, final IBaseContract.OnDataChangerListener listener) {
        mApiService.loadBanner(adKind)
                .filter(new Func1<BannerResultBean, Boolean>() {
                    @Override
                    public Boolean call(BannerResultBean bannerResultBean) {
                        int size = bannerResultBean.getResult().size();
                        if (size > 0) {
                            return true;
                        } else {
                            // 此处抛出的异常也会走onError
                            throw new RuntimeException(bannerResultBean.getErrorMsg() + "广告数量为零");
                        }
                    }
                })
                .map(new Func1<BannerResultBean, List<String>>() {
                    @Override
                    public List<String> call(BannerResultBean bannerResultBean) {

                        List<BannerResultBean.BannerInfoBean> beannerList
                                = bannerResultBean.getResult();
                        List<String> list = new ArrayList<>();

                        for (BannerResultBean.BannerInfoBean bannerInfoBean : beannerList) {
                            list.add(ApiService.BASE_URL + bannerInfoBean.getAdUrl());
                        }
                        return list;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<String>>() {

                    @Override
                    public void onNext(List<String> data) {
                        listener.onSuccess(action, data);
                        log.d("onNext(): " + data.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(action, e);
                        log.e("onError(): 加载轮播图失败 " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onCompleted() {
                        log.i("onCompleted(): 加载轮播图完成");
                    }
                });
    }

    @Override
    public void loadSecKill(final int action, final IBaseContract.OnDataChangerListener listener) {
        mApiService.loadSecKill()
                .filter(new Func1<SecKillResultBean, Boolean>() {
                    @Override
                    public Boolean call(SecKillResultBean secKillResultBean) {
                        int total = secKillResultBean.getResult().getTotal();
                        if (total > 0) {
                            return true;
                        } else {
                            // 此处抛出的异常也会走onError
                            throw new RuntimeException(secKillResultBean.getErrorMsg() + "广告数量为零");
                        }
                    }
                })
                .map(new Func1<SecKillResultBean, List<SecKillResultBean.SecKillInfoBean.RowsBean>>() {
                    @Override
                    public List<SecKillResultBean.SecKillInfoBean.RowsBean> call(SecKillResultBean secKillResultBean) {
                        return secKillResultBean.getResult().getRows();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<SecKillResultBean.SecKillInfoBean.RowsBean>>() {
                    @Override
                    public void onNext(List<SecKillResultBean.SecKillInfoBean.RowsBean> rowsBeen) {
                        listener.onSuccess(action, rowsBeen);
                        log.d("onNext(): " + rowsBeen.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(action, e);
                        log.e("onError():加载秒杀信息失败  " + e.getLocalizedMessage());
                        log.e("onError():加载秒杀信息失败  " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onCompleted() {
                        log.i("onCompleted(): 加载秒杀信息完成");
                    }
                });
    }
}
