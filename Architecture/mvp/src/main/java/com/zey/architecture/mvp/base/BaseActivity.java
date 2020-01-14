package com.zey.architecture.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements View.OnClickListener {

    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewID());

        initView();
        initData();
        initListener();

        mPresenter = getPresenterInstance();
        mPresenter.bindView(this);
    }

    public abstract void initView();

    public abstract void initData();

    public abstract void initListener();

    public abstract int getContentViewID();

    public abstract P getPresenterInstance();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.unBindView();
        destroy();
    }

    public abstract void destroy();
}
