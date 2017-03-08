package com.ifarma.ifarma.model;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

/**
 * Created by Gabriel on 03/03/2017.
 */

public class Order {

    private String _deliveryAddress;
    private String _customerName;
    private OrderStatus _orderStatus;
    private double _orderTotalPrice;
    private String id;
    private Date date;
    private String description;
    private String comment;
    private String pharmacyId;
    private String customerTelephone;

    public Order(){

    }

    public Order(String _deliveryAddress, String _customerName, double _orderTotalPrice, String id,
                 String description, String customerTelephone, String comment, String pharmacyId, Date date) {
        this._deliveryAddress = _deliveryAddress;
        this._customerName = _customerName;
        this._orderStatus = OrderStatus.WAITING_ORDER;
        this._orderTotalPrice = _orderTotalPrice;
        this.description = description;
        this.id = id;
        this.customerTelephone = customerTelephone;
        this.pharmacyId = pharmacyId;
        this.comment = comment;
        this.date = date;
    }

    public String getDeliveryAddress() {
        return _deliveryAddress;
    }

    public void setDeliveryAddress(String _deliveryAddress) {
        this._deliveryAddress = _deliveryAddress;
    }

    public String getCustomerName() {
        return _customerName;
    }

    public void setCustomerName(String _customerName) {
        this._customerName = _customerName;
    }

    public OrderStatus getOrderStatus() {
        return _orderStatus;
    }

    public void setOrderStatus(OrderStatus _orderStatus) {
        this._orderStatus = _orderStatus;
    }

    public double getOrderTotalPrice() {
        return _orderTotalPrice;
    }

    public void setOrderTotalPrice(double _orderTotalPrice) {
        this._orderTotalPrice = _orderTotalPrice;
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
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
