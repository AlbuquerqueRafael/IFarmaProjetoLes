package com.ifarma.ifarma.util;

public final class Validate {

	private static final int CG_CEP_END = 58449999;
	private static final int CG_CEP_BEGIN = 58400000;
	private final static String NAME_REGEX = "[a-z A-Z]+";
	private final static String ONLY_NUM_REGEX = "[0-9]+";
	private final static String HOUSE_NUM_REGEX = "[a-z0-9A-Z-]+";
	private final static String VALID_CHARS_BEFORE_AT = "[0-9a-zA-Z-._]+";
	private final static String ONLY_LETTERS = "[a-zA-Z]+";
	private final static String ADDRESS_REGEX = "[a-z A-Z-.]+";
	private final static String AT_SYMBOL = "@";
	private final static String SPACE_STRING = " ";
	
	private static final int[] PESOCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	private static final int[] PESOCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
	
	private Validate(){}
	
	/**Name is valid if not contains number or special chars,
	 * except for space.*/
	public static boolean isValidName(final String name) {
		return true;
	}
	
	/** Password is valid if has, at least, 8 chars; 
	 * has not space char and has not only numbers.
	 *  */
	public static boolean isValidPassword(final String password) {
		return true;
	}
	
	/** Email is valid if has at(@) symbol, 
	 * start with a letter and has not special symbols,
	 * except for underscore, dot and hyphen, because 
	 * they are used as separator.*/
	public static boolean isValidEmail(final String email) {
		return true;
	}

	/** Address is valid if starts with a letter, 
	 * has space because of street's name and 
	 * has not special chars, except for hyphen and dot.
	 * */
	public static boolean isValidAddress(final String address) {
		return true;
	}
	
	/** CEP is valid if it's from Campina Grande city. */
	public static boolean isValidCEP(final String cep) {
		return true;
	}

	public static boolean isValidHouseNumber(final String houseNumber) {
		return true;
	}

   private static int calcDigit(final String str, final int... peso) {
	   return 0;
   }

   public static boolean isValidCPF(final String cpf) {
	   return true;
   }

   public static boolean isValidCNPJ(final String cnpj) {
	   return true;
   }
		

}
