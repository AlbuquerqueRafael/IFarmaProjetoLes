package tests;

import org.junit.*;

import exceptions.InvalidNameException;
import exceptions.InvalidUserDataException;
import model.User;

public class UserTests {
		// criar uma lista de siglas de estados do BR na classe de validação. Se for lista não contém, é inválido o estado.
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

	private String validUsername;
	private String validMail;
	private String validPassword;
	private String validAddress ;
	private String validHouseNumber;
	private String validCEP;
	private String validState;
	
	private User userWithInvalidName;
	private User userWithValidName;
	
	@Before
	public void setUpValidUserData(){
		validName = "Jobson Lucas";
		validUsername = "lucaspk";
		validMail = "lucas@gmail.com";
		validPassword = "6xablau9";
		validAddress = "Rua das mulatas saradas";
		validHouseNumber = "150";
		validCEP = "58410538";
		validState = "PB";
	}
	
	@Before
	public void setUpInvalidUserData(){
		invalidNameWithOnlyLetters = "JobsonLucasDias";
		invalidNameWithGraphicAccentuation = "João Victor Mafra Bói";
		invalidNameWithOnlyNumbers = "1651561681818616818161516156";
		invalidNameWithOnlySpecialChars = "!bs@#      ¬¢*$   |)/%£_=+§{}ºª°?/;:<>¹²³";
		invalidNameWithOnlySpaces = "                           ";
		invalidNameMixingLettersAndSeveralNumbers = "Jobson54654 Lucas245541561";
		invalidNameMixingLettersAndOnlyOneNumber = "Jobson Lucas2";
		invalidNameMixingLettersAndSpecialChars = "Job$@# ¬u¢@! !@#sdf#WER@xcv$%$¨¨¨&**/-*";
		invalidNameMixingLettersAndNumbersAndSpecialChars = "J156651o51b$@# 156156¬u¢@!asdqwe2 !@#sdf#WER@xcv$%$¨¨¨&**/-*";
				
	}
	
	@Test
	public void testShouldPassCreatingUserValidName(){
		try {
			userWithValidName = new User(validName, validUsername, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingUserWithGraphAccentuationInName(){
		try {
			userWithInvalidName = new User(invalidNameWithGraphicAccentuation, validUsername, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingUserWithOnlyLettersInName(){
		try {
			userWithInvalidName = new User(invalidNameWithOnlyLetters, validUsername, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingUserWithOnlyNumbersAsName(){
		try {
			userWithInvalidName = new User(invalidNameWithOnlyNumbers, validUsername, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingUserWithOnlySpacesAsName(){
		try {
			userWithInvalidName = new User(invalidNameWithOnlySpaces, validUsername, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingUserWithOnlySpecialCharAsName(){
		try {
			userWithInvalidName = new User(invalidNameWithOnlySpecialChars, validUsername, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testShouldFailCreatingUserWithSpecialCharAndNumberInName(){
		try {
			userWithInvalidName = new User(invalidNameMixingLettersAndNumbersAndSpecialChars, validUsername, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingUserWithSpecialCharInName(){
		try {
			userWithInvalidName = new User(invalidNameMixingLettersAndSpecialChars, validUsername, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
			
	@Test
	public void testShouldFailCreatingUserWithSingleNumberInName(){
		try {
			userWithInvalidName = new User(invalidNameMixingLettersAndOnlyOneNumber, validUsername, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingUserWithSeveralNumbersInName(){
		try {
			userWithInvalidName = new User(invalidNameMixingLettersAndSeveralNumbers, validUsername, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	


}
