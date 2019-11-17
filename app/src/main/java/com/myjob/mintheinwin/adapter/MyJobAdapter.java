package com.myjob.mintheinwin.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.myjob.mintheinwin.R;
import com.myjob.mintheinwin.ViewHolder.JobViewHolder;
import com.myjob.mintheinwin.mvp.data.JobDataResponse;
import com.myjob.mintheinwin.mvp.view.JobItemDeligate;

public class MyJobAdapter extends BaseRecyclerAdapter<JobViewHolder, JobDataResponse> {

    private JobItemDeligate mJobItemDeligate;
    private Context mContext;

    public MyJobAdapter(Context context,JobItemDeligate jobItemDeligate) {
        super(context);
        this.mContext=context;
        this.mJobItemDeligate=jobItemDeligate;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.job_view_item, parent, false);
        return new JobViewHolder(view,mJobItemDeligate);
    }
}
