package com.zey.mvp1.login;

import com.zey.mvp1.base.BaseModel;

public class LoginModel extends BaseModel<LoginPresenter, ILoginContract.M> {
    public LoginModel(LoginPresenter presenter) {
        super(presenter);
    }

    /*
    @Override
    public void requestLogin(String name, String pwd) throws Exception {
        //请求服务器接口，返回数据
        if ("abc".equals(name) && "123".equals(pwd)) {
            mPresenter.responseLoginResult(true);
        } else {
            mPresenter.responseLoginResult(false);
        }
    }
     */

    @Override
    public ILoginContract.M getContract() {
        return new ILoginContract.M() {
            @Override
            public void requestLogin(String name, String pwd) throws Exception {
                //请求服务器接口，返回数据
                if ("abc".equals(name) && "123".equals(pwd)) {
                    mPresenter.getContract().responseLoginResult(true);
                } else {
                    mPresenter.getContract().responseLoginResult(false);
                }
            }
        };
    }
}
