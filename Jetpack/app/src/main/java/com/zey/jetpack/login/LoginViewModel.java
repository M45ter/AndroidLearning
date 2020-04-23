package com.zey.jetpack.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.zey.jetpack.data.bean.LoginReq;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginReq> loginReq;

    public MutableLiveData<LoginReq> getLoginReq() {
        if (loginReq == null) {
            loginReq = new MutableLiveData<>();
            loginReq.setValue(new LoginReq("zey", "123456"));
        }
        return loginReq;
    }
}
