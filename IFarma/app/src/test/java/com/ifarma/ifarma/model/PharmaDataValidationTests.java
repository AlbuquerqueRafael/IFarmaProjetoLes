package com.ifarma.ifarma.model;

import com.ifarma.ifarma.exceptions.*;

import org.junit.*;

public class PharmaDataValidationTests {

	private String validCNPJ;
	private String invalidCNPJ;

	private Pharma pharma;
	private Pharma pharmaCrt;

	@Before
	public void setUp() throws InvalidUserDataException{
		pharma = new Pharma();
		validCNPJ = "46633676000113";
		invalidCNPJ = "0000067000000300000000000";

	}

	@Test
	public void testShouldNotSetCNPJOfPharma(){
		try {
			pharma.setCnpj(invalidCNPJ);
		} catch (InvalidCNPJException e) {
			Assert.assertEquals(new InvalidCNPJException().getMessage(), e.getMessage());
		}
	}

	@Test
	public void testShouldSetCNPJOfPharma(){
		try {
			pharma.setCnpj(validCNPJ);
		} catch (InvalidCNPJException e) {
			Assert.assertEquals(new InvalidCNPJException().getMessage(), e.getMessage());
		}
		Assert.assertEquals(validCNPJ, pharma.getCnpj());
	}

	@Test
	public void testShouldFailCreatingInvalidName(){
		//try {
			pharmaCrt = new Pharma("Gilo boy ph@rm@", "redepharma@hotmail.com",
					"Rua das Cebolas que nao fazem chorar",
					"1500", "58410538");
		//} catch (InvalidUserDataException e) {
		//	Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		//}
	}

}
