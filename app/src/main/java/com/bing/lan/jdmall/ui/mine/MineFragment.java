package com.bing.lan.jdmall.ui.mine;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bing.lan.comm.base.mvp.fragment.BaseFragment;
import com.bing.lan.comm.di.FragmentComponent;
import com.bing.lan.jdmall.R;
import com.bing.lan.jdmall.bean.LoginResultBean;
import com.bing.lan.jdmall.ui.mine.IMineContract.IMineView;

import butterknife.BindView;
import butterknife.OnClick;


/**
 *
 */
public class MineFragment extends BaseFragment<IMineContract.IMinePresenter>
        implements IMineView {
    @BindView(R.id.user_icon_iv)
    ImageView mUserIconIv;
    @BindView(R.id.user_name_tv)
    TextView mUserNameTv;
    @BindView(R.id.user_level_tv)
    TextView mUserLevelTv;
    @BindView(R.id.wait_pay_tv)
    TextView mWaitPayTv;
    @BindView(R.id.wait_pay_ll)
    LinearLayout mWaitPayLl;
    @BindView(R.id.wait_receive_tv)
    TextView mWaitReceiveTv;
    @BindView(R.id.wait_receive_ll)
    LinearLayout mWaitReceiveLl;
    @BindView(R.id.mime_order)
    LinearLayout mMimeOrder;
    @BindView(R.id.logout_btn)
    Button mLogoutBtn;

    @Override
    protected int getLayoutResId() {
        getActivity();
        return R.layout.fragment_mine;
    }

    @Override
    protected void startInject(FragmentComponent fragmentComponent) {
        fragmentComponent.inject(this);
    }

    @Override
    protected void readyStartPresenter() {
        mPresenter.onStart();
    }


    @Override
    public void updateViewData(LoginResultBean.UserInfoBean userInfo) {

        mUserNameTv.setText(userInfo.getUserName());
        mUserLevelTv.setText(userInfo.getUserLevelStr());
        mWaitPayTv.setText(userInfo.getWaitPayCount());
        mWaitReceiveTv.setText(userInfo.getWaitReceiveCount());

    }

    @Override
    public void logout() {
        mPresenter.logout();
    }


    @OnClick({R.id.user_icon_iv, R.id.user_name_tv,
            R.id.user_level_tv, R.id.wait_pay_tv,
            R.id.wait_pay_ll, R.id.wait_receive_tv,
            R.id.logout_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_icon_iv:
                break;
            case R.id.user_name_tv:
                break;
            case R.id.user_level_tv:
                break;
            case R.id.wait_pay_tv:
                break;
            case R.id.wait_pay_ll:
                break;
            case R.id.wait_receive_tv:
                break;
            case R.id.logout_btn:
                logout();

                break;
        }
    }
}
