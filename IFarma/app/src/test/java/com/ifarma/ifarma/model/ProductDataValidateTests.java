package com.ifarma.ifarma.model;

import com.ifarma.ifarma.util.*;
import com.ifarma.ifarma.exceptions.*;
import org.junit.*;

public class ProductDataValidateTests {
				
	private Product anador;
	private Product productinho;
	private Product anadorCopy;
	private String description;
	private String labson;
	private boolean isGeneric = true;
	private boolean isNotGeneric = false;
	private double price = 2.50;
	private final String invalidProductNameWarn = "The Product Name is invalid.";
	private final String invalidProductLabWarn = "The Product Lab is invalid.";
	private final String invalidProductDescripWarn = "The Product Description is invalid.";
	private final String invalidProductIDWarn = "The ID of Pharma is null or empty.";
	private final String invalidProductPharmaNameWarn = "The name of Pharma is null or empty.";
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
		anadorCopy = new Product("Anador", price, labson, description, isGeneric, "1", "Gilekel pharma");
	}

	@Test
	public void testShouldChangeDescript(){
		String novaDescript = "caxalau morreu de doooor";
		try {
			productinho = new Product("histamin", price, labson, description, isGeneric, "1", "Gilekel pharma");
			productinho.setDescription(novaDescript);
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductNameWarn, e.getMessage());
		}
		Assert.assertEquals(novaDescript, productinho.getDescription());
	}

	@Test
	public void testShouldNotBeEqualsProductWithoutName(){
		try {
			productinho = new Product("histamin", price, labson, description, isGeneric, "1", "Gilekel pharma");
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductNameWarn, e.getMessage());
		}

		Assert.assertEquals(false, productinho.equals(new Product()));
	}

	@Test
	public void testShouldChangeLab(){
		String novoLab = "caxalau";
		try {
			productinho = new Product("histamin", price, labson, description, isGeneric, "1", "Gilekel pharma");
			productinho.setLab(novoLab);
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductNameWarn, e.getMessage());
		}

		Assert.assertEquals(novoLab, productinho.getLab());
	}

	@Test
	public void testShouldChangeIfIsGeneric(){
		try {
			productinho = new Product("histamin", price, labson, description, isGeneric, "1", "Gilekel pharma");
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductNameWarn, e.getMessage());
		}
		productinho.setGeneric(isNotGeneric);
		Assert.assertEquals(isNotGeneric, productinho.isGeneric());
	}

	@Test
	public void testShouldChangePrice(){
		try {
			productinho = new Product("histamin", price, labson, description, isGeneric, "1", "Gilekel pharma");
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductNameWarn, e.getMessage());
		}
		double novoPreco = 24.50;
		productinho.setPrice(novoPreco);
		Assert.assertEquals(novoPreco, productinho.getPrice(), 0.00001);
	}

	@Test
	public void testShouldNotCreateProductWithNameEmpty(){
		try {
			productinho = new Product("", price, labson, description, isGeneric, "1", "Gilekel pharma");
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductNameWarn, e.getMessage());
		}
	}

	@Test
	public void testShouldNotCreateProductWithLabEmpty(){
		try {
			productinho = new Product("Camisa de venus", price, invalidEmptyParam, description, isGeneric, "1", "Gilekel pharma");
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductLabWarn, e.getMessage());
		}
	}

	@Test
	public void testShouldNotCreateProductWithDescriptionEmpty(){
		try {
			productinho = new Product("Camisa de venus", price, "luxuria", invalidEmptyParam, isGeneric, "1", "Gilekel pharma");
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductDescripWarn, e.getMessage());
		}
	}

	@Test
	public void testShouldNotCreateProductWithIdEmpty(){
		try {
			productinho = new Product("Camisa de venus", price, "luxuria", "loxorea", isGeneric, "", "Gilekel pharma");
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductIDWarn, e.getMessage());
		}
	}

	@Test
	public void testShouldNotCreateProductWithPharmaNameEmpty(){
		try {
			productinho = new Product("Camisa de venus", price, "luxuria", "loxorea", isGeneric, "xzzx", "");
		} catch (InvalidProductDataException e) {
			Assert.assertEquals(invalidProductPharmaNameWarn, e.getMessage());
		}
	}

	@Test
	public void testShouldBeNotEqualsBecauseOfDifferentClasses(){
		Assert.assertEquals(false, anadorCopy.equals(new String("churupita")));
	}

	@Test
	public void testShouldBeEqualsBecauseOfSameProductName(){
		Assert.assertEquals(true, anadorCopy.equals(anador));
	}

	@Test
	public void testShouldBeNotEqualsBecauseOfDifferentProductName(){
		try {
			anador.setNameProduct("bom bril");
		} catch (InvalidProductDataException e) {
			e.getMessage();
		}
		Assert.assertEquals(false, anadorCopy.equals(anador));
	}

	@Test
	public void testShouldNotSetProductPharmaIDToNull(){
		String correctPharmaID = anador.getPharmacyId();
		anador.setPharmacyId(invalidNullParam);
		Assert.assertEquals(correctPharmaID, anador.getPharmacyId());

	}

	@Test
	public void testShouldNotSetProductPharmaIDToEmpty(){
		String correctPharmaID = anador.getPharmacyId();
		anador.setPharmacyId(invalidEmptyParam);
		Assert.assertEquals(correctPharmaID, anador.getPharmacyId());

	}

	@Test
	public void testShouldNotSetProductPharmaNamesToNull(){
		String correctPharmaName = anador.getPharmacyName();
		anador.setPharmacyName(invalidNullParam);
		Assert.assertEquals(correctPharmaName, anador.getPharmacyName());

	}

	@Test
	public void testShouldNotSetProductPharmaNameToEmpty(){
		String correctPharmaName = anador.getPharmacyName();
		anador.setPharmacyName(invalidEmptyParam);
		Assert.assertEquals(correctPharmaName, anador.getPharmacyName());

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
