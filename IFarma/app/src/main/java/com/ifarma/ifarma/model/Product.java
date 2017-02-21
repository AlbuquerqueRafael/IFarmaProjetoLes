package com.ifarma.ifarma.model;


import java.io.Serializable;

/**
 * Created by Kelvin on 18-Feb-17.
 */

public class Product implements Serializable{

    private String name;
    private double price;
    private String lab;
    private String description;
    private boolean generic;
    private String pharmacyId;


    public Product(String name, double price, String lab, String description, boolean generic, String pharmacyId){
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

    public void setNameProduct(String newName){
        this.name = newName;
    }

    public void setPrice(double newPrice){
        this.price = newPrice;
    }

    public void setLab(String newLab){
        this.lab = newLab;
    }

    public void setDescription(String newDescription){
        this.description = newDescription;
    }

    public void setGeneric(boolean newGeneric) {
        this.generic = newGeneric;
    }

    public void setPharmacyId(String pharmacyId){
        this.pharmacyId = pharmacyId;
    }

    public boolean equals(Object prod){
        if(!(prod instanceof Product)){
            return false;
        }

        Product prod1 = (Product) prod;

        return prod1.getNameProduct().equals(this.getNameProduct());
    }


}
