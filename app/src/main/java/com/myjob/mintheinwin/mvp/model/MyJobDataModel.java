package com.myjob.mintheinwin.mvp.model;

import android.content.Context;

import com.google.gson.Gson;
import com.myjob.mintheinwin.mvp.data.JobDataResponse;
import com.myjob.mintheinwin.network.APIConstants;
import com.myjob.mintheinwin.network.DataAgent;
import com.myjob.mintheinwin.network.JobAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyJobDataModel implements DataAgent {

    private static MyJobDataModel objectInstance;
    private JobAPI jobAPI;
    private static List<JobDataResponse> jobDataResponseList;

    private MyJobDataModel() {
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();
        // time 60 sec is optimal.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .build();

        jobAPI = retrofit.create(JobAPI.class);
    }

    public static MyJobDataModel getObjectInstance() {
        if (objectInstance == null) {
            objectInstance = new MyJobDataModel();
        }
        return objectInstance;
    }


    @Override
    public void getJobDataList(Context context, final onJobListListener onJobListListener) {
        jobDataResponseList=new ArrayList<>();

        Call<List<JobDataResponse>> getJobDataResponse=jobAPI.getJobDataResponse();
        getJobDataResponse.enqueue(new Callback<List<JobDataResponse>>() {
            @Override
            public void onResponse(Call<List<JobDataResponse>> call, Response<List<JobDataResponse>> response) {
                if (response.body()!=null){
                    jobDataResponseList=response.body();
                    onJobListListener.onJobResponseList(jobDataResponseList);
                }
            }

            @Override
            public void onFailure(Call<List<JobDataResponse>> call, Throwable t) {
                onJobListListener.onJobResponseErrorMessage(t.getMessage());
            }
        });
    }
}
