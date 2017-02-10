package com.bing.lan.jdmall.ui.category;

import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.comm.base.mvp.fragment.BaseFragmentModule;
import com.bing.lan.jdmall.bean.CategoryResultBean;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public class CategoryModule extends BaseFragmentModule
        implements ICategoryContract.ICategoryModule {

    @Override
    public void loadCategory(final int action, final IBaseContract.OnDataChangerListener listener) {
        mApiService.loadCategory()
                .filter(new Func1<CategoryResultBean, Boolean>() {
                    @Override
                    public Boolean call(CategoryResultBean bannerResultBean) {
                        int size = bannerResultBean.getResult().size();
                        if (size > 0) {
                            return true;
                        } else {
                            // 此处抛出的异常也会走onError
                            throw new RuntimeException(bannerResultBean.getErrorMsg() + "广告数量为零");
                        }
                    }
                })
                .map(new Func1<CategoryResultBean, List<CategoryResultBean.TopCategoryInfoBean>>() {
                    @Override
                    public List<CategoryResultBean.TopCategoryInfoBean> call(CategoryResultBean bannerResultBean) {

                        return bannerResultBean.getResult();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<CategoryResultBean.TopCategoryInfoBean>>() {

                    @Override
                    public void onNext(List<CategoryResultBean.TopCategoryInfoBean> data) {
                        listener.onSuccess(action, data);
                        log.d("onNext(): 轮播图数量" + data.size());
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
}
