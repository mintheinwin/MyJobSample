package com.myjob.mintheinwin.ViewHolder;

import android.view.View;
import android.widget.TextView;

import com.myjob.mintheinwin.R;
import com.myjob.mintheinwin.mvp.data.JobDataResponse;
import com.myjob.mintheinwin.mvp.view.JobItemDeligate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JobViewHolder extends BaseViewHolder<JobDataResponse>{


    @BindView(R.id.tv_job_no)
    TextView tv_job_no;
    @BindView(R.id.tv_company_name)
    TextView tv_company_name;
    @BindView(R.id.tv_address)
    TextView tv_address;

    private JobItemDeligate mJobItemDeligate;
    private JobDataResponse mJobDataResponse;

    public JobViewHolder(View itemView,JobItemDeligate jobItemDeligate) {
        super(itemView);
        this.mJobItemDeligate=jobItemDeligate;
        itemView.setOnClickListener(this);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(JobDataResponse mData) {
        mJobDataResponse=mData;
        tv_job_no.setText(String.valueOf(mData.getJobId()));
        tv_company_name.setText(mData.getCompanyName());
        tv_address.setText(mData.getAddress());
    }

    @Override
    public void onClick(View v) {
       mJobItemDeligate.onJobTapItem(mJobDataResponse);
    }
}
