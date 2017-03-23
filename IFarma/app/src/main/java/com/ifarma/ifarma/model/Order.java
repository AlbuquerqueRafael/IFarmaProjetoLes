package com.ifarma.ifarma.model;

import java.util.Date;

public class Order {

    private String deliveryAddress;
    private String customerName;
    private OrderStatus orderStatus;
    private double orderTotalPrice;
    private String id;
    private Date date;
    private String description;
    private String comment;
    private String pharmacyId;
    private String customerTelephone;
    private String userToken;
    private String pharmacyName;

    public Order(){
        // construtor padrão
    }
    // talvez criar um objeto para encapsular deliveryAddress, costumerName, costumerTelephone
    // talvez criar um objeto para os dados da compra: encapsular orderTotalPrice, comment, date, description
    public Order(String deliveryAddress, String customerName, double orderTotalPrice, String id,
                 String description, String customerTelephone, String comment, String pharmacyId, Date date, String userToken) {
        this.deliveryAddress = deliveryAddress;
        this.customerName = customerName;
        this.orderStatus = OrderStatus.WAITING_ORDER;
        this.orderTotalPrice = orderTotalPrice;
        this.description = description;
        this.id = id;
        this.customerTelephone = customerTelephone;
        this.pharmacyId = pharmacyId;
        this.comment = comment;
        this.date = new Date(date.getTime());
        this.userToken = userToken;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public String getId(){
        return this.id;
    }

    public void setId(String id){
        this.id = id;
    }
    public String getComment(){
        return this.comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getPharmacyId(){
        return this.pharmacyId;
    }

    public void setPharmacyId(String pharmacyId){
        this.pharmacyId = pharmacyId;
    }

    public String getCustomerTelephone(){
        return this.customerTelephone;
    }

    public void setCustomerTelephone(String customerTelephone) {
        this.customerTelephone = customerTelephone;
    }

    public Date getDate(){
        return new Date(this.date.getTime());
    }

    public void setDate(Date date) {
        this.date = new Date(date.getTime());
    }

    public void setUserToken(String userToken){
        this.userToken = userToken;
    }

    public String getUserToken(){
        return this.userToken;
    }

    public void setPharmacyName(String pharmacyName){
        this.pharmacyName = pharmacyName;
    }

    public String getPharmacyName(){
        return this.pharmacyName;
    }
}
