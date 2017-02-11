package com.bing.lan.comm.view.pop;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bing.lan.jdmall.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.bing.lan.jdmall.R.id.all_sort;

public class ProductSortPop implements IPopProtocal, OnClickListener {

    @BindView(R.id.all_sort)
    TextView mAllSortTv;
    @BindView(R.id.new_sort)
    TextView mNewSortTv;
    @BindView(R.id.comment_sort)
    TextView mCommentSortTv;
    private PopupWindow mPopupWindow;
    private Context mContext;

    private IProductSortChangeListener mListener;
    private Unbinder mUnbinder;

    public ProductSortPop(Context c) {
        mContext = c;
        initUI();
    }

    public void setIProductSortChangeListener(IProductSortChangeListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View v) {
        onDismiss();
        switch (v.getId()) {
            case all_sort:
                if (mListener != null) {
                    mListener.onSortChanged("综合");
                }
                break;
            case R.id.new_sort:
                if (mListener != null) {
                    mListener.onSortChanged("新品");
                }
                break;
            case R.id.comment_sort:
                if (mListener != null) {
                    mListener.onSortChanged("评价");
                }
                break;
        }
    }

    @Override
    public void initUI() {
        View contentView = LayoutInflater.from(mContext).inflate(
                R.layout.product_sort_pop_view, null, false);
        // mAllSortTv = (TextView) contentView.findViewById(R.id.all_sort);
        // mNewSortTv = (TextView) contentView.findViewById(R.id.new_sort);
        // mCommentSortTv = (TextView) contentView.findViewById(R.id.comment_sort);

        mUnbinder = ButterKnife.bind(this, contentView);

        mAllSortTv.setOnClickListener(this);
        mNewSortTv.setOnClickListener(this);
        mCommentSortTv.setOnClickListener(this);

        mPopupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);

        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        // 更新PopupWindow的状态
        mPopupWindow.update();
    }

    @Override
    public void onShow(View anchorView) {
        if (mPopupWindow != null) {
            mPopupWindow.showAsDropDown(anchorView);
        }
    }

    @Override
    public void onDismiss() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }
}
