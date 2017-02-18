package com.ifarma.ifarma.controllers;

import com.firebase.client.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ifarma.ifarma.exceptions.InvalidNameException;
import com.ifarma.ifarma.exceptions.InvalidUserDataException;
import com.ifarma.ifarma.model.Customer;
import com.ifarma.ifarma.model.Pharma;
import com.google.firebase.*;

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

        customersReference.child(customer.getName()).setValue(customer);
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


        pharmarciesReference.child(pharma.getName()).setValue(pharma);


    }

//    public static Customer getCustomer(String email){
//        DatabaseReference customer = customerReference.child(email);
//        final Customer[] customerRetrieved = new Customer[1];
//
//        customer.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                customerRetrieved[0] = dataSnapshot.getValue(Customer.class);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("ERROR");
//
//            }
//        });
//
//        return customerRetrieved[0];
//    }





}
