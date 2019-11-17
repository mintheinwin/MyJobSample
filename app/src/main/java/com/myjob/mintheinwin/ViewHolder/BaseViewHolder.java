package com.myjob.mintheinwin.ViewHolder;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;


public abstract class BaseViewHolder<W> extends RecyclerView.ViewHolder implements View.OnClickListener {

    private W mData;

    public BaseViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    public abstract void setData(W mData);

}
