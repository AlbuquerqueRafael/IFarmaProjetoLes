package model;

import exceptions.*;
import util.*;

public class Customer extends AbstractUser {
	
	private String cpf;
	
	public Customer() {super();}
	
	public Customer(final String name, final String email, final String password, 
			final String address, final String houseNumber, final String cep)
			throws InvalidUserDataException {

		super(name, email, password, address, houseNumber, cep);
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
