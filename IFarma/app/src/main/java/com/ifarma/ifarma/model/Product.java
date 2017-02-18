package model;

import exceptions.*;
import util.*;


/**
 * Created by Kelvin on 18-Feb-17.
 */

public class Product {

    private String name;
    private double price;
    private String lab;
    private String description;
    private boolean generic;

    public Product(final String name, final double price, final String lab, final String description, final boolean generic) throws InvalidProductDataException{
    	if(!Validate.isValidProductName(name)){
    		throw new InvalidProductDataException("The Product Name is invalid.");
    	}
    	if(!Validate.isValidProductLab(lab)){
    		throw new InvalidProductDataException("The Product Lab is invalid.");
    	}
    	if(!Validate.isValidProductDescription(description)){
    		throw new InvalidProductDataException("The Product Description is invalid.");
    	}
    	this.name = name;
        this.price = price;
        this.lab = lab;
        this.description = description;
        this.generic = generic;
    }

    // This empty constructor make easier the work with Firebase database.
    public Product(){}

    public String getNameProduct(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public String getLab(){
        return lab;
    }

    public String getDescription(){
        return description;
    }

    public boolean isGeneric(){
        return generic;
    }

    public void setNameProduct(final String newName) throws InvalidProductDataException{
    	if(!Validate.isValidProductName(newName)){
    		throw new InvalidProductDataException("The Product Name is invalid.");
    	}
        this.name = newName;
    }

    public void setPrice(final double newPrice){
        this.price = newPrice;
    }

    public void setLab(final String newLab) throws InvalidProductDataException{
    	if(!Validate.isValidProductLab(newLab)){
    		throw new InvalidProductDataException("The Product Lab is invalid.");
    	}
        this.lab = newLab;
    }

    public void setDescription(final String newDescription) throws InvalidProductDataException{
    	if(!Validate.isValidProductDescription(newDescription)){
    		throw new InvalidProductDataException("The Product Description is invalid.");
    	}
        this.description = newDescription;
    }

    public void setGeneric(final boolean newGeneric) {
        this.generic = newGeneric;
    }




}
