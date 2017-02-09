package tests;

import org.junit.*;

import exceptions.InvalidNameException;
import exceptions.InvalidPasswordException;
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
	
	
	private String invalidPasswordOnlyNumbers;
	private String invalidPasswordWithOnlySpaces;
	private String invalidPasswordLengthInferiorToEight;
	private String validPasswordNumbersAndLetters;
	private String validPasswordNumbersAndSpecialChars;
	private String validPasswordLettersAndSpecialChars;
	private String validPasswordWithLettersAndSpecialCharsAndNumbers;
	
	private String validMail;
	
	private String validAddress ;
	private String validHouseNumber;
	private String validCEP;
	private String validState;
	
	private User userInvalid;
	private User userValid;
	
	
	
	@Before
	public void setUpValidUserData(){
		validName = "Jobson Lucas";
		
		validUsernameWithOnlyLetters = "lucaspk";
		validUsernameMixLettersAndNumbers = "lucaspk96";
		validUsernameWithUnderscore = "lucas_pk";
		validUsernameMixLettersNumbersSeparators = "lucaspk-96";
				
		validPasswordNumbersAndLetters = "6xablau9";
		validPasswordNumbersAndSpecialChars = "564864@#";
		validPasswordLettersAndSpecialChars = "lucas%&*";
		validPasswordWithLettersAndSpecialCharsAndNumbers = "lucas%9621&";
				
		validAddress = "Rua das mulatas saradas";
		
		validMail = "lucas@gmail.com";
		
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
		
		invalidPasswordOnlyNumbers = "46644684";
		invalidPasswordLengthInferiorToEight = "luc96$%";
		invalidPasswordWithOnlySpaces = "               ";
	}
	
	@Test
	public void testCreatingUserWithOnlyNumbersAsPassword(){
		try {
			userInvalid = new User(validName, validUsernameWithOnlyLetters, validMail, 
					invalidPasswordOnlyNumbers,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithPasswordWithOnlySpaces(){
		try {
			userInvalid = new User(validName, validUsernameWithOnlyLetters, validMail, 
					invalidPasswordWithOnlySpaces,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithPasswordLengthInferiorToEight(){
		try {
			userInvalid = new User(validName, validUsernameWithOnlyLetters, validMail, 
					invalidPasswordLengthInferiorToEight,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testCreatingUserWithNumbersAndLettersAndSpecialCharsAsPassword(){
		try {
			userValid = new User(validName, validUsernameWithOnlyLetters, validMail, 
					validPasswordWithLettersAndSpecialCharsAndNumbers,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testCreatingUserWithNumbersAndLettersAsPassword(){
		try {
			userValid = new User(validName, validUsernameWithOnlyLetters, validMail, 
					validPasswordNumbersAndLetters,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithSpecialCharsAndLettersAsPassword(){
		try {
			userValid = new User(validName, validUsernameWithOnlyLetters, validMail, 
					validPasswordLettersAndSpecialChars,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithSpecialCharsAndNumbersAsPassword(){
		try {
			userValid = new User(validName, validUsernameWithOnlyLetters, validMail, 
					validPasswordNumbersAndSpecialChars,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithSpecialCharsAndNumbersInUsername(){
		try {
			userValid = new User(validName, validUsernameMixLettersNumbersSeparators, validMail, validPasswordNumbersAndLetters,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithSpecialCharsInUsername(){
		try {
			userValid = new User(validName, validUsernameWithUnderscore, validMail, validPasswordNumbersAndLetters,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidUsernameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithNumbersInUsername(){
		try {
			userValid = new User(validName, validUsernameMixLettersAndNumbers, validMail, validPasswordNumbersAndLetters,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidUsernameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithGraphAccentuationInUsername(){
		try {
			userInvalid = new User(validName, invalidUsernameWithGraphicAccentuation, validMail, validPasswordNumbersAndLetters,
					validAddress, validHouseNumber, validCEP, validState);
			
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidUsernameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithSpaceInUsername(){
		try {
			userInvalid = new User(validName, invalidUsernameWithSpace, validMail, validPasswordNumbersAndLetters,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidUsernameException().getClass(), e.getClass());
			
		}
		
	}
	
	@Test
	public void testCreatingUserWithOnlySpacesAsUsername(){
		try {
			userInvalid = new User(validName, invalidUsernameWithOnlySpaces, validMail, validPasswordNumbersAndLetters,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidUsernameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithOnlyNumbersAsUsername(){
		try {
			userInvalid = new User(validName, invalidUsernameWithOnlyNumbers, validMail, validPasswordNumbersAndLetters,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidUsernameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithOnlySpecialCharsAsUsername() throws InvalidUserDataException{
		try {
			userInvalid = new User(validName, invalidUsernameWithOnlySpecialChars, validMail, validPasswordNumbersAndLetters,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidUsernameException().getClass(), e.getClass());
		}
		
	}
	
	
	@Test
	public void testCreatingUserValidData(){
		try {
			userValid = new User(validName, validUsernameWithOnlyLetters, validMail, validPasswordNumbersAndLetters,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testCreatingUserWithGraphAccentuationInName(){
		try {
			userInvalid = new User(invalidNameWithGraphicAccentuation, validUsernameWithOnlyLetters, validMail, validPasswordNumbersAndLetters,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithOnlyLettersInName(){
		try {
			userInvalid = new User(invalidNameWithOnlyLetters, validUsernameWithOnlyLetters, validMail, validPasswordNumbersAndLetters,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithOnlyNumbersAsName(){
		try {
			userInvalid = new User(invalidNameWithOnlyNumbers, validUsernameWithOnlyLetters, validMail, validPasswordNumbersAndLetters,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithOnlySpacesAsName(){
		try {
			userInvalid = new User(invalidNameWithOnlySpaces, validUsernameWithOnlyLetters, validMail, validPasswordNumbersAndLetters,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithOnlySpecialCharAsName(){
		try {
			userInvalid = new User(invalidNameWithOnlySpecialChars, validUsernameWithOnlyLetters, validMail, validPasswordNumbersAndLetters,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testCreatingUserWithSpecialCharAndNumberInName(){
		try {
			userInvalid = new User(invalidNameMixingLettersAndNumbersAndSpecialChars, validUsernameWithOnlyLetters, validMail, validPasswordNumbersAndLetters,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithSpecialCharInName(){
		try {
			userInvalid = new User(invalidNameMixingLettersAndSpecialChars, validUsernameWithOnlyLetters, validMail, validPasswordNumbersAndLetters,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
			
	@Test
	public void testCreatingUserWithSingleNumberInName(){
		try {
			userInvalid = new User(invalidNameMixingLettersAndOnlyOneNumber, validUsernameWithOnlyLetters, validMail, validPasswordNumbersAndLetters,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithSeveralNumbersInName(){
		try {
			userInvalid = new User(invalidNameMixingLettersAndSeveralNumbers, validUsernameWithOnlyLetters, validMail, validPasswordNumbersAndLetters,
					validAddress, validHouseNumber, validCEP, validState);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	


}
