package com.myjob.mintheinwin.mvp.data;

import android.os.Binder;

public class ObjectWrapperForBinder extends Binder {

    private final JobDataResponse mData;

    public ObjectWrapperForBinder(JobDataResponse data) {
        mData = data;
    }

    public JobDataResponse getData() {
        return mData;
    }
}