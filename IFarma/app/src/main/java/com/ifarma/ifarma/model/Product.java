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
    private static final String PHARMA_ID_IS_NULL_OR_EMPTY = "The ID of Pharma is null or empty.";
    private static final String PHARMA_NAME_IS_NULL_OR_EMPTY = "The name of Pharma is null or empty.";
    private static final String PRODUCT_NAME_IS_INVALID = "The Product Name is invalid.";
    private static final String PRODUCT_DESCRIP_IS_INVALID = "The Product Description is invalid.";
    private static final String PRODUCT_LAB_IS_INVALID = "The Product Lab is invalid.";

    public Product(final String name, final double price, final String lab,
                   final String description, final boolean generic,
                   final String pharmacyId, final String pharmacyName) throws InvalidProductDataException{
            if(!Validate.isValidProductName(name)){
                throw new InvalidProductDataException(PRODUCT_NAME_IS_INVALID);
            }
            if(!Validate.isValidProductLab(lab)){
                throw new InvalidProductDataException(PRODUCT_LAB_IS_INVALID);
            }
            if(!Validate.isValidProductDescription(description)){
                throw new InvalidProductDataException(PRODUCT_DESCRIP_IS_INVALID);
            }
            if(!Validate.isValidName(pharmacyName)){
                throw new InvalidProductDataException(PHARMA_NAME_IS_NULL_OR_EMPTY);
            }
            if(!Validate.isValidPharmaID(pharmacyId)){
                throw new InvalidProductDataException(PHARMA_ID_IS_NULL_OR_EMPTY);
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
            throw new InvalidProductDataException(PRODUCT_NAME_IS_INVALID);
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
            throw new InvalidProductDataException(PRODUCT_LAB_IS_INVALID);
        }
        else{this.lab = newLab;}
    }

    public void setDescription(final String newDescription) throws InvalidProductDataException{
        if(!Validate.isValidProductDescription(newDescription)){
            throw new InvalidProductDataException(PRODUCT_DESCRIP_IS_INVALID);
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
                throw new InvalidProductDataException(PHARMA_ID_IS_NULL_OR_EMPTY);
            } catch (InvalidProductDataException ignored) {

            }
        }
    }

    private int getSumOfAllASCIIValues(String strToConvertToASCII){
        int totalASCIIValueOfProductName = 0;
        char actualChar;
        for (int i = 0; i < strToConvertToASCII.length(); i++){
            actualChar = strToConvertToASCII.charAt(0);
            totalASCIIValueOfProductName += (int) actualChar;
        }
        return totalASCIIValueOfProductName;
    }

    private int multiplyASCIIValueOfProductNameAndPharmaID(){

        return getSumOfAllASCIIValues(name) * getSumOfAllASCIIValues(pharmacyId);
    }

    @Override
    public int hashCode() {
        return multiplyASCIIValueOfProductNameAndPharmaID();
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
                throw new InvalidProductDataException(PHARMA_NAME_IS_NULL_OR_EMPTY);
            } catch (InvalidProductDataException ignored) {

            }
        }
    }
}
