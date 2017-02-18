package com.ifarma.ifarma.model;

import com.ifarma.ifarma.util.*;
import com.ifarma.ifarma.exceptions.*;
import org.junit.*;

public class UserDataValidationTests {
		
	private String invalidNameWithOnlyLetters;
	private String invalidNameMixingLettersAndSeveralNumbers;
	private String invalidNameMixingLettersAndOnlyOneNumber;
	private String invalidNameMixingLettersAndSpecialChars;
	private String invalidNameMixingLettersAndNumbersAndSpecialChars;
	private String invalidNameWithOnlySpecialChars;
	private String invalidNameWithOnlyNumbers;
	private String invalidNameWithOnlySpaces;
	private String invalidNameWithGraphicAccentuation;
	private String validName;
	
	private String invalidPasswordOnlyNumbers;
	private String invalidPasswordWithOnlySpaces;
	private String invalidPasswordLengthInferiorToEight;
	private String validPasswordNumbersAndLetters;
	private String validPasswordNumbersAndSpecialChars;
	private String validPasswordLettersAndSpecialChars;
	private String validPasswordWithLettersAndSpecialCharsAndNumbers;
	
	private String validMail;
	private String invalidMailStartingWithNumber;
	private String invalidMailWithoutAtSymbol;
	private String invalidMailStartingWithDot;
	private String invalidMailStartingWithUnderscore;
	private String invalidMailStartingWithHyphen;
	private String invalidMailWithOnlyNumbersBeforeAtSymbol;
	private String invalidMailWithOnlySpecialCharsBeforeAtSymbol;
	private String invalidMailWithOnlySpacesBeforeAtSymbol;
	private String invalidMailWithOnlySpaces;
	private String invalidMailWithSpace;
	private String validMailWithUnderscoreBeforeAtSymbol;
	private String validMailWithDotBeforeAtSymbol;
	private String validMailWithHyphenBeforeAtSymbol;
	private String validMailWithAtLeastOneLetterBeforeAtSymbol;
	private String invalidMailWithInvalidSpecialCharsBeforeAtSymbol;
	
	private String validAddress ;
	private String invalidAddressWithOnlySpaces;
	private String invalidAddressWithoutSpaces;
	private String invalidAddressWithOnlyNumbers;
	private String invalidAddressWithSpecialSymbols;
	private String invalidAddressWithoutLetterAsFirstChar;
	
	private String validHouseNumber;
	private String validHouseNumberWithContainsHyphen;
	private String invalidHouseNumberFirstCharIsNotNumber;
	private String invalidHouseNumberWithOnlyLetters;
	private String invalidHouseNumberWithOnlySpaces;
	private String invalidHouseNumberWithSpecialChars;
	
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
		validMailWithUnderscoreBeforeAtSymbol = "lucas_dias@gmail.com";
		validMailWithDotBeforeAtSymbol = "lucas.dias@gmail.com";
		validMailWithHyphenBeforeAtSymbol = "lucas-dias@gmail.com";
		validMailWithAtLeastOneLetterBeforeAtSymbol = "ll5648546@gmail.com";
		
		validPasswordNumbersAndLetters = "6xablau9";
		validPasswordNumbersAndSpecialChars = "564864@#";
		validPasswordLettersAndSpecialChars = "lucas%&*";
		validPasswordWithLettersAndSpecialCharsAndNumbers = "lucas%9621&";
		
		validAddress = "R. das mulatas saradas-loucas";
		
		validHouseNumber = "150";
		validHouseNumberWithContainsHyphen = "150-A";
		
