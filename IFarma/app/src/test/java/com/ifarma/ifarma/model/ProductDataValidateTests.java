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
	private String invalidEmptyParam;
	private String invalidNullParam;

	@Before
	public void setUpProducts() throws InvalidProductDataException{
		description = "ninguem se importa com essa historia.";
		labson = "Predador de Calcinhas";
		invalidEmptyParam = "              ";
		invalidNullParam = null;
		anador = new Product("Anador", price, labson, description, isGeneric, "1", "Gilekel pharma");
	}
		
	@Test
	public void testShouldNotSetProductNameToNull(){
		try {
			anador.setNameProduct(invalidNullParam);
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductNameWarn, e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldNotSetProductNameToEmpty(){
		String correctName = anador.getNameProduct();
		try {
			anador.setNameProduct(invalidEmptyParam);
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductNameWarn, e.getMessage());
		}
		
	}

	public void testShouldNotSetProductNameToMoreThan30Chars(){
		String name = "minha terra tem palmeiras onde canta o sabiar";
		try {
			anador.setNameProduct(name);
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductNameWarn, e.getMessage());
		}

	}
	
	@Test
	public void testShouldNotSetProductLabToNull(){
		String correctName = anador.getLab();
		try {
			anador.setLab(invalidNullParam);
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductLabWarn, e.getMessage());
		}
	}
	
	@Test
	public void testShouldNotSetProductLabToEmpty(){
		String correctName = anador.getLab();
		try {
			anador.setLab(invalidEmptyParam);
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductLabWarn, e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldNotSetProductDescripToNull(){
		try {
			anador.setDescription(invalidNullParam);
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductDescripWarn, e.getMessage());
		}
		
	}

	public void testShouldNotSetProductDescripToMoreThan50Chars(){
		String descrip = "minha terra tem palmeiras onde canta o sabiá as arvers que aqui ggggg";
		try {
			anador.setDescription(descrip);
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductDescripWarn, e.getMessage());
		}

	}

	@Test
	public void testShouldNotSetProductDescripToEmpty(){
		String correctName = anador.getDescription();
		try {
			anador.setDescription(invalidEmptyParam);
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductDescripWarn, e.getMessage());
		}
		
	}
	
	
}
