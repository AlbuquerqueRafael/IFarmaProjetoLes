package com.ifarma.ifarma.model;

import com.ifarma.ifarma.exceptions.InvalidAddressException;
import com.ifarma.ifarma.exceptions.InvalidCEPException;
import com.ifarma.ifarma.exceptions.InvalidCPFException;
import com.ifarma.ifarma.exceptions.InvalidEmailException;
import com.ifarma.ifarma.exceptions.InvalidHouseNumberException;
import com.ifarma.ifarma.exceptions.InvalidNameException;
import com.ifarma.ifarma.exceptions.InvalidProductDataException;
import com.ifarma.ifarma.exceptions.InvalidUserDataException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class CostumerPurchaseTests {

	private String validName;
	private String validMail;
	private String validAddress ;
	private String validHouseNumber;
	private String validCEPOfCampinaGrande;
	private String validCPF;

    private Product anador;
    private String description;
    private String labson;
    private boolean isGeneric = true;
    private boolean isNotGeneric = false;
    private double price = 2.50;

	private Customer customer;

	@Before
	public void setUpValidUserData() throws InvalidUserDataException{
		validName = "Jobson Lucas";
		validMail = "lucas@gmail.com";
		validAddress = "R. das mulatas saradas-loucas";
		validHouseNumber = "150";
		validCEPOfCampinaGrande = "58410538";
		validCPF = "34327908053";
		customer = new Customer(validMail);
	}

	@Before
	public void setUpValidPharmas(){
        Pharma pharma = new Pharma();
        try {
            pharma.setName("gilekel pharma");
        } catch (InvalidNameException e) {
            e.printStackTrace();
        }
        pharma.addProduct(anador);
	}

    @Before
    public void setUpProducts() throws InvalidProductDataException {
        description = "ninguem se importa com essa historia de mil meu comi o teu.";
        labson = "Predador de Calcinhas";

        anador = new Product("Anador", price, labson, description, isGeneric, "gilekel");

    }

    /** eu procuro um medicamento. Ao fazer isso, eu vou lá e add ele ao carrinho */
    @Test
    public void shouldBuyTheWantedProduct(){
        //Map<Pharma,Product> wantedProduct = costumer.searchProductInCertainPharma("gilekel pharma", "Anador");
        //customer.addToKart(wantedProduct.);
    }
}
