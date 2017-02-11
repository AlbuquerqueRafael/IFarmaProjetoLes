package util;

public final class Validate {

	private final static String NAME_REGEX = "[a-z A-Z]";
	private final static String USERNAME_REGEX = "[a-z0-9_-]+";
	private final static String ONLY_NUM_REGEX = "[0-9]+";
	private final static String ONLY_UNDERSCORES_AND_HIFEN = "[_-]+";
	private final static String VALID_CHARS_BEFORE_AT = "[0-9a-zA-Z-._]+";
	private final static String ONLY_LETTERS = "[a-zA-Z]+";
	private final static String ADDRESS_REGEX = "[a-z A-Z-.]+";
	private final static String AT_SYMBOL = "@";
	private final static String SPACE_STRING = " ";
	
	private Validate(){}
		
	public static boolean isValidName(final String name) {
		boolean isValid = true;
		final String firstCharOfName = getFirstCharAsStringOf(name);
		if(!firstCharOfName.matches(ONLY_LETTERS) 
				&& !name.matches(NAME_REGEX)){
			isValid = false;
		}	
		return isValid;
	}

	public static boolean isValidUsername(final String username) {
		boolean isValid = true;
		final String usrnamefirstChar = getFirstCharAsStringOf(username);
		if(!usrnamefirstChar.matches(ONLY_LETTERS) 
				|| username.matches(ONLY_NUM_REGEX) 
				|| username.matches(ONLY_UNDERSCORES_AND_HIFEN)
				|| !username.matches(USERNAME_REGEX)){
			isValid = false;
		}	
		return isValid;
	}

	public static boolean isValidPassword(final String password) {
		boolean isValid = true;
		final String passwdfirstChar = getFirstCharAsStringOf(password);
		if(passwdfirstChar.matches("") 
				|| password.length() < 8
				|| password.matches(ONLY_NUM_REGEX) 
				|| passwdfirstChar.matches(" ")){
			isValid = false;
		}	
		return isValid;
	}

	private static String getFirstCharAsStringOf(final String data){
		return data.substring(0,1);
	}
	
	private static String getPartBeforeAtSymbol(final String email){
		final String[] emailSplitted = email.split(AT_SYMBOL);
		return emailSplitted[0];
	}
	
	public static boolean isValidEmail(final String email) {
		final String firstCharOfEmail = getFirstCharAsStringOf(email);
		boolean isValid = email.contains(AT_SYMBOL) && firstCharOfEmail.matches(ONLY_LETTERS);
		if(isValid == true){
			final String firstPartOfEmail = getPartBeforeAtSymbol(email);
			isValid = firstPartOfEmail.matches(VALID_CHARS_BEFORE_AT);
		}
		return isValid;
	}

	public static boolean isValidAddress(final String address) {
		boolean isValid = true;
		final String addressFirstChar = getFirstCharAsStringOf(address);
		if(address.matches(ONLY_NUM_REGEX) || 
				!addressFirstChar.matches(ONLY_LETTERS) ||
				!address.contains(SPACE_STRING) ||
				!address.matches(ADDRESS_REGEX)){
			isValid = false;
		}
		return isValid;
	}
}
