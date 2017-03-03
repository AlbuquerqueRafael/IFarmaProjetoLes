package com.ifarma.ifarma.model;

/**
 * Created by Gabriel on 03/03/2017.
 */

public class Order {

    private String _deliveryAddress;
    private String _customerName;
    private OrderStatus _orderStatus;
    private float _orderTotalPrice;
    private float _orderChange;
    private int _orderAmount;

    public Order(String _deliveryAddress, String _customerName, float _orderTotalPrice, float _orderChange, int _orderAmount) {
        this._deliveryAddress = _deliveryAddress;
        this._customerName = _customerName;
        this._orderStatus = OrderStatus.WAITING_ORDER;
        this._orderTotalPrice = _orderTotalPrice;
        this._orderChange = _orderChange;
        this._orderAmount = _orderAmount;
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

    public float getOrderTotalPrice() {
        return _orderTotalPrice;
    }

    public void setOrderTotalPrice(float _orderTotalPrice) {
        this._orderTotalPrice = _orderTotalPrice;
    }

    public float getOrderChange() {
        return _orderChange;
    }

    public void setOrderChange(float _orderChange) {
        this._orderChange = _orderChange;
    }

    public int getOrderAmount() {
        return _orderAmount;
    }

    public void setOrderAmount(int _orderAmount) {
        this._orderAmount = _orderAmount;
    }
}
