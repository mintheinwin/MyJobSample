package com.myjob.mintheinwin.mvp.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class JobDataResponse implements Serializable {

    @SerializedName("id")
    private int Id;
    @SerializedName("job-id")
    private int jobId;
    @SerializedName("priority")
    private int priority;
    @SerializedName("company")
    private String companyName;
    @SerializedName("address")
    private String address;
    @SerializedName("geolocation")
    private Geolocation geolocation;


    public JobDataResponse(){

    }

    public JobDataResponse(int id,int jobId,int priority,String companyName,String address,Geolocation geolocation){
        this.Id=id;
        this.jobId=jobId;
        this.priority=priority;
        this.companyName=companyName;
        this.address=address;
        this.geolocation=getGeolocation();
    }

    public int getId() {
        return Id;
    }

    public int getJobId() {
        return jobId;
    }

    public int getPriority() {
        return priority;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getAddress() {
        return address;
    }

    public Geolocation getGeolocation() {
        if (geolocation==null){

            geolocation=new Geolocation();
        }
        return geolocation;
    }



   /* @Override
    public void writeToParcel(Parcel dest, int flags) {
      dest.writeInt(Id);
      dest.writeInt(jobId);
      dest.writeInt(priority);
      dest.writeString(companyName);
      dest.writeString(address);

      dest.writeParcelable(geolocation,flags);
    }*/
}
