package com.myjob.mintheinwin.mvp.presenter;

public abstract class BasePresenter<T> {

    protected T mView;

    public void onCreate(T view) {
        mView = view;
    }

    public abstract void onStart();

    public void onResume() {

    }

    public void onPause() {

    }

    public abstract void onStop();

    public void onDestroy() {

    }


}
