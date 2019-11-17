package com.myjob.mintheinwin.network;

import android.content.Context;

import com.myjob.mintheinwin.mvp.data.JobDataResponse;

import java.util.List;

public interface DataAgent {

    interface onJobListListener{

        void onJobResponseList(List<JobDataResponse> jobDataResponse);
        void onJobResponseErrorMessage(String message);
    }

    void getJobDataList(Context context,onJobListListener onJobListListener);
}
