package com.zey.architecture.mvp.login;

import com.zey.architecture.mvp.base.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginModel, LoginActivity> implements ILoginContract.VP {
    @Override
    public LoginModel getModelInstance() {
        return new LoginModel(this);
    }


    @Override
    public void requestLogin(String name, String pwd) {
        //校验
        try {
            mModel.requestLogin(name, pwd);
        } catch (Exception e) {
            e.printStackTrace();
            //异常处理
        }
    }

    @Override
    public void responseLoginResult(boolean loginStatusResult) {
        //解析数据
        mView.responseLoginResult(loginStatusResult);
    }
}
