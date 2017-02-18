package com.ifarma.ifarma.model;

import com.ifarma.ifarma.util.*;
import com.ifarma.ifarma.exceptions.*;
import org.junit.*;

public class ProductDataValidateTests {
				
	private Product anador;
	private String description;
	private String labson;
	private boolean isGeneric = true;
	private boolean isNotGeneric = false;
	private double price = 2.50;
	private final String invalidProductNameWarn = "The Product Name is invalid.";
	private final String invalidProductLabWarn = "The Product Lab is invalid.";
	private final String invalidProductDescripWarn = "The Product Description is invalid.";
	private final String onlyNumbers = "1561151651561";
	private final String emptyString = "";
	
	@Before
	public void setUpProducts() throws InvalidProductDataException{
		description = "ningu�m se importa com essa hist�ria de mil meu comi o teu.";
		labson = "Predador de Calcinhas";
		
		anador = new Product("Anador", price, labson, description, isGeneric);
		
	}
		
	@Test
	public void testShouldNotSetProductNameToNumbers(){
		String correctName = anador.getNameProduct();
		try {
			anador.setNameProduct(onlyNumbers);
		} catch (InvalidProductDataException e) {
			
			Assert.assertEquals(invalidProductNameWarn, e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldNotSetProductNameToEmpty(){
		String correctName = anador.getNameProduct();
		try {
			anador.setNameProduct(emptyString);
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductNameWarn, e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldNotSetProductLabToNumbers(){
		String correctName = anador.getLab();
		try {
			anador.setLab(onlyNumbers);
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductLabWarn, e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldNotSetProductLabToEmpty(){
		String correctName = anador.getLab();
		try {
			anador.setLab(emptyString);
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductLabWarn, e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldNotSetProductDescripToNumbers(){
		String correctName = anador.getDescription();
		try {
			anador.setDescription(onlyNumbers);
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductDescripWarn, e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldNotSetProductDescripToEmpty(){
		String correctName = anador.getDescription();
		try {
			anador.setDescription(emptyString);
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductDescripWarn, e.getMessage());
		}
		
	}
	
	
}
