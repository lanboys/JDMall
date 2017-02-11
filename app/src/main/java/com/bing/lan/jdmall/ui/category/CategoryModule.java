package com.bing.lan.jdmall.ui.category;

import com.bing.lan.comm.base.mvp.BaseModule;
import com.bing.lan.comm.base.mvp.IBaseContract;
import com.bing.lan.jdmall.bean.CategoryResultBean;
import com.bing.lan.jdmall.bean.SubCategoryResultBean;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static com.bing.lan.jdmall.ui.category.CategoryPresenter.LOAD_SUB_CATEGORYINFO;
import static com.bing.lan.jdmall.ui.category.CategoryPresenter.LOAD_TOP_CATEGORYINFO;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:26
 */
public class CategoryModule extends BaseModule
        implements ICategoryContract.ICategoryModule {

    @Override
    public void releaseTask() {

    }

    @Override
    public void loadData(int action, IBaseContract.OnDataChangerListener listener, Object... parameter) {
        switch (action) {
            case LOAD_TOP_CATEGORYINFO:
                loadCategory(action, listener);
                break;
            case LOAD_SUB_CATEGORYINFO:
                loadSubCategory(action, listener, (Integer) parameter[0]);
                break;
        }
    }

    private void loadSubCategory(final int action, final IBaseContract.OnDataChangerListener listener, Integer parentId) {
        mApiService.loadCategory(parentId)
                .filter(new Func1<SubCategoryResultBean, Boolean>() {
                    @Override
                    public Boolean call(SubCategoryResultBean subCategoryResultBean) {
                        int size = subCategoryResultBean.getResult().size();
                        log.d("call(): loadSubCategory--size" + size);
                        if (size > 0) {
                            return true;
                        } else {
                            // 此处抛出的异常也会走onError
                            throw new RuntimeException(subCategoryResultBean.getErrorMsg() + "二级商品数量为零");
                        }
                    }
                })
                .map(new Func1<SubCategoryResultBean, List<SubCategoryResultBean.SubCategoryInfoBean>>() {
                    @Override
                    public List<SubCategoryResultBean.SubCategoryInfoBean> call(SubCategoryResultBean subCategoryResultBean) {
                        return subCategoryResultBean.getResult();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<SubCategoryResultBean.SubCategoryInfoBean>>() {
                    @Override
                    public void onNext(List<SubCategoryResultBean.SubCategoryInfoBean> subCategoryInfoBeen) {
                        listener.onSuccess(action, subCategoryInfoBeen);
                        log.d("onNext(): 二级商品数量" + subCategoryInfoBeen.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(action, e);
                        log.e("onError(): 加载二级商品失败 " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onCompleted() {
                        log.i("onCompleted(): 加载二级商品完成");
                    }
                });
    }

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
                            throw new RuntimeException(bannerResultBean.getErrorMsg() + "分类数量为零");
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
                        log.d("onNext(): 分类数量" + data.size());
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(action, e);
                        log.e("onError(): 加载分类失败 " + e.getLocalizedMessage());
                    }

                    @Override
                    public void onCompleted() {
                        log.i("onCompleted(): 加载分类完成");
                    }
                });
    }
}
