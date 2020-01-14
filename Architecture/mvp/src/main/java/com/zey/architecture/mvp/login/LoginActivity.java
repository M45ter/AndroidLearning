package com.zey.architecture.mvp.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zey.architecture.mvp.R;
import com.zey.architecture.mvp.base.BaseActivity;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginContract.VP {

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
        requestLogin(name, pwd);
    }

    @Override
    public void requestLogin(String name, String pwd) {
        mPresenter.requestLogin(name, pwd);
    }

    @Override
    public void responseLoginResult(boolean loginStatusResult) {
        if (loginStatusResult) {
            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
        }
    }
}
