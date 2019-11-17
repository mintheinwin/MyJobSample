package com.myjob.mintheinwin.network;

import com.myjob.mintheinwin.mvp.data.JobDataResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JobAPI {

    @GET("/bins/8d195.json")
    Call<List<JobDataResponse>> getJobDataResponse();


}
