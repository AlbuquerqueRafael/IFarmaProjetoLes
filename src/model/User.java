package model;

import exceptions.*;
import util.*;

public class User {

	private String name;
	private String email; 
	private String password;
	private String address; 
	private String houseNumber; 
	private String cep;
	
	public User(final String name, final String email, final String password, 
			final String address, final String houseNumber, final String cep) throws InvalidUserDataException {
		
		if(!Validate.isValidName(name)){
			throw new InvalidNameException();
		}
				
		if(!Validate.isValidEmail(email)){
			throw new InvalidEmailException();
		}
		
		if(!Validate.isValidPassword(password)){
			throw new InvalidPasswordException();
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
		this.password = password;
		this.address = address;
		this.houseNumber = houseNumber;
		this.cep = cep;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(final String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(final String cep) {
		this.cep = cep;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
