package com.ifarma.ifarma.controllers;

import com.ifarma.ifarma.model.Product;

import java.util.List;

/**
 * Created by Mafra on 18/02/2017.
 */
public interface OnGetDataListener {

        public void onStart();
        public void onSuccess(List<Product> lista);
}
