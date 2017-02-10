package util;

public final class Validate {

	private final static String LETTERS_AND_SPACE_ONLY_REGEX = "[a-z A-Z]";
	private final static String LOWERCASE_LETTERS_REGEX = "[a-z0-9_-]+";
	private final static String ONLY_NUMBERS_REGEX = "[0-9]+";
	private final static String ONLY_UNDERSCORES_AND_HIFEN_REGEX = "[_-]+";
	private final static String EXCEPT_LETTERS_REGEX = "[^a-zA-Z]+";
	private final static String FIRST_PART_EMAIL_REGEX = "[0-9a-zA-Z-._]+";
	private final static String AT_SYMBOL = "@";
	
	private Validate(){}
		
	public static boolean isValidName(final String name) {
		boolean isValid = true;
		final boolean nameIsEmpty = name.trim().isEmpty();
		if(nameIsEmpty && !name.matches(LETTERS_AND_SPACE_ONLY_REGEX)){
			isValid = false;
		}	
		return isValid;
	}

	public static boolean isValidUsername(final String username) {
		boolean isValid = true;
		final boolean usernameIsEmpty = username.trim().isEmpty();
		if(usernameIsEmpty || username.matches(ONLY_NUMBERS_REGEX) 
				|| username.matches(ONLY_UNDERSCORES_AND_HIFEN_REGEX)
				|| !username.matches(LOWERCASE_LETTERS_REGEX)){
			isValid = false;
		}	
		return isValid;
	}

	public static boolean isValidPassword(final String password) {
		boolean isValid = true;
		final boolean passwordIsEmpty = password.trim().isEmpty();
		if(passwordIsEmpty || password.matches(ONLY_NUMBERS_REGEX) 
				|| password.length() < 8){
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
		boolean isValid = true;
		if(!email.contains(AT_SYMBOL) 
				|| firstCharOfEmail.matches(EXCEPT_LETTERS_REGEX)){
			isValid = false;
		}
		else{
			final String firstPartOfEmail = getPartBeforeAtSymbol(email);
			if(firstPartOfEmail.matches(FIRST_PART_EMAIL_REGEX)){
				isValid = true;
			}
			else{
				isValid = false;
			}
		}
		return isValid;
	}
}
