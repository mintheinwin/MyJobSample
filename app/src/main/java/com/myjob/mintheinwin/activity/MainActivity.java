package com.myjob.mintheinwin.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.myjob.mintheinwin.R;
import com.myjob.mintheinwin.adapter.MyJobAdapter;
import com.myjob.mintheinwin.mvp.data.JobDataResponse;
import com.myjob.mintheinwin.mvp.presenter.MainPresenter;
import com.myjob.mintheinwin.mvp.view.JobItemDeligate;
import com.myjob.mintheinwin.mvp.view.MyJobView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MyJobView , JobItemDeligate, View.OnClickListener {


    private MainPresenter mainPresenter;
    @BindView(R.id.job_list)
    RecyclerView mRecyclerJobListView;
    private MyJobAdapter myJobAdapter;

    @BindView(R.id.iv_logout)
    AppCompatImageView iv_logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

       // setSupportActionBar(toolbar);
        mainPresenter =new MainPresenter(getBaseContext());
        mainPresenter.onCreate(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(), RecyclerView.VERTICAL, false);
        mRecyclerJobListView.setLayoutManager(linearLayoutManager);
        mRecyclerJobListView.setItemAnimator(new DefaultItemAnimator());

        mainPresenter.getJobDataList(getBaseContext());
        myJobAdapter=new MyJobAdapter(getApplicationContext(),this);
        mRecyclerJobListView.setHasFixedSize(true);
        mRecyclerJobListView.setAdapter(myJobAdapter);

        iv_logout.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }





    @Override
    public void responseMyJobListView(List<JobDataResponse> jobDataResponse) {
        myJobAdapter.setNewData(jobDataResponse);
    }

    @Override
    public void responseMyJobErrorMessage(String message) {

    }

    @Override
    public void onJobTapItem(JobDataResponse jobDataResponse) {

     Intent intent=DetailShowMapActivity.newIntent(MainActivity.this,jobDataResponse);
     startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_logout:
            getGoogleLogout();
            break;
        }
    }
}
