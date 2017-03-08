package com.ifarma.ifarma.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Toast;

import com.ifarma.ifarma.R;
import com.ifarma.ifarma.controllers.FirebaseController;
import com.ifarma.ifarma.controllers.OnOrderGetDataListener;
import com.ifarma.ifarma.holders.OrderHolder;
import com.ifarma.ifarma.holders.ViewHolder;
import com.ifarma.ifarma.model.Order;
import com.ifarma.ifarma.model.OrderStatus;
import com.ifarma.ifarma.model.Product;
import com.ifarma.ifarma.services.AdapterService;
import com.ifarma.ifarma.util.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Rafael on 3/4/2017.
 */

public class PharmaOrdersAdapter extends RecyclerView.Adapter<OrderHolder> {
    private ArrayList<Order> ordersList;
    private ArrayList<Order> orderOrignalValues; // Original Values
    private ArrayList<Order> orderDisplayedValues;    // Values to be displayed
    private LayoutInflater inflater;
    private View view;
    private Context context;
    private OrderHolder holder;
    private boolean _isSelecting;
    private FloatingActionButton addToCart;
    private View rootView;
    private OrderStatus orderStatus;
    private final PublishSubject<Product> onClickSubject = PublishSubject.create();
    private String m_text = "";
    private boolean cancelPressed = false;
    private boolean isPharmacy = false;
    public static final String FLAG_EMAIL = "currentEmail";

    public PharmaOrdersAdapter(Context context, ArrayList<Order> orderArrayList, OrderStatus order) {

        this.orderOrignalValues = orderArrayList;
        this.orderDisplayedValues = orderArrayList;
        this.context = context;
        this.orderStatus = order;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public OrderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_list_view, parent, false);
        holder = new OrderHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final OrderHolder holder, int position) {
        holder.customerName.setText(orderDisplayedValues.get(position).getCustomerName());
        holder.orderPrice.setText("R$ " + String.format("%.2f", orderDisplayedValues.get(position).getOrderTotalPrice()));
        holder.orderAdress.setText(orderDisplayedValues.get(position).getDeliveryAddress());
        holder.customerTelephone.setText(orderDisplayedValues.get(position).getCustomerTelephone());
        holder.orderDate.setText(new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").
                format(orderDisplayedValues.get(position)
                .getDate()));

        final Order order = orderDisplayedValues.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(orderStatus == orderStatus.WAITING_ORDER){
                    new AlertDialog.Builder(context)
                            .setTitle("Confirmar o Pedido?")
                            .setMessage("Descrição: " + order.getDescription())
                            .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    order.setOrderStatus(OrderStatus.REJECTED_ORDER);
                                    cancelPressed = false;
                                    String comment = initInputDialog(order);

                                }
                            })
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    order.setOrderStatus(OrderStatus.ACCEPTED_ORDER);
                                    FirebaseController.editOrder(order);
                                    initList();
                                    dialog.dismiss();
                                }
                            })
                            .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }else if(orderStatus == OrderStatus.REJECTED_ORDER){
                    new AlertDialog.Builder(context)
                            .setTitle("Comentário:")
                            .setMessage(order.getComment())
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    order.setOrderStatus(OrderStatus.ACCEPTED_ORDER);
                                    FirebaseController.editOrder(order);
                                    initList();
                                    dialog.dismiss();
                                }
                            })
                            .show();
                }

            }
        });

    }

    public Observable<Product> getPositionClicks(){
        return onClickSubject.asObservable();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return orderDisplayedValues.size();
    }


    private void initList(){
        orderDisplayedValues = new ArrayList<Order>();

        FirebaseController.retrievePharmaOrders(new OnOrderGetDataListener() {

            @Override
            public void onStart() {
            }


            @Override
            public void onSuccess(List<Order> lista) {
                orderDisplayedValues = new ArrayList<Order>();

                for (Order o : lista){{
                    orderDisplayedValues.add(o);
                }}

                notifyDataSetChanged();

            }

            @Override
            public void onUpdated(List<Order> lista) {
            }

        }, orderStatus);



    }

    private String initInputDialog(final Order order){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Comentário: ");
        m_text = "";
        final EditText input = new EditText(context);

        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_text = input.getText().toString();
                order.setComment(m_text);
                FirebaseController.editOrder(order);
                initList();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();

        return m_text;
    }
}
