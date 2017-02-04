package util;

public final class Validate {

	private final static String LETTERS_AND_SPACE_ONLY_REGEX = "[a-z A-Z]";
	
	private Validate(){}
		
	public static boolean isValidName(final String name) {
		boolean isValid = true;
		final boolean nameIsEmpty = name.trim().isEmpty();
		if(nameIsEmpty && !name.matches(LETTERS_AND_SPACE_ONLY_REGEX)){
			isValid = false;
		}	
		return isValid;
	}
	
	
	
}
