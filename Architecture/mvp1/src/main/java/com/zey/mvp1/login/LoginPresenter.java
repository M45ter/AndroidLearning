package com.zey.mvp1.login;

import com.zey.mvp1.base.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginModel, LoginActivity, ILoginContract.VP> {
    @Override
    public LoginModel getModelInstance() {
        return new LoginModel(this);
    }

    /*
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
     */

    @Override
    public ILoginContract.VP getContract() {
        return new ILoginContract.VP() {
            @Override
            public void requestLogin(String name, String pwd) {
                //校验
                try {
                    mModel.getContract().requestLogin(name, pwd);
                } catch (Exception e) {
                    e.printStackTrace();
                    //异常处理
                }
            }

            @Override
            public void responseLoginResult(boolean loginStatusResult) {
                //解析数据
                mView.getContract().responseLoginResult(loginStatusResult);
            }
        };
    }
}
