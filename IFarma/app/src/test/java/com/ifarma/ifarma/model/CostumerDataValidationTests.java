package com.ifarma.ifarma.model;

import org.junit.*;

import com.ifarma.ifarma.exceptions.*;
import com.ifarma.ifarma.model.*;

public class CostumerDataValidationTests {
		
	private String invalidNameMixingLettersAndNumbersAndSpecialChars;
	private String validName;
	
	private String invalidPasswordOnlyNumbers;
	private String validPasswordLettersAndSpecialChars;
	
	private String validMail;
	private String invalidMailStartingWithNumber;
	
	private String validAddress ;
	private String invalidAddressWithOnlyNumbers;
	
	private String validHouseNumber;
	private String invalidHouseNumberWithOnlyLetters;

	private String validCEPOfCampinaGrande;
	private String invalidCEPforCampinaGrande;
	
	private String validCPF;
	private String invalidCPF;

	private Customer customer;
	
	
		
	@Before
	public void setUpValidUserData() throws InvalidUserDataException{
		validName = "Jobson Lucas";		
		
		validMail = "lucas@gmail.com";
	
		validPasswordLettersAndSpecialChars = "lucas%&*";
		
		validAddress = "R. das mulatas saradas-loucas";
		
		validHouseNumber = "150";
		
		validCEPOfCampinaGrande = "58410538";
		
		validCPF = "34327908053";
				
		customer = new Customer();
		
	}
	
	@Before
	public void setUpInvalidUserData(){
		invalidNameMixingLettersAndNumbersAndSpecialChars = "J156651o51b$@# 156156�u�@!asdqwe2 !@#sdf#WER@xcv$%$���&**/-*";
				
		invalidMailStartingWithNumber = "9sdfsdf99@gmail.com";
				
		invalidPasswordOnlyNumbers = "46644684";
		
		invalidCEPforCampinaGrande = "50875-090";
				
		invalidCPF = "12345678910";
		
		invalidAddressWithOnlyNumbers = "54564645414651651565";
		
		invalidHouseNumberWithOnlyLetters = "hjfsdfsdfsdfsdfsdfsdfsafsdfsdf";
	}
	
	@Test
	public void testShouldNotSetCPFOfCustomer(){
		try {
			customer.setCpf(invalidCPF);
		} catch (InvalidCPFException e) {
			Assert.assertEquals(new InvalidCPFException().getMessage(), e.getMessage());
		}	
	}
	
	@Test
	public void testShouldSetCPFOfCustomer(){
		try {
			customer.setCpf(validCPF);
		} catch (InvalidCPFException e) {
			Assert.assertEquals(new InvalidCPFException().getMessage(), e.getMessage());
		}		
		Assert.assertEquals(validCPF, customer.getCpf());
	}
	
	
	@Test
	public void testShouldNotSetNameOfCustomer(){
		try {
			customer.setName(invalidNameMixingLettersAndNumbersAndSpecialChars);
		} catch (InvalidNameException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}	
	}
	
	@Test
	public void testShouldNotSetEmailOfCustomer(){
		try {
			customer.setEmail(invalidMailStartingWithNumber);
		} catch (InvalidEmailException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}		
		
	}
	
	@Test
	public void testShouldNotSetPasswordOfCustomer(){
		try {
			customer.setPassword(invalidPasswordOnlyNumbers);
		} catch (InvalidPasswordException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}		
		
	}
	
	@Test
	public void testShouldNotSetAddressOfCustomer(){
		try {
			customer.setAddress(invalidAddressWithOnlyNumbers);
		} catch (InvalidAddressException e) {
			Assert.assertEquals(new InvalidAddressException().getMessage(), e.getMessage());
		}	
	}
	
	@Test
	public void testShouldNotSetCEPOfCustomer(){
		try {
			customer.setCep(invalidCEPforCampinaGrande);
		} catch (InvalidCEPException e) {
			Assert.assertEquals(new InvalidCEPException().getMessage(), e.getMessage());
		}		
		
	}
	
	@Test
	public void testShouldNotSetHouseNumberOfCustomer(){
		try {
			customer.setHouseNumber(invalidHouseNumberWithOnlyLetters);
		} catch (InvalidHouseNumberException e) {
			Assert.assertEquals(new InvalidHouseNumberException().getMessage(), e.getMessage());
		}		
		
	}
		
	@Test
	public void testShouldSetNameOfCustomer(){
		try {
			customer.setName(validName);
		} catch (InvalidNameException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		Assert.assertEquals(validName, customer.getName());
		
		
	}
	
	@Test
	public void testShouldSetEmailOfCustomer(){
		try {
			customer.setEmail(validMail);
		} catch (InvalidEmailException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		Assert.assertEquals(validMail, customer.getEmail());
		
	}
	
	@Test
	public void testShouldSetPasswordOfCustomer(){
		try {
			customer.setPassword(validPasswordLettersAndSpecialChars);
		} catch (InvalidPasswordException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}		
		Assert.assertEquals(validPasswordLettersAndSpecialChars, customer.getPassword());
		
	}
	
	@Test
	public void testShouldSetAddressOfCustomer(){
		try {
			customer.setAddress(validAddress);
		} catch (InvalidAddressException e) {
			Assert.assertEquals(new InvalidAddressException().getMessage(), e.getMessage());
		}	
		Assert.assertEquals(validAddress, customer.getAddress());
	}
	
	@Test
	public void testShouldSetCEPOfCustomer(){
		try {
			customer.setCep(validCEPOfCampinaGrande);
		} catch (InvalidCEPException e) {
			Assert.assertEquals(new InvalidCEPException().getMessage(), e.getMessage());
		}		
		Assert.assertEquals(validCEPOfCampinaGrande, customer.getCep());
	}
	
	@Test
	public void testShouldSetHouseNumberOfCustomer(){
		try {
			customer.setHouseNumber(validHouseNumber);
		} catch (InvalidHouseNumberException e) {
			Assert.assertEquals(new InvalidHouseNumberException().getMessage(), e.getMessage());
		}	
		Assert.assertEquals(validHouseNumber, customer.getHouseNumber());
	}


}
