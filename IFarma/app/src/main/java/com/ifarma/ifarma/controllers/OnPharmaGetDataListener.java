package com.ifarma.ifarma.controllers;

import com.ifarma.ifarma.model.Pharma;
import com.ifarma.ifarma.model.Product;

import java.util.List;

/**
 * Created by Mafra on 18/02/2017.
 */
public interface OnPharmaGetDataListener {

        public void onStart();
        public void onSuccess(List<Pharma> lista);
}
