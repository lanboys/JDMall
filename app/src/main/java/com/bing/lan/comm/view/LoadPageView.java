package com.bing.lan.comm.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.bing.lan.jdmall.R;
import com.bing.lan.comm.utils.LogUtil;

/**
 * @author 蓝兵
 * @time 2017/1/10  18:42
 */
public abstract class LoadPageView extends FrameLayout {

    private static final int STATE_EMPTY = 0;
    private static final int STATE_ERROR = 1;
    private static final int STATE_LOADING = 2;
    private static final int STATE_SUCCESS = 3;
    /**
     * 重试次数
     */
    private static final int RELOAD_TIMES = 3;
    protected LogUtil log = LogUtil.getLogUtil(getClass(), 1);
    private View mEmptyPager;
    private View mErrorPager;
    private View mLoadingPager;
    private View mSuccessPager;
    private int mCurrentState = STATE_LOADING;
    // private LoadDataTask mLoadDataTask;
    private OnErrorButtonListener mErrorButtonListener;
    private int mErrorCount;

    public LoadPageView(Context context) {
        this(context, null);
    }

    public LoadPageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCommonView(context);
    }

    public void setErrorButtonListener(OnErrorButtonListener errorButtonListener) {
        mErrorButtonListener = errorButtonListener;
    }

    private void initCommonView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);


        mLoadingPager = View.inflate(context, R.layout.pager_loading, null);
        mEmptyPager = View.inflate(context, R.layout.pager_empty, null);
        mErrorPager = View.inflate(context, R.layout.pager_error, null);

//        mLoadingPager = inflater.inflate(R.layout.pager_loading, this, false);
//        mEmptyPager = inflater.inflate(R.layout.pager_loading, this, false);
//        mErrorPager = inflater.inflate(R.layout.pager_loading, this, false);

        this.addView(mLoadingPager);
        this.addView(mEmptyPager);
        this.addView(mErrorPager);

        mSuccessPager = initSuccessView(inflater, this);
        this.addView(mSuccessPager);

        mErrorPager.findViewById(R.id.error_btn_retry).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // loadingDataAndRefreshState();

                mCurrentState = STATE_LOADING;
                refreshViewByState();

                if (mErrorButtonListener != null) {
                    mErrorButtonListener.OnErrorButtonClick(v);
                }
            }
        });

        refreshViewByState();
    }

    private void refreshViewByState() {

        mEmptyPager.setVisibility(mCurrentState == STATE_EMPTY ? VISIBLE : GONE);
        mErrorPager.setVisibility(mCurrentState == STATE_ERROR ? VISIBLE : GONE);
        mLoadingPager.setVisibility(mCurrentState == STATE_LOADING ? VISIBLE : GONE);

        mSuccessPager.setVisibility(mCurrentState == STATE_SUCCESS ? VISIBLE : GONE);
    }

    protected abstract View initSuccessView(LayoutInflater inflater, LoadPageView parent);

    /**
     * 外界接口
     *
     * @param viewState
     */
    public void setViewState(LoadDataResult viewState) {
        mCurrentState = viewState.getState();

        //记录显示错误页面的次数,次数大于2将显示空白界面
        if (mCurrentState == STATE_ERROR) {
            mErrorCount++;
            if (mErrorCount > RELOAD_TIMES) {
                mCurrentState = STATE_EMPTY;
            }

            log.d("setViewState():出现错误页面的次数 " + mErrorCount);
        }

        //加载成功后清除计数
        if (mCurrentState == STATE_SUCCESS) {
            resetErrorCount();
        }

        //更新状态
        refreshViewByState();
    }

    public void resetErrorCount() {
        mErrorCount = 0;
    }

    // @Deprecated
    // private void loadingDataAndRefreshState() {
    //
    //     //加载成功 或者 正在加载时,不再进行加载任务
    //     if (mCurrentState != STATE_SUCCESS /*&& mLoadDataTask == null*/) {
    //         //第一次加载失败,重新加载时显示loading界面
    //         mCurrentState = STATE_LOADING;
    //         refreshViewByState();
    //
    //         // mLoadDataTask = new LoadDataTask();
    //         // new Thread(mLoadDataTask).start();
    //         // ThreadPoolProxyFactory.createNormalThreadPoolProxy().execute(mLoadDataTask);
    //     }
    // }

    // @Deprecated
    // private LoadDataResult initData() {
    //     return null;
    // }

    public enum LoadDataResult {

        LOAD_SUCCESS(STATE_SUCCESS), LOAD_ERROR(STATE_ERROR), /*LOAD_EMPTY(STATE_EMPTY),*/ LOAD_LOADING(STATE_LOADING);

        private final int mState;

        LoadDataResult(int state) {
            this.mState = state;
        }

        public int getState() {
            return mState;
        }

    }

    public interface OnErrorButtonListener {

        void OnErrorButtonClick(View v);
    }

    // @Deprecated
    // private class LoadDataTask implements Runnable {
    //
    //     @Override
    //     public void run() {
    //         //获取加载数据的结果,有可能返回null,所以安全起见做个非空判断
    //         LoadDataResult loadDataResult = initData();
    //         if (loadDataResult != null) {
    //             mCurrentState = loadDataResult.getState();
    //         } else {
    //             //加载结果返回null时,2s后显示为空页面
    //             SystemClock.sleep(2 * 1000);
    //             mCurrentState = STATE_EMPTY;
    //         }
    //
    //         AppUtil.postTaskSafe(new Runnable() {
    //             @Override
    //             public void run() {
    //                 refreshViewByState();
    //             }
    //         });
    //
    //         mLoadDataTask = null;
    //     }
    // }
}
