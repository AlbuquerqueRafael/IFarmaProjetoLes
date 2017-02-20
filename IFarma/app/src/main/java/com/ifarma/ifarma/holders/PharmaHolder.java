package com.ifarma.ifarma.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ifarma.ifarma.R;

/**
 * Created by Rafael on 2/19/2017.
 */

public class PharmaHolder extends RecyclerView.ViewHolder{
    public ImageView pharmaLogo;
    public TextView pharmaName;

    public PharmaHolder(View itemView) {
        super(itemView);
        pharmaLogo = (ImageView) itemView.findViewById(R.id.pharmaLogo);
        pharmaName = (TextView) itemView.findViewById(R.id.pharmaName);

    }
}
