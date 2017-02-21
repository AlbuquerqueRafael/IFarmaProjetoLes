package com.ifarma.ifarma.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ifarma.ifarma.R;

/**
 * Created by Rafael on 2/19/2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder{
    public TextView medPrice;
    public TextView medName;
    public TextView medDescription;

    public ViewHolder(View itemView) {
        super(itemView);
        medName = (TextView) itemView.findViewById(R.id.medName);
        medPrice = (TextView) itemView.findViewById(R.id.medPrice);
        medDescription = (TextView) itemView.findViewById(R.id.medDescription);

    }
}
