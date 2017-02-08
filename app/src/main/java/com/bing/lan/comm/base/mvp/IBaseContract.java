package com.bing.lan.comm.base.mvp;


import android.widget.ImageView;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;

public interface IBaseContract {

    interface IBaseView<T extends IBasePresenter> {
        /**
         * 当P层发生错误时调用
         */
        void showError(String msg, Throwable e);

        void showToast(String msg);

        void showDialog(String msg);

        T getPresenter();

        void startActivity(Class<? extends BaseActivity> clazz, boolean isFinish);

        void startActivity(Class<? extends BaseActivity> clazz);

    }

    interface IBasePresenter<T extends IBaseView, M extends IBaseModule> extends OnDataChangerListener {

        void onAttachView(T view);

        void onDetachView();

        void onStart();

        void setModule(M module);

        void loadImage(Object path, ImageView imageView);
    }

    interface IBaseModule {

        void loadImage(Object path, ImageView imageView);

    }

    interface OnDataChangerListener {

        void onSuccess(int action, Object data);

        void onError(int action, Throwable e);
    }
}
