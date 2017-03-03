package com.ifarma.ifarma.model;

import com.ifarma.ifarma.exceptions.*;
import com.ifarma.ifarma.util.*;

abstract class AbstractUser{

	private String name;
	private String email;
	private String address; 
	private String houseNumber; 
	private String cep;

    public AbstractUser() {}

    public AbstractUser(String email){
		this.email = email;
		this.name = "";
		this.address = "";
		this.houseNumber = "";
		this.cep = "";
	}

    public AbstractUser(final String name, final String email,
				 final String address, final String houseNumber, final String cep) throws InvalidUserDataException {
		
		if(!Validate.isValidName(name)){
			throw new InvalidNameException();
		}
				
		if(!Validate.isValidEmail(email)){
			throw new InvalidEmailException();
		}

		if(!Validate.isValidAddress(address)){
			throw new InvalidAddressException();
		}
		
		if(!Validate.isValidHouseNumber(houseNumber)){
			throw new InvalidHouseNumberException();
		}
		
		if(!Validate.isValidCEP(cep)){
			throw new InvalidCEPException();
		}
		
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
		
}
