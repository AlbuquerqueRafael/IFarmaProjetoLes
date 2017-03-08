package com.ifarma.ifarma.fragments.pharmacy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ifarma.ifarma.R;
import com.ifarma.ifarma.adapters.PharmaOrdersAdapter;
import com.ifarma.ifarma.controllers.FirebaseController;
import com.ifarma.ifarma.controllers.OnOrderGetDataListener;
import com.ifarma.ifarma.decoration.DividerItemDecoration;
import com.ifarma.ifarma.model.Order;
import com.ifarma.ifarma.model.OrderStatus;

import java.util.ArrayList;
import java.util.List;

public class DemandFragment extends Fragment {

    private View rootView;
    private RadioGroup _radioGroup;
    private ArrayList<Order> listOrderItens;
    private OrderStatus orderStatus = OrderStatus.WAITING_ORDER;
    private PharmaOrdersAdapter ordersAdapter;
    private RecyclerView _listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_demand, container, false);
        _radioGroup = (RadioGroup) rootView.findViewById(R.id.demandRadioGroup);
        _listView = (RecyclerView) rootView.findViewById(R.id.order_list_view);
        _listView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        RadioButton initButton = (RadioButton) rootView.findViewById(R.id.pendingButton);
        initButton.setChecked(true);

        initList();
        radioGroupListener();

        return rootView;
    }


    public void radioGroupListener(){
        _radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.pendingButton) {
                    orderStatus = OrderStatus.WAITING_ORDER;
                } else if(checkedId == R.id.acceptButton) {
                    orderStatus = OrderStatus.ACCEPTED_ORDER;

                }else{
                    orderStatus = OrderStatus.REJECTED_ORDER;
                }


               initList();
            }

        });
    }

    public void initList(){
        listOrderItens = new ArrayList<Order>();

        FirebaseController.retrievePharmaOrders(new OnOrderGetDataListener() {

            @Override
            public void onStart() {
            }


            @Override
            public void onSuccess(List<Order> lista) {
                listOrderItens = new ArrayList<Order>();

                for (Order o : lista){{
                    listOrderItens.add(o);
                }}

                ordersAdapter = new PharmaOrdersAdapter(getActivity(), listOrderItens, orderStatus);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                _listView.setHasFixedSize(true);
                _listView.setLayoutManager(mLayoutManager);
                _listView.setAdapter(ordersAdapter);
            }

            @Override
            public void onUpdated(List<Order> lista) {
                listOrderItens = new ArrayList<Order>();

                for (Order o : lista){{
                    listOrderItens.add(o);
                }}

                ordersAdapter = new PharmaOrdersAdapter(getActivity(), listOrderItens, orderStatus);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                _listView.setHasFixedSize(true);
                _listView.setLayoutManager(mLayoutManager);
                _listView.setAdapter(ordersAdapter);
            }

        }, orderStatus);

    }
}
