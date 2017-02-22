package com.ifarma.ifarma.model;
import com.ifarma.ifarma.exceptions.*;
import com.ifarma.ifarma.util.Validate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pharma extends AbstractUser implements Serializable {

	private Map<String, Product> products;
	private List<Product> Product;

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

	public void initProducts(){
		this.products = new HashMap<String, Product>();
	}

	public void addProduct(Product product){
		this.products.entrySet();
	}

	public void removeProduct(Product product){
		this.products.remove(product);
	}

	public Map<String, Product>  getProducts(){
		return products;
	}
	
}
