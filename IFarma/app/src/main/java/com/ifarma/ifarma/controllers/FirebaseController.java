package com.ifarma.ifarma.controllers;

import android.util.Log;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ifarma.ifarma.exceptions.*;
import com.ifarma.ifarma.model.*;
import com.google.firebase.*;
import com.ifarma.ifarma.util.*;


/**
 * Created by Mafra on 17/02/2017.
 */

public class FirebaseController {

    private final static String CUSTOMERS = "customers";
    private final static String PHARMACIES = "pharmacies";
    private final static String PRODUCTS = "products";
    public static final String FIREBASE_URL = "https://ifarma-5d2e6.firebaseio.com/";

    private static Firebase firebase;

    public static Firebase getFirebase(){
        if( firebase == null ){
            firebase = new Firebase(FIREBASE_URL);
        }
        return firebase;
    }

    public static void saveCustomer(String name, String email, String password, String address, String houseNumber, String cep,
        String cpf) throws InvalidUserDataException {

        System.out.println("SAVING CUSTOMER");

        Firebase firebaseRef = getFirebase();
        Firebase customersReference = firebaseRef.child(CUSTOMERS);

        Customer customer = new Customer();
        customer.setName(name);
        customer.setCpf(cpf);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setAddress(address);
        customer.setHouseNumber(houseNumber);
        customer.setCep(cep);

        String customerNode = Utils.convertEmail(customer.getEmail());

        customersReference.child(customerNode).setValue(customer);
    }

    public static void savePharmacy(String name, String email, String password, String address, String houseNumber, String cep,
                                    String cnpj) throws InvalidUserDataException {

        Firebase firebaseRef = getFirebase();
        Firebase pharmarciesReference = firebaseRef.child(PHARMACIES);

        Pharma pharma = new Pharma();
        pharma.setCep(cep);
        pharma.setHouseNumber(houseNumber);
        pharma.setAddress(address);
        pharma.setName(name);
        pharma.setPassword(password);
        pharma.setEmail(email);
        pharma.setCnpj(cnpj);

        String emailNode = Utils.convertEmail(pharma.getEmail());

        pharmarciesReference.child(emailNode).setValue(pharma);

    }

    public static void saveProduct(String name, double price, String lab, String description, boolean generic) {

        Firebase firebaseRef = getFirebase();
        Firebase productsReference = firebaseRef.child(PRODUCTS);

        System.out.println("SAVING PRODUCT");

        Product product = new Product();
        try {
            product.setNameProduct(name);
            product.setPrice(price);
            product.setLab(lab);
            product.setDescription(description);
            product.setGeneric(generic);
        } catch (InvalidProductDataException e) {
            e.printStackTrace();
        }
        productsReference.child(product.getNameProduct()).setValue(product);

    }





}
