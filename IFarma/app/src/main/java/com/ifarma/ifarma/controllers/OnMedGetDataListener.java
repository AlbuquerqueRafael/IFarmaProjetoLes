package com.ifarma.ifarma.controllers;

import com.ifarma.ifarma.model.Pharma;
import com.ifarma.ifarma.model.Product;

import java.util.List;

/**
 * Created by Rafael on 2/19/2017.
 */

public interface OnMedGetDataListener {

    public void onStart();
    public void onSuccess(List<Product> lista);
}
