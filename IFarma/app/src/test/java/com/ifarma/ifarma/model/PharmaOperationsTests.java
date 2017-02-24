package com.ifarma.ifarma.model;

import com.ifarma.ifarma.exceptions.InvalidProductDataException;
import com.ifarma.ifarma.exceptions.InvalidUserDataException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PharmaOperationsTests {
				
	private Product anador;
	private Product supositorioRetal;
	private String description;
	private String labson;
	private boolean isGeneric = true;
	private double price = 2.50;

    private Pharma pharma;
    private String validCNPJ;
    private String pharmaName;


	@Before
	public void setUpProducts() throws InvalidProductDataException{
		description = "alivia ate a dor do peso dos chifres.";
		labson = "juliana comprou dipirona ja ana dor.";
		anador = new Product("Anador", price, labson, description, isGeneric, "gilekel");
        supositorioRetal = null;
	}

    @Before
    public void setUpPharma() throws InvalidUserDataException {
        pharma = new Pharma("Gilo boy pharma", "redepharma@hotmail.com",
                "5151526w2a", "Rua das Cebolas que nao fazem chorar",
                "1500", "58410538");

    }

	
	@Test
	public void testShouldAddAndRemoveProduct(){
        pharma.initProducts();
        pharma.addProduct(anador);
        Assert.assertEquals(1, pharma.getTotalProductsRegistered());
        pharma.addProduct(supositorioRetal);
        Assert.assertEquals(1, pharma.getTotalProductsRegistered());
        pharma.removeProduct(anador);
        Assert.assertEquals(0, pharma.getTotalProductsRegistered());
        pharma.removeProduct(supositorioRetal);
        Assert.assertEquals(0, pharma.getTotalProductsRegistered());
	}
	
	
}
