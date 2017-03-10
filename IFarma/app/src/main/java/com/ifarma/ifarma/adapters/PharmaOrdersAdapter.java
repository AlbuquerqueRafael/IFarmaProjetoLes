package com.ifarma.ifarma.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
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

import com.google.firebase.iid.FirebaseInstanceId;
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

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
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
    private String email = "";

    public PharmaOrdersAdapter(Context context, ArrayList<Order> orderArrayList, OrderStatus order, String email) {

        this.email = email;
        this.orderOrignalValues = orderArrayList;
        this.orderDisplayedValues = orderArrayList;
        this.context = context;
        this.orderStatus = order;

        if(context != null){
            inflater = LayoutInflater.from(context);
        }
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
                                    sendNotification(order.getUserToken(), order.getPharmacyName(), "O seu pedido foi aceito!");
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

        FirebaseController.retrievePharmaOrders(email, new OnOrderGetDataListener() {

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
                sendNotification(order.getUserToken(), order.getPharmacyName() + "-Negou Pedido", order.getComment());
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

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
    private void sendNotification(final String reg_token,  final String title, final String body) {
        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    OkHttpClient client = new OkHttpClient();
                    JSONObject json=new JSONObject();
                    JSONObject dataJson=new JSONObject();
                    dataJson.put("body",body);
                    dataJson.put("title",title);
                    json.put("notification",dataJson);
                    json.put("to", reg_token);
                    RequestBody body = RequestBody.create(JSON, json.toString());
                    Request request = new Request.Builder()
                            .header("Authorization","key="+
                                    "AAAA9VfseiU:APA91bHT5sLApI9dk5vXACzRCyNP7IiaT_8dyJzgKVdN1tjkdfBMslbvOEm02I0bJDLwIuxcc2mepHDP1D7mO9DYvWD3Ck3lDrDy85pZ3vXKMaTevvMmeC4gmSslRucpBfgdXaO1AhTd")
                            .url("https://fcm.googleapis.com/fcm/send")
                            .post(body)
                            .build();
                    Response response = client.newCall(request).execute();
                    String finalResponse = response.body().string();
                }catch (Exception e){
                    System.out.println("error");
                    //Log.d(TAG,e+"");
                }
                return null;
            }
        }.execute();

    }
}
