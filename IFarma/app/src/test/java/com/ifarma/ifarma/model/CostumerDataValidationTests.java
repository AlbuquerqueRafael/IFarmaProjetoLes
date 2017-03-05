package com.ifarma.ifarma.model;

import org.junit.*;

import com.ifarma.ifarma.exceptions.*;
import com.ifarma.ifarma.model.*;

public class CostumerDataValidationTests {

	private String validName;

	private String invalidEmptyParam;
	private String invalidNullParam;

	private String validMail;
	private String invalidEmailWithoutAtSymbol;

	private String validAddress ;

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

		validAddress = "R. das mulatas saradas-loucas";

		validHouseNumber = "150";

		validCEPOfCampinaGrande = "58410538";

		validCPF = "34327908053";

		customer = new Customer();

	}

	@Before
	public void setUpInvalidUserData(){

		invalidCEPforCampinaGrande = "50875-090";

		invalidCPF = "12345678910000";

		invalidHouseNumberWithOnlyLetters = "hjfsdfsdfsdfsdfsdfsdfsafsdfsdf";

		invalidEmailWithoutAtSymbol = "xablau.gmail.com";

		invalidEmptyParam = "              ";
		invalidNullParam = null;
	}

	@Test
	public void testShouldNotSetCPFOfCustomerForInvalidCPF(){
		try {
			customer.setCpf(invalidCPF);
		} catch (InvalidCPFException e) {
			Assert.assertEquals(new InvalidCPFException().getMessage(), e.getMessage());
		}
	}

	@Test
	public void testShouldNotSetCPFOfCustomerForInvalidEmpty(){
		try {
			customer.setCpf(invalidEmptyParam);
		} catch (InvalidCPFException e) {
			Assert.assertEquals(new InvalidCPFException().getMessage(), e.getMessage());
		}
	}

	@Test
	public void testShouldNotSetCPFOfCustomerForInvalidNull(){
		try {
			customer.setCpf(invalidNullParam);
		} catch (InvalidCPFException e) {
			Assert.assertEquals(new InvalidCPFException().getMessage(), e.getMessage());
		}
	}

	@Test
	public void testShouldSetCPFOfCustomerForValidCPF(){
		try {
			customer.setCpf(validCPF);
		} catch (InvalidCPFException e) {
			Assert.assertEquals(new InvalidCPFException().getMessage(), e.getMessage());
		}
		Assert.assertEquals(validCPF, customer.getCpf());
	}

	@Test
	public void testShouldNotSetNameOfCustomerForEmpty(){
		try {
			customer.setName(invalidEmptyParam);
		} catch (InvalidNameException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
	}

	@Test
	public void testShouldNotSetNameOfCustomerForNull(){
		try {
			customer.setName(invalidNullParam);
		} catch (InvalidNameException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
	}

	@Test
	public void testShouldNotSetEmailOfCustomerForNull(){
		try {
			customer.setEmail(invalidNullParam);
		} catch (InvalidEmailException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testShouldNotSetEmailOfCustomerForInvalidEmailWithoutAtSymbol(){
		try {
			customer.setEmail(invalidEmailWithoutAtSymbol);
		} catch (InvalidEmailException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testShouldNotSetEmailOfCustomerForEmpty(){
		try {
			customer.setEmail(invalidEmptyParam);
		} catch (InvalidEmailException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testShouldNotSetAddressOfCustomerForEmpty(){
		try {
			customer.setAddress(invalidEmptyParam);
		} catch (InvalidAddressException e) {
			Assert.assertEquals(new InvalidAddressException().getMessage(), e.getMessage());
		}
	}

	@Test
	public void testShouldNotSetAddressOfCustomerForNull(){
		try {
			customer.setAddress(invalidNullParam);
		} catch (InvalidAddressException e) {
			Assert.assertEquals(new InvalidAddressException().getMessage(), e.getMessage());
		}
	}

	@Test
	public void testShouldNotSetCEPOfCustomerForInvalidCEPforCG(){
		try {
			customer.setCep(invalidCEPforCampinaGrande);
		} catch (InvalidCEPException e) {
			Assert.assertEquals(new InvalidCEPException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testShouldNotSetCEPOfCustomerForEmpty(){
		try {
			customer.setCep(invalidEmptyParam);
		} catch (InvalidCEPException e) {
			Assert.assertEquals(new InvalidCEPException().getMessage(), e.getMessage());
		}

	}

	@Test
	public void testShouldNotSetCEPOfCustomerForNull(){
		try {
			customer.setCep(invalidNullParam);
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
	public void testShouldSetNameOfCustomerForValidName(){
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
