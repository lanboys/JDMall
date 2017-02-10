package com.bing.lan.jdmall.ui.category;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.utils.ToastUtil;
import com.bing.lan.comm.view.LoadPageView;
import com.bing.lan.jdmall.R;
import com.bing.lan.jdmall.bean.CategoryResultBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *
 */
public class CategoryFragment
        extends BaseFragment<ICategoryContract.ICategoryPresenter>
        implements ICategoryContract.ICategoryView, View.OnClickListener {


    @BindView(R.id.scrollview)
    ScrollView mScrollView;
    // @BindView(R.id.framelayout)
    // FrameLayout mFrameLayout;
    @BindView(R.id.category_container)
    LinearLayout mLinearLayout;
    private List<String> titles;
    //装装ScrollView的item的TextView的数组
    private TextView[] textViewArray;
    //装ScrollView的item的数组
    private View[] views;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_category;
    }

    // @Override
    // protected void startInject(FragmentComponent fragmentComponent) {
    //     fragmentComponent.inject(this);
    // }

    @Override
    protected void readyStartPresenter() {
        setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_SUCCESS);
        // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new FragmentOne()).commit();
        mPresenter.onStart();
    }

    @Override
    protected void startInject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }



    private void initView() {


    }

    // @Override
    public void onClick(View v) {
        ToastUtil.showToast(getActivity(), titles.get((int) v.getId()));
        changeTextColor((int) v.getId());
        changeTextLocation((int) v.getId());

        Fragment fragment = null;

        switch (v.getId()) {
            case 0:
                fragment = new FragmentOne();
                break;
            case 1:
                fragment = new Fragment1();
                break;
            case 2:
                fragment = new Fragment2();
                break;
            case 3:
                fragment = new Fragment3();
                break;
            case 4:
                fragment = new Fragment4();
                break;
            case 5:
                fragment = new Fragment5();
                break;
            case 6:
                fragment = new Fragment6();
                break;
            case 7:
                fragment = new Fragment7();
                break;
            default:
                fragment = new Fragment7();
                break;
        }
        // if (fragment != null) {
        //     getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragment).commit();
        // }
    }

    /**
     * 改变textView的颜色
     *
     * @param id
     */
    private void changeTextColor(int id) {
        for (int i = 0; i < textViewArray.length; i++) {
            if (i != id) {
                textViewArray[i].setBackgroundResource(android.R.color.transparent);
                textViewArray[i].setTextColor(0xff000000);
            } else {
                textViewArray[id].setBackgroundResource(android.R.color.white);
                textViewArray[id].setTextColor(0xffff5d5e);
            }
        }
    }

    /**
     * 改变栏目位置
     */
    private void changeTextLocation(int index) {

        //views[clickPosition].getTop()针对其父视图的顶部相对位置
        int x = (views[index].getTop() - mScrollView.getHeight() / 2);
        mScrollView.smoothScrollTo(0, x);
    }

    // @Override
    public void updateSlideMenu(List<CategoryResultBean.TopCategoryInfoBean> list) {
        titles = new ArrayList<>();
        for (CategoryResultBean.TopCategoryInfoBean topCategoryInfoBean : list) {
            titles.add(topCategoryInfoBean.getName());
        }
        log.d("updateSlideMenu(): " + titles.toString());
        textViewArray = new TextView[list.size()];
        views = new View[list.size()];
        addView();
        changeTextColor(0);
    }

    /**
     * 给ScrollView添加子View
     */
    private void addView() {
        View view;
        for (int x = 0; x < titles.size(); x++) {
            view = View.inflate(getActivity(), R.layout.item_scrollview, null);
            view.setId(x);
            view.setOnClickListener(this);
            TextView tv = (TextView) view.findViewById(R.id.textview);
            tv.setText(titles.get(x));
            mLinearLayout.addView(view);

            textViewArray[x] = tv;
            views[x] = view;
        }
    }
}
