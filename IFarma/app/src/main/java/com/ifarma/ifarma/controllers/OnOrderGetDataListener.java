package com.ifarma.ifarma.controllers;

import com.ifarma.ifarma.model.Order;
import com.ifarma.ifarma.model.Product;

import java.util.List;

/**
 * Created by Rafael on 3/4/2017.
 */

public interface OnOrderGetDataListener {
    public void onStart();
    public void onSuccess(List<Order> lista);
    public void onUpdated(List<Order> lista);
}
