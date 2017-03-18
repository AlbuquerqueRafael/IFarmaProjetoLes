package com.ifarma.ifarma.controllers;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.iid.FirebaseInstanceId;
import com.ifarma.ifarma.exceptions.InvalidProductDataException;
import com.ifarma.ifarma.exceptions.InvalidUserDataException;
import com.ifarma.ifarma.model.Customer;
import com.ifarma.ifarma.model.Order;
import com.ifarma.ifarma.model.OrderStatus;
import com.ifarma.ifarma.model.Pharma;
import com.ifarma.ifarma.util.Utils;
import com.ifarma.ifarma.model.Product;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Mafra on 17/02/2017.
 */

public class FirebaseController {

    private final static String CUSTOMERS = "customers";
    private final static String PHARMACIES = "pharmacies";
    private final static String PRODUCTS = "products";
    private final static String ORDERS = "orders";
    public static final String FIREBASE_URL = "https://ifarma-5d2e6.firebaseio.com/";
    private static SecureRandom random = new SecureRandom();

    private static Firebase firebase;

    public static Firebase getFirebase(){
        if( firebase == null ){
            firebase = new Firebase(FIREBASE_URL);
        }
        return firebase;
    }

    public static void sendOrder(String pharmacyId, Order order){
        Firebase firebaseRef = getFirebase();
        Firebase demandsReference = firebaseRef.child(PHARMACIES).child(pharmacyId).child(ORDERS);

        Firebase newDemand = demandsReference.push();

        newDemand.setValue(order);

    }

    public static void saveCustomer(String email){

        Utils.showSavingCostumerMsg();

        Firebase firebaseRef = getFirebase();
        Firebase customersReference = firebaseRef.child(CUSTOMERS);

        Customer customer = new Customer(email);

        String customerNode = Utils.convertEmail(customer.getEmail());

        customersReference.child(customerNode).setValue(customer);
    }

    public static void editCustomer(Customer customer) {

        Firebase firebaseRef = getFirebase();
        Firebase customersReference = firebaseRef.child(CUSTOMERS);

        String emailNode = Utils.convertEmail(customer.getEmail());

        customersReference.child(emailNode).setValue(customer);

    }

    public static void savePharmacy(String email) {

        Firebase firebaseRef = getFirebase();
        Firebase pharmarciesReference = firebaseRef.child(PHARMACIES);

        Pharma pharma = new Pharma(email);

        String emailNode = Utils.convertEmail(pharma.getEmail());

        pharmarciesReference.child(emailNode).setValue(pharma);

    }

    public static void editPharmacy(Pharma pharma) {

        Firebase firebaseRef = getFirebase();
        Firebase pharmarciesReference = firebaseRef.child(PHARMACIES);

        String emailNode = Utils.convertEmail(pharma.getEmail());

        pharmarciesReference.child(emailNode).setValue(pharma);

    }

    public static void editProduct(String pharmacyId, Product product, String lastName) {

        Firebase firebaseRef = getFirebase();
        Firebase productsReference = firebaseRef.child(PHARMACIES).child(Utils.convertEmail(pharmacyId)).child(PRODUCTS);

        if (!product.getNameProduct().equals(lastName)){
            productsReference.child(lastName).removeValue();
        }
        productsReference.child(product.getNameProduct()).setValue(product);
    }

