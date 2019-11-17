package com.myjob.mintheinwin.mvp.view;

import com.myjob.mintheinwin.mvp.data.JobDataResponse;

import java.util.List;

public interface MyJobView {

    void responseMyJobListView(List<JobDataResponse> jobDataResponse);
    void responseMyJobErrorMessage(String message);
}
