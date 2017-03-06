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
    private String pharmacyName;

    public Product(String name, double price, String lab, String description, boolean generic, String pharmacyId, String pharmacyName) throws InvalidProductDataException{
            if(!Validate.isValidProductName(name)){
                throw new InvalidProductDataException("The Product Name is invalid.");
            }
            if(!Validate.isValidProductLab(lab)){
                throw new InvalidProductDataException("The Product Lab is invalid.");
            }
            if(!Validate.isValidProductDescription(description)){
                throw new InvalidProductDataException("The Product Description is invalid.");
            }
            if(!Validate.isValidName(pharmacyName)){
                throw new InvalidProductDataException("The name of Pharma is null or empty.");
            }
            if(!Validate.isValidPharmaID(pharmacyId)){
                throw new InvalidProductDataException("The ID of Pharma is null or empty.");
            }

        this.name = name;
        this.price = price;
        this.lab = lab;
        this.description = description;
        this.generic = generic;
        this.pharmacyId = pharmacyId;
        this.pharmacyName = pharmacyName;
    }

    public Product(){
        this.name = "default product";
        this.price = 0.0;
        this.lab = "default lab";
        this.description = "default description";
        this.generic = true;
        this.pharmacyId = "default pharmacy id";
        this.pharmacyName = "default pharmacy name";
    }

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
        else{
            this.name = newName;
        }
    }

    public void setPrice(final double newPrice){
        this.price = newPrice;
    }

    public void setLab(final String newLab) throws InvalidProductDataException{
        if(!Validate.isValidProductLab(newLab)){
            throw new InvalidProductDataException("The Product Lab is invalid.");
        }
        else{this.lab = newLab;}
    }

    public void setDescription(final String newDescription) throws InvalidProductDataException{
        if(!Validate.isValidProductDescription(newDescription)){
            throw new InvalidProductDataException("The Product Description is invalid.");
        }
        else{this.description = newDescription;}
    }

    public void setGeneric(boolean newGeneric) {
        this.generic = newGeneric;
    }

    public void setPharmacyId(String pharmacyId){
        if(Validate.isValidPharmaID(pharmacyId)){
            this.pharmacyId = pharmacyId;
        }
        else{
            try {
                throw new InvalidProductDataException("The name of Pharma is null or empty.");
            } catch (InvalidProductDataException e) {
                e.getMessage();
            }
        }
    }

    @Override
    public boolean equals(Object prod){
        if(!(prod instanceof Product)){
            return false;
        }

        Product prod1 = (Product) prod;

        return prod1.getNameProduct().equals(this.getNameProduct());
    }

    public String getPharmacyName() {
        return this.pharmacyName;
    }

    public void setPharmacyName(String pharmacyName) {
        if(Validate.isValidName(pharmacyName)){
            this.pharmacyName = pharmacyName;
        }
        else{
            try {
                throw new InvalidProductDataException("The name of Pharma is null or empty.");
            } catch (InvalidProductDataException e) {
                e.getMessage();
            }
        }
    }
}
