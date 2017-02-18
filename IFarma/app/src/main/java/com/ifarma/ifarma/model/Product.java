package com.ifarma.ifarma.model;


/**
 * Created by Kelvin on 18-Feb-17.
 */

public class Product {

    private String name;
    private double price;
    private String lab;
    private String description;
    private boolean generic;

    public Product(String name, double price, String lab, String description, boolean generic){
        this.name = name;
        this.price = price;
        this.lab = lab;
        this.description = description;
        this.generic = generic;
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




}
