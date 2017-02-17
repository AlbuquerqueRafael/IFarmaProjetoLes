package tests;

import org.junit.*;

import exceptions.InvalidAddressException;
import exceptions.InvalidCEPException;
import exceptions.InvalidEmailException;
import exceptions.InvalidHouseNumberException;
import exceptions.InvalidNameException;
import exceptions.InvalidPasswordException;
import exceptions.InvalidUserDataException;
import model.User;

public class UserTests {
		// os itens que são comuns, como por exemplo, onlyNumbers e onlySpaces podem ser apenas
		// uma variável, para evitar duplicação de código, mas isso fica para depois(para o refactoring dos testes)
		
		// Retirar atributo state, já que a cidade é campina Grande, então será, por default, PB
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
	
	private User userInvalid;
	private User userValid;
	
	
	
	
	
	@Before
	public void setUpValidUserData(){
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
		invalidNameWithGraphicAccentuation = "João Victor Mafra Bói";
		invalidNameWithOnlyNumbers = "1651561681818616818161516156";
		invalidNameWithOnlySpecialChars = "!bs@#      ¬¢*$   |)/%£_=+§{}ºª°?/;:<>¹²³";
		invalidNameWithOnlySpaces = "                           ";
		invalidNameMixingLettersAndSeveralNumbers = "Jobson54654 Lucas245541561";
		invalidNameMixingLettersAndOnlyOneNumber = "Jobson Lucas2";
		invalidNameMixingLettersAndSpecialChars = "Job$@# ¬u¢@! !@#sdf#WER@xcv$%$¨¨¨&**/-*";
		invalidNameMixingLettersAndNumbersAndSpecialChars = "J156651o51b$@# 156156¬u¢@!asdqwe2 !@#sdf#WER@xcv$%$¨¨¨&**/-*";
				
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
	public void testShouldCreateUserHouseNumberContainsHyphen(){
		try {
			userValid = new User(validName, 
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
	public void testShouldFailCreatingUserHouseNumberWithSpecialChars(){
		try {
			userInvalid = new User(validName, 
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
	public void testShouldFailCreatingUserHouseNumberWithOnlySpaces(){
		try {
			userInvalid = new User(validName, 
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
	public void testShouldFailCreatingUserHouseNumberWithOnlyLetters(){
		try {
			userInvalid = new User(validName, 
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
	public void testShouldFailCreatingUserHouseNumberWithFirstCharNotNumerical(){
		try {
			userInvalid = new User(validName, 
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
	public void testShouldCreateUserCEPOfCampinaGrande(){
		try {
			userValid = new User(validName, 
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
	public void testShouldFailCreatingUserCEPFromNotCampinaGrande(){
		try {
			userInvalid = new User(validName, 
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
	public void testShouldFailCreatingUserCEPLengthSuperiorToEight(){
		try {
			userInvalid = new User(validName, 
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
	public void testShouldFailCreatingUserCEPLengthInferiorToEight(){
		try {
			userInvalid = new User(validName, 
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
	public void testShouldFailCreatingUserCEPWithSpecialChars(){
		try {
			userInvalid = new User(validName, 
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
	public void testShouldFailCreatingUserCEPWithLetter(){
		try {
			userInvalid = new User(validName, 
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
	public void testShouldFailCreatingUserAddressWithoutLetterAsFirstChar(){
		try {
			userInvalid = new User(validName, validMailWithAtLeastOneLetterBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					invalidAddressWithoutLetterAsFirstChar,
					validHouseNumber, 
					validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidAddressException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingUserAddressWithOnlySpaces(){
		try {
			userInvalid = new User(validName, validMailWithAtLeastOneLetterBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					invalidAddressWithOnlySpaces,
					validHouseNumber, 
					validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidAddressException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testShouldFailCreatingUserAddressWithoutSpaces(){
		try {
			userInvalid = new User(validName, validMailWithAtLeastOneLetterBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					invalidAddressWithoutSpaces,
					validHouseNumber, 
					validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidAddressException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testShouldFailCreatingUserAddressWithOnlyNumbers(){
		try {
			userInvalid = new User(validName, validMailWithAtLeastOneLetterBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					invalidAddressWithOnlyNumbers,
					validHouseNumber, 
					validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidAddressException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingUserAddressWithSpecialSymbols(){
		try {
			userInvalid = new User(validName, validMailWithAtLeastOneLetterBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					invalidAddressWithSpecialSymbols,
					validHouseNumber, 
					validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidAddressException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testShouldCreateUserEmailWithAtLeastOneLetterBeforeAtSymbol(){
		try {
			userValid = new User(validName, validMailWithAtLeastOneLetterBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldCreateUserEmailWithHyphenBeforeAtSymbol(){
		try {
			userValid = new User(validName, validMailWithHyphenBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldCreateUserEmailWithDotBeforeAtSymbol(){
		try {
			userValid = new User(validName, validMailWithDotBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldCreateUserEmailWithUnderscoreBeforeAtSymbol(){
		try {
			userValid = new User(validName, validMailWithUnderscoreBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingUserEmailWithSpaceBeforeAtSymbol(){
		try {
			userInvalid = new User(validName, invalidMailWithSpace,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingUserEmailWithSpecialCharsBeforeAtSymbol(){
		try {
			userInvalid = new User(validName, invalidMailWithInvalidSpecialCharsBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingUserEmailWithOnlySpaces(){
		try {
			userInvalid = new User(validName, invalidMailWithOnlySpaces,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingUserEmailWithOnlySpacesBeforeAtSymbol(){
		try {
			userInvalid = new User(validName, invalidMailWithOnlySpacesBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingUserEmailWithOnlySpecialCharsBeforeAtSymbol(){
		try {
			userInvalid = new User(validName, invalidMailWithOnlySpecialCharsBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingUserEmailWithOnlyNumbersBeforeAtSymbol(){
		try {
			userInvalid = new User(validName, invalidMailWithOnlyNumbersBeforeAtSymbol,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingUserEmailWithHyphenAsFirstChar(){
		try {
			userInvalid = new User(validName, invalidMailStartingWithHyphen,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testShouldFailCreatingUserEmailWithUnderscoreAsFirstChar(){
		try {
			userInvalid = new User(validName, invalidMailStartingWithUnderscore,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testShouldFailCreatingUserEmailWithDotAsFirstChar(){
		try {
			userInvalid = new User(validName, invalidMailStartingWithDot,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testShouldFailCreatingUserEmailWithoutAtSymbol(){
		try {
			userInvalid = new User(validName, invalidMailWithoutAtSymbol,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testShouldFailCreatingUserEmailWithNumberAsFirstChar(){
		try {
			userInvalid = new User(validName, invalidMailStartingWithNumber,
					validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidEmailException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testCreatingUserWithOnlyNumbersAsPassword(){
		try {
			userInvalid = new User(validName, validMail, invalidPasswordOnlyNumbers, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithPasswordWithOnlySpaces(){
		try {
			userInvalid = new User(validName, validMail, invalidPasswordWithOnlySpaces, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithPasswordLengthInferiorToEight(){
		try {
			userInvalid = new User(validName, validMail, invalidPasswordLengthInferiorToEight, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testCreatingUserWithNumbersAndLettersAndSpecialCharsAsPassword(){
		try {
			userValid = new User(validName, validMail, validPasswordWithLettersAndSpecialCharsAndNumbers, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testCreatingUserWithNumbersAndLettersAsPassword(){
		try {
			userValid = new User(validName, validMail, validPasswordNumbersAndLetters, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithSpecialCharsAndLettersAsPassword(){
		try {
			userValid = new User(validName, validMail, validPasswordLettersAndSpecialChars, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithSpecialCharsAndNumbersAsPassword(){
		try {
			userValid = new User(validName, validMail, validPasswordNumbersAndSpecialChars, 
					validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidPasswordException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserValidData(){
		try {
			userValid = new User(validName, validMail, validPasswordNumbersAndLetters, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testCreatingUserWithGraphAccentuationInName(){
		try {
			userInvalid = new User(invalidNameWithGraphicAccentuation, validMail, validPasswordNumbersAndLetters, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithOnlyLettersInName(){
		try {
			userInvalid = new User(invalidNameWithOnlyLetters, validMail, validPasswordNumbersAndLetters, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithOnlyNumbersAsName(){
		try {
			userInvalid = new User(invalidNameWithOnlyNumbers, validMail, validPasswordNumbersAndLetters, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithOnlySpacesAsName(){
		try {
			userInvalid = new User(invalidNameWithOnlySpaces, validMail, validPasswordNumbersAndLetters, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithOnlySpecialCharAsName(){
		try {
			userInvalid = new User(invalidNameWithOnlySpecialChars, validMail, validPasswordNumbersAndLetters, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	
	@Test
	public void testCreatingUserWithSpecialCharAndNumberInName(){
		try {
			userInvalid = new User(invalidNameMixingLettersAndNumbersAndSpecialChars, validMail, validPasswordNumbersAndLetters, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithSpecialCharInName(){
		try {
			userInvalid = new User(invalidNameMixingLettersAndSpecialChars, validMail, validPasswordNumbersAndLetters, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
			
	@Test
	public void testCreatingUserWithSingleNumberInName(){
		try {
			userInvalid = new User(invalidNameMixingLettersAndOnlyOneNumber, validMail, validPasswordNumbersAndLetters, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	
	@Test
	public void testCreatingUserWithSeveralNumbersInName(){
		try {
			userInvalid = new User(invalidNameMixingLettersAndSeveralNumbers, validMail, validPasswordNumbersAndLetters, validAddress,
					validHouseNumber, validCEPOfCampinaGrande);
		} catch (InvalidUserDataException e) {
			Assert.assertEquals(new InvalidNameException().getMessage(), e.getMessage());
		}
		
	}
	


}
