package com.bing.lan.jdmall.ui.main;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.comm.utils.AppUtil;
import com.bing.lan.jdmall.R;
import com.bing.lan.jdmall.ui.category.CategoryFragment;
import com.bing.lan.jdmall.ui.home.HomeFragment;
import com.bing.lan.jdmall.ui.mine.MineFragment;
import com.bing.lan.jdmall.ui.shopcar.ShopcarFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity<IMainContract.IMainPresenter>
        implements IMainContract.IMainView<IMainContract.IMainPresenter> {


    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabHost;
    private Class[] mFragmentClazz;
    private String[] mTabTitles;
    private int[] mTabImages;
    private LayoutInflater mInflater;
    private int[] mTabImages1;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void readyStartPresenter() {

        initTabData();
        initFragmentTabHost();
        //启动p层逻辑
        mPresenter.onStart();
    }

    private void initTabData() {
        mFragmentClazz = new Class[]{
                HomeFragment.class,
                CategoryFragment.class,
                ShopcarFragment.class,
                MineFragment.class};

        mTabTitles = AppUtil.getStrArr(R.array.main_tab_title);

        TypedArray ar = AppUtil.getAppRes().obtainTypedArray(R.array.main_tab_image);
        int len = ar.length();
        mTabImages = new int[len];
        for (int i = 0; i < len; i++)
            mTabImages[i] = ar.getResourceId(i, 0);
        ar.recycle();

        mInflater = LayoutInflater.from(this);
    }

    private void initFragmentTabHost() {
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);

        for (int i = 0; i < mFragmentClazz.length; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTabTitles[i]).setIndicator(getTabView(i));
            mTabHost.addTab(tabSpec, mFragmentClazz[i], null);
            mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
        }
    }

    private View getTabView(int index) {
        View view = mInflater.inflate(R.layout.tab_item, null);

        ImageView image = (ImageView) view.findViewById(R.id.image);
        TextView title = (TextView) view.findViewById(R.id.title);

        image.setImageResource(mTabImages[index]);
        title.setText(mTabTitles[index]);

        return view;
    }

}
