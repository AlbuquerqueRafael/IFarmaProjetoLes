package com.ifarma.ifarma.controllers;

import android.util.Log;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.ifarma.ifarma.exceptions.InvalidUserDataException;
import com.ifarma.ifarma.model.Customer;
import com.ifarma.ifarma.model.Pharma;
import com.ifarma.ifarma.util.Utils;
import com.ifarma.ifarma.model.Product;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        pharma.initProducts();

        String emailNode = Utils.convertEmail(pharma.getEmail());

        pharmarciesReference.child(emailNode).setValue(pharma);

    }

    public static void editPharmacy(Pharma pharma) {

        Firebase firebaseRef = getFirebase();
        Firebase pharmarciesReference = firebaseRef.child(PHARMACIES);

        String emailNode = Utils.convertEmail(pharma.getEmail());

        pharmarciesReference.child(emailNode).setValue(pharma);

    }


    public static void retrievePharmacies(final OnPharmaGetDataListener listener){
        final Firebase pharmasReference = getFirebase().child(PHARMACIES);
        final List<Pharma> lista = new ArrayList<>();

        pharmasReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                Pharma pharma = dataSnapshot.getValue(Pharma.class);
                lista.add(pharma);

                listener.onSuccess(lista);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                System.out.println("FILHO MODIFICADO!");

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                System.out.println("onChildRemoved");
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
                System.out.println("onChildMoved");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("onCancelled");
            }


        });
    }


    public static void retrieveProducts( final OnMedGetDataListener listener) {
        final Firebase productsReference = getFirebase().child(PHARMACIES);
        final List<Product> lista = new ArrayList<>();

        productsReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                DataSnapshot productSnapshot = dataSnapshot.child(PRODUCTS);
                Iterable<DataSnapshot> productChildren = productSnapshot.getChildren();

                for (DataSnapshot prod : productChildren){
                    Product product = prod.getValue(Product.class);
                    lista.add(product);
                }

                listener.onSuccess(lista);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                System.out.println("FILHO MODIFICADO!");

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                System.out.println("onChildRemoved");
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
                System.out.println("onChildMoved");
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("onCancelled");
            }


        });
    }


    private void collectPhoneNumbers(Map<String,Object> users) {

        ArrayList<Long> phoneNumbers = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()){

            System.out.println(entry.toString());
        }

        System.out.println(phoneNumbers.toString());
    }

    public static void newProduct(String pharmacyId, Product product){

        Firebase firebaseRef = getFirebase();
        Firebase productsPharmacyReference = firebaseRef.child(PHARMACIES).child(pharmacyId).child(PRODUCTS);
        Firebase newProduct = productsPharmacyReference.push();

        newProduct.setValue(product);
    }

    public static void saveProduct(String name, double price, String lab, String description, boolean generic){

        Firebase firebaseRef = getFirebase();
        Firebase productsReference = firebaseRef.child(PRODUCTS);

        System.out.println("SAVING PRODUCT");

        Product product = new Product();
        product.setNameProduct(name);
        product.setPrice(price);
        product.setLab(lab);
        product.setDescription(description);
        product.setGeneric(generic);

        productsReference.child(product.getNameProduct()).setValue(product);

    }





}
