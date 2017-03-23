package com.ifarma.ifarma.model;

import com.ifarma.ifarma.exceptions.*;
import com.ifarma.ifarma.util.*;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

abstract class AbstractUser{

	private String name;
	private String email;
	private String address; 
	private String houseNumber; 
	private String cep;
	private Map<String, Order> orders;
    private final SecureRandom random = new SecureRandom();

    AbstractUser() {}

   	AbstractUser(String email){
		this.email = email;
		this.name = "";
		this.address = "";
		this.houseNumber = "";
		this.cep = "";
	}

    AbstractUser(final String name, final String email,
				 final String address, final String houseNumber, final String cep) {

		this.name = name;
		this.email = email;
		this.address = address;
		this.houseNumber = houseNumber;
		this.cep = cep;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) throws InvalidNameException {
		if(!Validate.isValidName(name)){
			throw new InvalidNameException();
		}
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(final String address) throws InvalidAddressException {
		if(!Validate.isValidAddress(address)){
			throw new InvalidAddressException();
		}
		this.address = address;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(final String houseNumber) throws InvalidHouseNumberException {
		if(!Validate.isValidHouseNumber(houseNumber)){
			throw new InvalidHouseNumberException();
		}
		this.houseNumber = houseNumber;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(final String cep) throws InvalidCEPException {
		if(!Validate.isValidCEP(cep)){
			throw new InvalidCEPException();
		}
		this.cep = cep;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) throws InvalidEmailException {
		if(!Validate.isValidEmail(email)){
			throw new InvalidEmailException();
		}
		this.email = email;
	}

	public void initOrders(){
		this.orders = new HashMap<>();
	}

	public void addOrder(Order order){
		if(order != null) {
			orders.put(nextSessionId(), order);
		}
	}

	public void removeProduct(String id){
		if(id != null) {
			orders.remove(id);
		}
	}

	public Map<String, Order> getOrders(){
		return orders;
	}

    private String nextSessionId() {
        return new BigInteger(130, random).toString(32);
    }
}
