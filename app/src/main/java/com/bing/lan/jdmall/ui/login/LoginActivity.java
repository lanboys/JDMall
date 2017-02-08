package com.bing.lan.jdmall.ui.login;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bing.lan.comm.base.mvp.activity.BaseActivity;
import com.bing.lan.comm.di.ActivityComponent;
import com.bing.lan.jdmall.R;
import com.bing.lan.jdmall.bean.LoginUserInfo;
import com.bing.lan.jdmall.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<ILoginContract.ILoginPresenter>
        implements ILoginContract.ILoginView<ILoginContract.ILoginPresenter> {

    @BindView(R.id.name_et)
    EditText mNameEt;
    @BindView(R.id.pwd_et)
    EditText mPwdEt;
    @BindView(R.id.title_v)
    TextView mTitleV;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.btn_register)
    Button mBtnRegister;
    @BindView(R.id.btn_resetPwd)
    Button mBtnResetPwd;
    private String mUsername;
    private String mPsd;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void startInject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void readyStartPresenter() {
        //启动p层逻辑
        mPresenter.onStart();
    }

    @Override
    public void showError(String msg, Throwable e) {

    }

    @OnClick({R.id.btn_login, R.id.btn_register, R.id.btn_resetPwd})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_register:
                break;
            case R.id.btn_resetPwd:
                break;
        }
    }

    private void login() {
        getUserInfo();

        if (TextUtils.isEmpty(mUsername) || TextUtils.isEmpty(mPsd)) {
            showToast("请输入正确的账号和密码");
            return;
        }
        mPresenter.login(mUsername, mPsd);
    }

    private void getUserInfo() {
        mUsername = mNameEt.getText().toString().trim();
        mPsd = mPwdEt.getText().toString().trim();
    }


    @Override
    public void goMainActivity() {
        startActivity(MainActivity.class, true);
    }

    @Override
    public void initUserInfo(LoginUserInfo loginUserInfo) {
        mNameEt.setText(loginUserInfo.name);
        mPwdEt.setText(loginUserInfo.pwd);
    }
}
