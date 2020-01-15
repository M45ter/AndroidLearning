package com.zey.mvp1.base;

/**
 * Presenter层
 * 持有View和Model，作为两者的连接，所有调度操作
 * @param <M>
 * @param <V>
 */
public abstract class BasePresenter<M extends BaseModel, V extends BaseActivity, CONTRACT> extends SuperBase<CONTRACT> {

    public V mView;

    public M mModel;

    public BasePresenter() {
        this.mModel = getModelInstance();
    }

    public void bindView(V view) {
        this.mView = view;
    }

    public void unBindView() {
        this.mView = null;
    }

    public abstract M getModelInstance();
}
