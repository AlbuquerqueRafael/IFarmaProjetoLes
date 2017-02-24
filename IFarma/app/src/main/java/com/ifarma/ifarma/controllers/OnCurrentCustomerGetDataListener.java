package com.ifarma.ifarma.controllers;

import com.ifarma.ifarma.model.Customer;

/**
 * Created by Mafra on 18/02/2017.
 */
public interface OnCurrentCustomerGetDataListener {

    public void onStart();
    public void onSuccess(Customer currentCustomer);
}