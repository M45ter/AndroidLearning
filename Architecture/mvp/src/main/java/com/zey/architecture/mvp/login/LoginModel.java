package com.zey.architecture.mvp.login;

import com.zey.architecture.mvp.base.BaseModel;

public class LoginModel extends BaseModel<LoginPresenter> implements ILoginContract.M {
    public LoginModel(LoginPresenter presenter) {
        super(presenter);
    }

    @Override
    public void requestLogin(String name, String pwd) throws Exception {
        //请求服务器接口，返回数据
        if ("abc".equals(name) && "123".equals(pwd)) {
            mPresenter.responseLoginResult(true);
        } else {
            mPresenter.responseLoginResult(false);
        }
    }
}
