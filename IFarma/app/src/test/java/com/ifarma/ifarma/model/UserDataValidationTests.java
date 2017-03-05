package com.ifarma.ifarma.model;

import com.ifarma.ifarma.exceptions.*;

import org.junit.*;
public class UserDataValidationTests {

	private String invalidNullParam;
	private String invalidEmptyParam;
	private String validName;

	private String validMail;
	private String invalidMailWithoutAtSymbol;

	private String validMailWithAtLeastOneLetterBeforeAtSymbol;

	private String validAddress ;

	private String validHouseNumber;
	private String invalidHouseNumberFirstCharIsNotNumber;
	private String invalidHouseNumberWithOnlyLetters;

	private String validCEPOfCampinaGrande;
	private String invalidCEPWithLetters;
	private String invalidCEPWithSpecialChar;
	private String invalidCEPWithLengthInferiorToEight;
	private String invalidCEPWithLengthSuperiorToEight;
	private String invalidCEPforCampinaGrande;

	private Customer userInvalid;
	private Customer userValid;

	@Before
	public void setUpValidUserData() throws InvalidUserDataException{
		validName = "Jobson Lucas";

		validMail = "lucas@gmail.com";
		validMailWithAtLeastOneLetterBeforeAtSymbol = "ll5648546@gmail.com";

		validAddress = "R. das mulatas saradas-loucas";

		validHouseNumber = "150";

		validCEPOfCampinaGrande = "58410538";
	}

	@Before
	public void setUpInvalidUserData(){
		invalidNullParam = null;
		invalidEmptyParam = "       ";

		invalidMailWithoutAtSymbol = "999gmail.com";

		invalidCEPWithLetters = "584a5282";
		invalidCEPWithSpecialChar = "5$410583";
		invalidCEPWithLengthInferiorToEight = "5841053";
		invalidCEPWithLengthSuperiorToEight = "584105338";
		invalidCEPforCampinaGrande = "50875-090";

		invalidHouseNumberFirstCharIsNotNumber = "A152";
		invalidHouseNumberWithOnlyLetters = "abcde";

	}

	@Test
	public void testShouldFailCreatingCustomerWithNullHouseNumber(){
		try {
			userInvalid = new Customer(validName,
					validMailWithAtLeastOneLetterBeforeAtSymbol,
					validAddress,
					invalidNullParam,
					validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidHouseNumberException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testShouldFailCreatingCustomerWithEmptyHouseNumber(){
		try {
			userInvalid = new Customer(validName,
					validMailWithAtLeastOneLetterBeforeAtSymbol,
					validAddress,
					invalidEmptyParam,
					validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidHouseNumberException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testShouldFailCreatingCustomerHouseNumberWithOnlyLetters(){
		try {
			userInvalid = new Customer(validName,
					validMailWithAtLeastOneLetterBeforeAtSymbol,
					validAddress,
					invalidHouseNumberWithOnlyLetters,
					validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidHouseNumberException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testShouldFailCreatingCustomerHouseNumberWithFirstCharNotNumerical(){
		try {
			userInvalid = new Customer(validName,
					validMailWithAtLeastOneLetterBeforeAtSymbol,
					validAddress,
					invalidHouseNumberFirstCharIsNotNumber,
					validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidHouseNumberException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testShouldCreateCustomerCEPOfCampinaGrande(){
		try {
			userValid = new Customer(validName,
					validMailWithAtLeastOneLetterBeforeAtSymbol,
					validAddress,
					validHouseNumber,
					validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidAddressException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testShouldCreateCustomerWithNullCEP(){
		try {
			userValid = new Customer(validName,
					validMailWithAtLeastOneLetterBeforeAtSymbol,
					validAddress,
					validHouseNumber,
					invalidNullParam);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidCEPException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testShouldCreateCustomerWithEmptyCEP(){
		try {
			userValid = new Customer(validName,
					validMailWithAtLeastOneLetterBeforeAtSymbol,
					validAddress,
					validHouseNumber,
					invalidEmptyParam);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidCEPException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testShouldFailCreatingCustomerCEPFromNotCampinaGrande(){
		try {
			userInvalid = new Customer(validName,
					validMailWithAtLeastOneLetterBeforeAtSymbol,
					validAddress,
					validHouseNumber,
					invalidCEPforCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidCEPException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testShouldFailCreatingCustomerCEPLengthSuperiorToEight(){
		try {
			userInvalid = new Customer(validName,
					validMailWithAtLeastOneLetterBeforeAtSymbol,
					validAddress,
					validHouseNumber,
					invalidCEPWithLengthSuperiorToEight);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidCEPException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testShouldFailCreatingCustomerCEPLengthInferiorToEight(){
		try {
			userInvalid = new Customer(validName,
					validMailWithAtLeastOneLetterBeforeAtSymbol,
					validAddress,
					validHouseNumber,
					invalidCEPWithLengthInferiorToEight);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidCEPException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testShouldFailCreatingCustomerCEPWithSpecialChars(){
		try {
			userInvalid = new Customer(validName,
					validMailWithAtLeastOneLetterBeforeAtSymbol,
					validAddress,
					validHouseNumber,
					invalidCEPWithSpecialChar);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidCEPException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testShouldFailCreatingCustomerCEPWithLetter(){
		try {
			userInvalid = new Customer(validName,
					validMailWithAtLeastOneLetterBeforeAtSymbol,
					validAddress,
					validHouseNumber,
					invalidCEPWithLetters);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidCEPException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testShouldFailCreatingCustomerWithEmptyAddress(){
		try {
			userInvalid = new Customer(validName, validMailWithAtLeastOneLetterBeforeAtSymbol,
					invalidEmptyParam,
					validHouseNumber,
					validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidAddressException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testShouldFailCreatingCustomerWithNullAddress(){
		try {
			userInvalid = new Customer(validName, validMailWithAtLeastOneLetterBeforeAtSymbol,
					invalidNullParam,
					validHouseNumber,
					validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidAddressException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testShouldFailCreatingCustomerWithEmptyEmail(){
		try {
			userInvalid = new Customer(validName, invalidEmptyParam,
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testShouldFailCreatingCustomerWithNullEmail(){
		try {
			userInvalid = new Customer(validName, invalidNullParam,
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testShouldFailCreatingCustomerEmailWithoutAtSymbol(){
		try {
			userInvalid = new Customer(validName, invalidMailWithoutAtSymbol,
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testCreatingCustomerValidDataPassingAllArgs(){
		try {
			userValid = new Customer(validName, validMail, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testCreatingCustomerValidDataPassingOnlyEmail(){
		userValid = new Customer(validMail);
		Assert.assertEquals(validMail, userValid.getEmail());
	}

	@Test
	public void testCreatingCustomerValidDataPassingOnlyEmailAndChanging(){
		userValid = new Customer(validMail);
		try {
			userValid.setEmail("vamosBrincar@gmail.com");
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		Assert.assertEquals("vamosBrincar@gmail.com", userValid.getEmail());
	}

	@Test
	public void testShouldFailCreatingCustomerWithNullName(){
		try {
			userInvalid = new Customer(invalidNullParam, validMail, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testShouldFailCreatingCustomerWithEmptyName(){
		try {
			userInvalid = new Customer(invalidEmptyParam, validMail, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}

	}
}
