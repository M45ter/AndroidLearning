package com.zey.mvp1.base;

/**
 * Model层处理数据
 * 持有Presenter，把数据回传给Presenter
 * @param <P>
 */
public abstract class BaseModel<P extends BasePresenter, CONTRACT> extends SuperBase<CONTRACT> {

    public P mPresenter;

    public BaseModel(P presenter) {
        this.mPresenter = presenter;
    }
}
