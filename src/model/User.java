package model;

import exceptions.*;
import util.*;

public class User {

	private String name;
	private final String username; 
	private final String email; 
	private String password;
	private String address; 
	private String houseNumber; 
	private String cep; 
	private String state;
	
	public User(final String name, final String username, final String email, final String password,
			final String address, final String houseNumber, final String cep, final String state) throws InvalidUserDataException {
		
		if(!Validate.isValidName(name)){
			throw new InvalidNameException();
		}
		
		if(!Validate.isValidUsername(username)){
			throw new InvalidUsernameException();
		}
		
		if(!Validate.isValidEmail(email)){
			throw new InvalidEmailException();
		}
		
		if(!Validate.isValidAddress(address)){
			throw new InvalidAddressException();
		}
		
		if(!Validate.isValidPassword(password)){
			throw new InvalidPasswordException();
		}
		
		this.name = name;
		this.username = username;
		this.email = email;
		this.password = password;
		this.address = address;
		this.houseNumber = houseNumber;
		this.cep = cep;
		this.state = state;
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

	public String getState() {
		return state;
	}

	public void setState(final String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}
	
	public String getUsername() {
		return username;
	}
		
}
