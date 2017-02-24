package com.ifarma.ifarma.model;


import java.io.Serializable;

import com.ifarma.ifarma.util.*;
import com.ifarma.ifarma.exceptions.*;

public class Product implements Serializable{

    private String name;
    private double price;
    private String lab;
    private String description;
    private boolean generic;
    private String pharmacyId;


    public Product(String name, double price, String lab, String description, boolean generic, String pharmacyId) throws InvalidProductDataException{
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
        this.pharmacyId = pharmacyId;
    }

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

    public String getPharmacyId(){
        return pharmacyId;
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

    public void setGeneric(boolean newGeneric) {
        this.generic = newGeneric;
    }

    public void setPharmacyId(String pharmacyId){
        this.pharmacyId = pharmacyId;
    }

    @Override
    public boolean equals(Object prod){
        if(!(prod instanceof Product)){
            return false;
        }

        Product prod1 = (Product) prod;

        return prod1.getNameProduct().equals(this.getNameProduct());
    }


}
