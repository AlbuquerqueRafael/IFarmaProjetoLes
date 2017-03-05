package com.ifarma.ifarma.model;
import com.ifarma.ifarma.exceptions.InvalidCNPJException;
import com.ifarma.ifarma.exceptions.InvalidUserDataException;
import com.ifarma.ifarma.util.Validate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Pharma extends AbstractUser implements Serializable {

	private Map<String, Product> products;

	private String cnpj;

	public Pharma(final String name, final String email,
				  final String address, final String houseNumber, final String cep)
			throws InvalidUserDataException {
		super(name, email, address, houseNumber, cep);
	}

    public Pharma(String email){
		super(email);
		this.cnpj = "";
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
		this.products = new HashMap<>();
	}

	public void addProduct(Product product){
        if(product != null) {
            products.put(product.getNameProduct(), product);
        }
	}

	public void removeProduct(Product product){
        if(product != null) {
            products.remove(product.getNameProduct());
        }
	}

	public Map<String, Product>  getProducts(){
		return products;
	}

}
