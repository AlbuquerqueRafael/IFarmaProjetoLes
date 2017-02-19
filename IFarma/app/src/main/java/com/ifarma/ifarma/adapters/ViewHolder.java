package com.ifarma.ifarma.adapters;

import android.view.View;
import android.widget.TextView;

import com.ifarma.ifarma.R;

/**
 * Created by Rafael on 2/19/2017.
 */

public class ViewHolder {
    TextView medPrice;
     TextView medName;
     TextView medDescription;

    public ViewHolder(View view) {
        medName = (TextView) view.findViewById(R.id.medName);
        medPrice = (TextView) view.findViewById(R.id.medPrice);
        medDescription = (TextView) view.findViewById(R.id.medDescription);
    }
}
