package com.bing.lan.jdmall.ui.productdetail.introduce;

import com.bing.lan.comm.base.mvp.fragment.BaseFragmentPresenter;
import com.bing.lan.jdmall.bean.ProductInfoResultBean;

/**
 * @author 蓝兵
 * @time 2017/2/8  10:27
 */
public class ProductIntroducePresenter extends
        BaseFragmentPresenter<IProductIntroduceContract.IProductIntroduceView,
                IProductIntroduceContract.IProductIntroduceModule>
        implements IProductIntroduceContract.IProductIntroducePresenter {

    @Override
    public void onStart(Object... params) {
        mModule.loadData(0, this, params);
    }

    @Override
    public void onSuccess(int action, Object data) {

        switch (action) {
            case 0:
                // mView.updateBanner(((ProductInfoResultBean.ProductInfo) data).getImgUrls());
                mView.updateData((ProductInfoResultBean.ProductInfo) data);

                break;
        }
    }

    @Override
    public void onError(int action, Throwable e) {

    }
}
