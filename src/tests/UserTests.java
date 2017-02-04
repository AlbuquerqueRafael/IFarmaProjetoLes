package tests;

import org.junit.*;

import exceptions.InvalidNameException;
import exceptions.InvalidUsernameException;
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

	private String validUsernameWithOnlyLetters;
	private String validUsernameMixLettersAndNumbers;
	private String validUsernameWithUnderscore;
	private String validUsernameMixLettersNumbersSeparators;
	private String invalidUsernameWithOnlyNumbers;
	private String invalidUsernameWithSpace;
	private String invalidUsernameWithOnlySpecialChars;
	private String invalidUsernameWithOnlySpaces;
	private String invalidUsernameWithGraphicAccentuation;
	
	private String validMail;
	private String validPassword;
	private String validAddress ;
	private String validHouseNumber;
	private String validCEP;
	private String validState;
	
	private User userInvalid;
	private User userWithValidName;
	
	
	@Before
	public void setUpValidUserData(){
		validName = "Jobson Lucas";
		
		validUsernameWithOnlyLetters = "lucaspk";
		validUsernameMixLettersAndNumbers = "lucaspk96";
		validUsernameWithUnderscore = "lucas_pk";
		validUsernameMixLettersNumbersSeparators = "lucaspk-96";
		
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
		
		invalidUsernameWithOnlyNumbers = "3151231531513135153531";
		invalidUsernameWithOnlySpaces = "               ";
		invalidUsernameWithOnlySpecialChars = "!@#$#%#$%&%&&*(*)--==++==*/-/+++";
		invalidUsernameWithSpace = "jobson Lucas";
		invalidUsernameWithGraphicAccentuation = "João";
	}
	
	@Test
	public void testCreatingUserWithSpecialCharsAndNumbersInUsername(){
		try {
			userWithValidName = new User(validName, validUsernameMixLettersNumbersSeparators, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithSpecialCharsInUsername(){
		try {
			userWithValidName = new User(validName, validUsernameWithUnderscore, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidUsernameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithNumbersInUsername(){
		try {
			userWithValidName = new User(validName, validUsernameMixLettersAndNumbers, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidUsernameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithGraphAccentuationInUsername(){
		try {
			userInvalid = new User(validName, invalidUsernameWithGraphicAccentuation, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
			
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidUsernameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithSpaceInUsername(){
		try {
			userInvalid = new User(validName, invalidUsernameWithSpace, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidUsernameException().getClass(), e.getClass());
			
		}
		
	}
	
	@Test
	public void testCreatingUserWithOnlySpacesAsUsername(){
		try {
			userInvalid = new User(validName, invalidUsernameWithOnlySpaces, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidUsernameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithOnlyNumbersAsUsername(){
		try {
			userInvalid = new User(validName, invalidUsernameWithOnlyNumbers, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidUsernameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithOnlySpecialCharsAsUsername() throws InvalidUserDataException{
		try {
			userInvalid = new User(validName, invalidUsernameWithOnlySpecialChars, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidUsernameException().getClass(), e.getClass());
		}
		
	}
	
	
	@Test
	public void testCreatingUserValidData(){
		try {
			userWithValidName = new User(validName, validUsernameWithOnlyLetters, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testCreatingUserWithGraphAccentuationInName(){
		try {
			userInvalid = new User(invalidNameWithGraphicAccentuation, validUsernameWithOnlyLetters, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithOnlyLettersInName(){
		try {
			userInvalid = new User(invalidNameWithOnlyLetters, validUsernameWithOnlyLetters, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithOnlyNumbersAsName(){
		try {
			userInvalid = new User(invalidNameWithOnlyNumbers, validUsernameWithOnlyLetters, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithOnlySpacesAsName(){
		try {
			userInvalid = new User(invalidNameWithOnlySpaces, validUsernameWithOnlyLetters, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithOnlySpecialCharAsName(){
		try {
			userInvalid = new User(invalidNameWithOnlySpecialChars, validUsernameWithOnlyLetters, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testCreatingUserWithSpecialCharAndNumberInName(){
		try {
			userInvalid = new User(invalidNameMixingLettersAndNumbersAndSpecialChars, validUsernameWithOnlyLetters, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithSpecialCharInName(){
		try {
			userInvalid = new User(invalidNameMixingLettersAndSpecialChars, validUsernameWithOnlyLetters, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
			
	@Test
	public void testCreatingUserWithSingleNumberInName(){
		try {
			userInvalid = new User(invalidNameMixingLettersAndOnlyOneNumber, validUsernameWithOnlyLetters, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithSeveralNumbersInName(){
		try {
			userInvalid = new User(invalidNameMixingLettersAndSeveralNumbers, validUsernameWithOnlyLetters, validMail, validPassword,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	


}
