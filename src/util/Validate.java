package util;

public final class Validate {

	private final static String LETTERS_AND_SPACE_ONLY_REGEX = "[a-z A-Z]";
	private final static String lOWERCASE_LETTERS_REGEX = "[a-z0-9_-]+";
	private final static String ONLY_NUMBERS_REGEX = "[0-9]+";
	private final static String ONLY_UNDERSCORES_AND_HIFEN_REGEX = "[0-9]+";
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
				|| !username.matches(lOWERCASE_LETTERS_REGEX)){
			isValid = false;
		}	
		return isValid;
	}
	
	
	
}
