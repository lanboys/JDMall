package com.bing.lan.jdmall.ui.category;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.comm.utils.ToastUtil;
import com.bing.lan.comm.view.LoadPageView;
import com.bing.lan.jdmall.R;

import butterknife.BindView;

/**
 *
 */
public class CategoryFragment extends BaseFragment<ICategoryContract.ICategoryPresenter>
        implements ICategoryContract.ICategoryView, View.OnClickListener {

    Context context;
    @BindView(R.id.scrollview)
    ScrollView mScrollView;
    @BindView(R.id.framelayout)
    FrameLayout mFrameLayout;
    @BindView(R.id.category_container)
    LinearLayout mLinearLayout;
    private String[] titles = {"常用分类", "潮流女装", "品牌男装", "内衣配饰", "家用电器", "手机数码", "电脑办公", "个护化妆", "母婴频道", "食物生鲜", "酒水饮料", "家居家纺", "整车车品", "鞋靴箱包", "运动户外", "图书", "玩具乐器", "钟表", "居家生活", "珠宝饰品", "音像制品", "家具建材", "计生情趣", "营养保健", "奢侈礼品", "生活服务", "旅游出行"};
    //装装ScrollView的item的TextView的数组
    private TextView[] textViewArray;
    //装ScrollView的item的数组
    private View[] views;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_category;
    }

    @Override
    protected void startInject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void readyStartPresenter() {

        setViewState2LoadPage(LoadPageView.LoadDataResult.LOAD_SUCCESS);
        textViewArray = new TextView[titles.length];
        views = new View[titles.length];

        initView();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new FragmentOne()).commit();
    }

    private void initView() {
        addView();
        changeTextColor(0);
    }

    /**
     * 给ScrollView添加子View
     */
    private void addView() {
        View view;
        for (int x = 0; x < titles.length; x++) {
            view = View.inflate(getActivity(), R.layout.item_scrollview, null);
            view.setId(x);
            view.setOnClickListener(this);
            TextView tv = (TextView) view.findViewById(R.id.textview);
            tv.setText(titles[x]);
            mLinearLayout.addView(view);

            textViewArray[x] = tv;
            views[x] = view;
        }
    }

    @Override
    public void onClick(View v) {
        ToastUtil.showToast(getActivity(), titles[(int) v.getId()]);
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
        }
        if (fragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, fragment).commit();
        }
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
}
