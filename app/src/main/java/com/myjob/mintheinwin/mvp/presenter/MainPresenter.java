package com.myjob.mintheinwin.mvp.presenter;

import android.content.Context;

import com.myjob.mintheinwin.mvp.data.JobDataResponse;
import com.myjob.mintheinwin.mvp.model.MyJobDataModel;
import com.myjob.mintheinwin.mvp.view.MyJobView;
import com.myjob.mintheinwin.network.DataAgent;

import java.util.List;

public class MainPresenter extends BasePresenter<MyJobView>implements DataAgent.onJobListListener {

     private Context mContext;


    public MainPresenter(Context context){
        this.mContext=context;
    }

    @Override
    public void onCreate(MyJobView view) {
        super.onCreate(view);
    }

    public void getJobDataList(Context context){
        MyJobDataModel.getObjectInstance().getJobDataList(context,this);
    }

    @Override
    public void onJobResponseList(List<JobDataResponse> jobDataResponse) {
        mView.responseMyJobListView(jobDataResponse);
    }

    @Override
    public void onJobResponseErrorMessage(String message) {
        mView.responseMyJobErrorMessage(message);
    }



    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
