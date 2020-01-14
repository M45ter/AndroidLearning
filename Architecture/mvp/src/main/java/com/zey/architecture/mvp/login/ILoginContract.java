package com.zey.architecture.mvp.login;

public interface ILoginContract {

    interface VP {
        void requestLogin(String name, String pwd);

        void responseLoginResult(boolean loginStatusResult);
    }

    interface M {
        void requestLogin(String name, String pwd) throws Exception;
    }
}
