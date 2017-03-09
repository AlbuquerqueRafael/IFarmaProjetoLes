package com.ifarma.ifarma.model;
import com.ifarma.ifarma.exceptions.*;
import com.ifarma.ifarma.util.*;

public class Customer extends AbstractUser {
	
	private String cpf;
	
	public Customer() {
		super();
	}

	public Customer(String email){
		super(email);
		this.cpf = "";
	}

	public Customer(final String name, final String email,
					final String address, final String houseNumber, final String cep)
			throws InvalidUserDataException {

		super(name, email, address, houseNumber, cep);
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(final String cpf) throws InvalidCPFException {
		if(!Validate.isValidCPF(cpf)){
			throw new InvalidCPFException();
		}
		this.cpf = cpf;
	}
}