		validCEPOfCampinaGrande = "58410538";		
	}
	
	@Before
	public void setUpInvalidUserData(){
		invalidNameWithOnlyLetters = "JobsonLucasDias";
		invalidNameWithGraphicAccentuation = "Jo�o Victor Mafra B�i";
		invalidNameWithOnlyNumbers = "1651561681818616818161516156";
		invalidNameWithOnlySpecialChars = "!bs@#      ��*$   |)/%�_=+�{}���?/;:<>���";
		invalidNameWithOnlySpaces = "                           ";
		invalidNameMixingLettersAndSeveralNumbers = "Jobson54654 Lucas245541561";
		invalidNameMixingLettersAndOnlyOneNumber = "Jobson Lucas2";
		invalidNameMixingLettersAndSpecialChars = "Job$@# �u�@! !@#sdf#WER@xcv$%$���&**/-*";
		invalidNameMixingLettersAndNumbersAndSpecialChars = "J156651o51b$@# 156156�u�@!asdqwe2 !@#sdf#WER@xcv$%$���&**/-*";
				
		invalidMailStartingWithNumber = "9sdfsdf99@gmail.com";
		invalidMailStartingWithDot = ".999@gmail.com";
		invalidMailStartingWithUnderscore= "_999@gmail.com";
		invalidMailStartingWithHyphen= "-999@gmail.com";
		invalidMailWithoutAtSymbol = "999gmail.com";
		invalidMailWithOnlyNumbersBeforeAtSymbol = "999@gmail.com";
		invalidMailWithOnlySpecialCharsBeforeAtSymbol= "+-/*/*+.@gmail.com";
		invalidMailWithOnlySpacesBeforeAtSymbol = "                      @gmail.com";
		invalidMailWithOnlySpaces = "                              ";
		invalidMailWithSpace = "lucas dias@gmail.com";
		invalidMailWithInvalidSpecialCharsBeforeAtSymbol = "luc+a*s&dias@gmail.com";
		
		invalidPasswordOnlyNumbers = "46644684";
		invalidPasswordLengthInferiorToEight = "luc96$%";
		invalidPasswordWithOnlySpaces = "               ";
		
		invalidAddressWithOnlySpaces = "                   ";
		invalidAddressWithoutSpaces = "ruadasmulatassaradas";
		invalidAddressWithOnlyNumbers = "515646845123";
		invalidAddressWithSpecialSymbols = "ru@ d@$ mu|@!@$ s*r+d%&";
		invalidAddressWithoutLetterAsFirstChar = "8 rua das muladas saradas";
		
		invalidCEPWithLetters = "584a5282";
		invalidCEPWithSpecialChar = "5$410583";
		invalidCEPWithLengthInferiorToEight = "5841053";
		invalidCEPWithLengthSuperiorToEight = "584105338";
		invalidCEPforCampinaGrande = "50875-090";
		
		invalidHouseNumberFirstCharIsNotNumber = "A152";
		invalidHouseNumberWithOnlyLetters = "abcde";
		invalidHouseNumberWithOnlySpaces = "           ";
		invalidHouseNumberWithSpecialChars = "150@#$%%$-*-*/]&%";
		
	}
		
	@Test
	public void testShouldCreateCustomerHouseNumberContainsHyphen(){
		try {
			userValid = new Customer(validName, 
					validMailWithAtLeastOneLetterBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumberWithContainsHyphen, 
					validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingCustomerHouseNumberWithSpecialChars(){
		try {
			userInvalid = new Customer(validName, 
					validMailWithAtLeastOneLetterBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					validAddress,
					invalidHouseNumberWithSpecialChars, 
					validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidHouseNumberException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingCustomerHouseNumberWithOnlySpaces(){
		try {
			userInvalid = new Customer(validName, 
					validMailWithAtLeastOneLetterBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					validAddress,
					invalidHouseNumberWithOnlySpaces, 
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
					validPasswordNumbersAndLetters, 
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
					validPasswordNumbersAndLetters, 
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
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, 
					validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidAddressException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingCustomerCEPFromNotCampinaGrande(){
		try {
			userInvalid = new Customer(validName, 
					validMailWithAtLeastOneLetterBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
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
					validPasswordNumbersAndLetters, 
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
					validPasswordNumbersAndLetters, 
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
					validPasswordNumbersAndLetters, 
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
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, 
					invalidCEPWithLetters);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidCEPException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testShouldFailCreatingCustomerAddressWithoutLetterAsFirstChar(){
		try {
			userInvalid = new Customer(validName, validMailWithAtLeastOneLetterBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					invalidAddressWithoutLetterAsFirstChar,
					validHouseNumber, 
					validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidAddressException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingCustomerAddressWithOnlySpaces(){
		try {
			userInvalid = new Customer(validName, validMailWithAtLeastOneLetterBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					invalidAddressWithOnlySpaces,
					validHouseNumber, 
					validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidAddressException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testShouldFailCreatingCustomerAddressWithoutSpaces(){
		try {
			userInvalid = new Customer(validName, validMailWithAtLeastOneLetterBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					invalidAddressWithoutSpaces,
					validHouseNumber, 
					validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidAddressException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testShouldFailCreatingCustomerAddressWithOnlyNumbers(){
		try {
			userInvalid = new Customer(validName, validMailWithAtLeastOneLetterBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					invalidAddressWithOnlyNumbers,
					validHouseNumber, 
					validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidAddressException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingCustomerAddressWithSpecialSymbols(){
		try {
			userInvalid = new Customer(validName, validMailWithAtLeastOneLetterBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					invalidAddressWithSpecialSymbols,
					validHouseNumber, 
					validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidAddressException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testShouldCreateCustomerEmailWithAtLeastOneLetterBeforeAtSymbol(){
		try {
			userValid = new Customer(validName, validMailWithAtLeastOneLetterBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldCreateCustomerEmailWithHyphenBeforeAtSymbol(){
		try {
			userValid = new Customer(validName, validMailWithHyphenBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldCreateCustomerEmailWithDotBeforeAtSymbol(){
		try {
			userValid = new Customer(validName, validMailWithDotBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldCreateCustomerEmailWithUnderscoreBeforeAtSymbol(){
		try {
			userValid = new Customer(validName, validMailWithUnderscoreBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingCustomerEmailWithSpaceBeforeAtSymbol(){
		try {
			userInvalid = new Customer(validName, invalidMailWithSpace,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingCustomerEmailWithSpecialCharsBeforeAtSymbol(){
		try {
			userInvalid = new Customer(validName, invalidMailWithInvalidSpecialCharsBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingCustomerEmailWithOnlySpaces(){
		try {
			userInvalid = new Customer(validName, invalidMailWithOnlySpaces,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingCustomerEmailWithOnlySpacesBeforeAtSymbol(){
		try {
			userInvalid = new Customer(validName, invalidMailWithOnlySpacesBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingCustomerEmailWithOnlySpecialCharsBeforeAtSymbol(){
		try {
			userInvalid = new Customer(validName, invalidMailWithOnlySpecialCharsBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingCustomerEmailWithOnlyNumbersBeforeAtSymbol(){
		try {
			userInvalid = new Customer(validName, invalidMailWithOnlyNumbersBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingCustomerEmailWithHyphenAsFirstChar(){
		try {
			userInvalid = new Customer(validName, invalidMailStartingWithHyphen,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testShouldFailCreatingCustomerEmailWithUnderscoreAsFirstChar(){
		try {
			userInvalid = new Customer(validName, invalidMailStartingWithUnderscore,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testShouldFailCreatingCustomerEmailWithDotAsFirstChar(){
		try {
			userInvalid = new Customer(validName, invalidMailStartingWithDot,
					validPasswordNumbersAndLetters, 
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
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingCustomerEmailWithNumberAsFirstChar(){
		try {
			userInvalid = new Customer(validName, invalidMailStartingWithNumber,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testShouldFailCreatingCustomerWithOnlyNumbersAsPassword(){
		try {
			userInvalid = new Customer(validName, validMail, invalidPasswordOnlyNumbers, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingCustomerWithPasswordWithOnlySpaces(){
		try {
			userInvalid = new Customer(validName, validMail, invalidPasswordWithOnlySpaces, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingCustomerWithPasswordLengthInferiorToEight(){
		try {
			userInvalid = new Customer(validName, validMail, invalidPasswordLengthInferiorToEight, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testCreatingCustomerWithNumbersAndLettersAndSpecialCharsAsPassword(){
		try {
			userValid = new Customer(validName, validMail, validPasswordWithLettersAndSpecialCharsAndNumbers, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testCreatingCustomerWithNumbersAndLettersAsPassword(){
		try {
			userValid = new Customer(validName, validMail, validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingCustomerWithSpecialCharsAndLettersAsPassword(){
		try {
			userValid = new Customer(validName, validMail, validPasswordLettersAndSpecialChars, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingCustomerWithSpecialCharsAndNumbersAsPassword(){
		try {
			userValid = new Customer(validName, validMail, validPasswordNumbersAndSpecialChars, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingCustomerValidData(){
		try {
			userValid = new Customer(validName, validMail, validPasswordNumbersAndLetters, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testShouldFailCreatingCustomerWithGraphAccentuationInName(){
		try {
			userInvalid = new Customer(invalidNameWithGraphicAccentuation, validMail, validPasswordNumbersAndLetters, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingCustomerWithOnlyLettersInName(){
		try {
			userInvalid = new Customer(invalidNameWithOnlyLetters, validMail, validPasswordNumbersAndLetters, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingCustomerWithOnlyNumbersAsName(){
		try {
			userInvalid = new Customer(invalidNameWithOnlyNumbers, validMail, validPasswordNumbersAndLetters, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingCustomerWithOnlySpacesAsName(){
		try {
			userInvalid = new Customer(invalidNameWithOnlySpaces, validMail, validPasswordNumbersAndLetters, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingCustomerWithOnlySpecialCharAsName(){
		try {
			userInvalid = new Customer(invalidNameWithOnlySpecialChars, validMail, validPasswordNumbersAndLetters, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testShouldFailCreatingCustomerWithSpecialCharAndNumberInName(){
		try {
			userInvalid = new Customer(invalidNameMixingLettersAndNumbersAndSpecialChars, validMail, validPasswordNumbersAndLetters, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingCustomerWithSpecialCharInName(){
		try {
			userInvalid = new Customer(invalidNameMixingLettersAndSpecialChars, validMail, validPasswordNumbersAndLetters, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
			
	@Test
	public void testShouldFailCreatingCustomerWithSingleNumberInName(){
		try {
			userInvalid = new Customer(invalidNameMixingLettersAndOnlyOneNumber, validMail, validPasswordNumbersAndLetters, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingCustomerWithSeveralNumbersInName(){
		try {
			userInvalid = new Customer(invalidNameMixingLettersAndSeveralNumbers, validMail, validPasswordNumbersAndLetters, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	


}
