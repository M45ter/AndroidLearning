package com.zey.mvp1.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zey.mvp1.R;
import com.zey.mvp1.base.BaseActivity;


public class LoginActivity extends BaseActivity<LoginPresenter, ILoginContract.VP> {

    private EditText etName;

    private EditText etPwd;

    private Button btLogin;

    @Override
    public void initView() {
        etName = findViewById(R.id.et_name);
        etPwd = findViewById(R.id.et_pwd);
        btLogin = findViewById(R.id.bt_login);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        btLogin.setOnClickListener(this);
    }

    @Override
    public int getContentViewID() {
        return R.layout.activity_login;
    }

    @Override
    public LoginPresenter getPresenterInstance() {
        return new LoginPresenter();
    }

    @Override
    public void destroy() {

    }

    @Override
    public void onClick(View v) {
        String name = etName.getText().toString();
        String pwd = etPwd.getText().toString();
        getContract().requestLogin(name, pwd);
    }

    /*
    @Override
    public void requestLogin(String name, String pwd) {
        mPresenter.requestLogin(name, pwd);
    }

    @Override
    public void responseLoginResult(boolean loginStatusResult) {
        Toast.makeText(this, loginStatusResult ? "登录成功" : "登录失败", Toast.LENGTH_SHORT).show();
    }
     */

    @Override
    public ILoginContract.VP getContract() {
        return new ILoginContract.VP() {
            @Override
            public void requestLogin(String name, String pwd) {
                mPresenter.getContract().requestLogin(name, pwd);
            }

            @Override
            public void responseLoginResult(boolean loginStatusResult) {
                Toast.makeText(LoginActivity.this, loginStatusResult ? "登录成功" : "登录失败", Toast.LENGTH_SHORT).show();
            }
        };
    }
}
