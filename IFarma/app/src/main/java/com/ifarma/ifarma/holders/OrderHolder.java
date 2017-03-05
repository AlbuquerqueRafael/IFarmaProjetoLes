package com.ifarma.ifarma.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ifarma.ifarma.R;

/**
 * Created by Rafael on 3/5/2017.
 */

public class OrderHolder  extends RecyclerView.ViewHolder{
    public TextView orderPrice;
    public TextView customerName;
    public TextView orderAdress;
    public TextView customerTelephone;
    public TextView orderDate;

    public OrderHolder(View itemView) {
        super(itemView);
        customerName = (TextView) itemView.findViewById(R.id.customer_name);
        orderPrice = (TextView) itemView.findViewById(R.id.order_price);
        orderAdress = (TextView) itemView.findViewById(R.id.order_adress);
        customerTelephone = (TextView) itemView.findViewById(R.id.customer_telephone);
        orderDate = (TextView) itemView.findViewById(R.id.order_date);
    }
}