    public static void retrievePharmacies(final OnPharmaGetDataListener listener){
        final Firebase pharmasReference = getFirebase().child(PHARMACIES);
        final List<Pharma> lista = new ArrayList<>();

        pharmasReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {

                String name = dataSnapshot.child("name").getValue().toString();
                String emptyName = "";

                Pharma pharma;

                if (emptyName.equals(name)) {
                    pharma = new Pharma(dataSnapshot.child("email").getValue().toString());
                } else {
                    pharma = dataSnapshot.getValue(Pharma.class);
                }

                lista.add(pharma);

                listener.onSuccess(lista);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                Utils.showChildModifiedMsg();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Utils.showChildRemovedMsg();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
                Utils.showChildMovedMsg();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Utils.showOnCancelledMsg();
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
                Utils.showChildModifiedMsg();

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Utils.showChildRemovedMsg();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
                Utils.showChildMovedMsg();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Utils.showOnCancelledMsg();
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
        Firebase newProduct = productsPharmacyReference.child(product.getNameProduct());

        newProduct.setValue(product);
    }

    public static void saveProduct(String name, double price, String lab, String description, boolean generic){

        Firebase firebaseRef = getFirebase();
        Firebase productsReference = firebaseRef.child(PRODUCTS);

        Utils.showSavingProductMsg();

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

    public static void retrieveCurrentPharma(String pharmaID, final OnCurrentPharmaGetDataListener listener) {
        final Firebase pharmasReference = getFirebase().child(PHARMACIES).child(pharmaID);

        pharmasReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child("name").getValue().toString();
                String emptyName = "";

                Pharma pharma;

                if (emptyName.equals(name)) {
                    pharma = new Pharma(dataSnapshot.child("email").getValue().toString());
                } else {
                    pharma = dataSnapshot.getValue(Pharma.class);
                }

                listener.onSuccess(pharma);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    public static void retrieveCurrentCustomer(String customerID, final OnCurrentCustomerGetDataListener listener) {
        final Firebase pharmasReference = getFirebase().child(CUSTOMERS).child(customerID);

        pharmasReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String name = dataSnapshot.child("name").getValue().toString();
                String emptyName = "";

                Customer customer;

                if (emptyName.equals(name)) {
                    customer = new Customer(dataSnapshot.child("email").getValue().toString());
                } else {
                    customer = dataSnapshot.getValue(Customer.class);
                }

                listener.onSuccess(customer);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }

    public static void removeProduct(String pharmacyId, String productID){
        Firebase firebaseRef = getFirebase();
        Firebase productsPharmacyReference = firebaseRef.child(PHARMACIES).child(pharmacyId).child(PRODUCTS);
        productsPharmacyReference.child(productID).removeValue();
    }

    public static void saveOrder(final String pharmacyId, final String customerTelephone,
                             final String comment, final double price, final String custommerName,final String description,
                             final String adressCustomer, final OrderStatus orderStatus, final String userToken){
        Firebase firebaseRef = getFirebase();

        Firebase pharmaReference = firebaseRef.child(PHARMACIES).child(Utils.convertEmail(pharmacyId));
        pharmaReference.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if(dataSnapshot.getKey().equals("name")){
                    String name = dataSnapshot.getValue(String.class);
                    auxSaveOrder(pharmacyId, customerTelephone,
                          comment, price, custommerName, description,
                          adressCustomer, orderStatus, userToken, name);
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private static void auxSaveOrder(String pharmacyId, String customerTelephone,
                                String comment, double price, String custommerName, String description,
                                String adressCustomer, OrderStatus orderStatus, String userToken, String name){

        pharmacyId = Utils.convertEmail(pharmacyId);
        Firebase firebaseRef = getFirebase();
        Firebase productsPharmacyReference = firebaseRef.child(PHARMACIES).child(pharmacyId).child(ORDERS);
        String id = nextSessionId();
        Firebase newOrder = productsPharmacyReference.child(id);
        System.out.println(name);
        System.out.println(name);
        System.out.println(name);
        System.out.println(name);
        Utils.showSavingProductMsg();

        Order order = new Order();
        try {
            order.setComment(comment);
            order.setPharmacyId(pharmacyId);
            order.setCustomerName(custommerName);
            order.setOrderTotalPrice(price);
            order.setCustomerTelephone(customerTelephone);
            order.setDate(new Date());
            order.setDescription(description);
            order.setOrderStatus(orderStatus);
            order.setDeliveryAddress(adressCustomer);
            order.setId(id);
            order.setPharmacyName(name);
            order.setUserToken(userToken);
        } catch (Exception e) {
            e.printStackTrace();
        }

        newOrder.setValue(order);

    }


    public static void editOrder(Order order) {
        Firebase firebaseRef = getFirebase();
        Firebase ordersPharmacyReference = firebaseRef.child(PHARMACIES).child(order.getPharmacyId()).child(ORDERS);
        ordersPharmacyReference.child(order.getId()).setValue(order);


    }

    public static void retrievePharmaOrders(final String email, final OnOrderGetDataListener listener, final OrderStatus orderStatus) {
        final Firebase productsReference = getFirebase().child(PHARMACIES).child(Utils.convertEmail(email));
        final List<Order> lista = new ArrayList<>();

        productsReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                if(dataSnapshot.getKey().equals("orders")){
                    Iterable<DataSnapshot> orderChildren = dataSnapshot.getChildren();

                    for (DataSnapshot ord : orderChildren){
                        System.out.println(ord);
                        Order order = ord.getValue(Order.class);

                        if(orderStatus == order.getOrderStatus()){
                            lista.add(order);
                        }

                    }

                    listener.onSuccess(lista);
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                if(dataSnapshot.getKey().equals("orders")){
                    List<Order> list = new ArrayList<>();
                    Iterable<DataSnapshot> orderChildren = dataSnapshot.getChildren();

                    for (DataSnapshot ord : orderChildren){
                        Order order = ord.getValue(Order.class);

                        if(orderStatus == order.getOrderStatus() && order.getPharmacyId().equals(Utils.convertEmail(email))){
                            list.add(order);
                        }

                    }

                    listener.onUpdated(list);
                }



            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Utils.showChildRemovedMsg();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
                Utils.showChildMovedMsg();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Utils.showOnCancelledMsg();
            }


        });
    }

    private static String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }

}
