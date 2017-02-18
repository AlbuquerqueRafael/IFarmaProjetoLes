package com.ifarma.ifarma.model;
import com.ifarma.ifarma.exceptions.*;
import com.ifarma.ifarma.util.Validate;

import java.util.ArrayList;
import java.util.List;

public class Pharma extends AbstractUser {

	private List<Product> productsList;

	private String cnpj;
	//private Map<Product, Integer> stock = new Map<Product, Integer>();
	public Pharma(final String name, final String email, final String password, 
			final String address, final String houseNumber, final String cep)
			throws InvalidUserDataException {
		super(name, email, password, address, houseNumber, cep);
	}
	
	public Pharma(){super();}
	
	public String getCnpj() {
		return cnpj;
	}
	
	public void setCnpj(final String cnpj) throws InvalidCNPJException {
		if(!Validate.isValidCNPJ(cnpj)){
			throw new InvalidCNPJException();
		}
		this.cnpj = cnpj;
	}

	public void initProductsList(){
		this.productsList = new ArrayList<Product>();
	}

	public void addProduct(Product product){
		this.productsList.add(product);
	}

	public void removeProduct(Product product){
		this.productsList.remove(product);
	}
	
}
