package com.zey.architecture.mvp.base;

public class BaseModel<P extends BasePresenter> {

    public P mPresenter;

    public BaseModel(P presenter) {
        this.mPresenter = presenter;
    }
}
