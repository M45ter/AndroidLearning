package com.zey.architecture.mvp.base;

/**
 * Model层处理数据
 * 持有Presenter，把数据回传给Presenter
 * @param <P>
 */
public class BaseModel<P extends BasePresenter> {

    public P mPresenter;

    public BaseModel(P presenter) {
        this.mPresenter = presenter;
    }
}
