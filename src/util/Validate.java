package util;

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
		boolean isValid = true; // NOPMD by Lucas on 17/02/17 18:47
		final String firstCharOfName = getFirstCharAsStringOf(name);
		if(!firstCharOfName.matches(ONLY_LETTERS)  // NOPMD by Lucas on 17/02/17 18:48
				|| !name.matches(NAME_REGEX)){
			isValid = false;
		}	
		return isValid;
	}
	
	/** Password is valid if has, at least, 8 chars; 
	 * has not space char and has not only numbers.
	 *  */
	public static boolean isValidPassword(final String password) {
		boolean isValid = true; // NOPMD by Lucas on 17/02/17 18:46
		final String passwdfirstChar = getFirstCharAsStringOf(password);
		if(passwdfirstChar.matches("")  // NOPMD by Lucas on 17/02/17 18:48
				|| password.length() < 8
				|| password.matches(ONLY_NUM_REGEX) 
				|| passwdfirstChar.matches(" ")){ // NOPMD by Lucas on 17/02/17 18:49
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
	
	/** Email is valid if has at(@) symbol, 
	 * start with a letter and has not special symbols,
	 * except for underscore, dot and hyphen, because 
	 * they are used as separator.*/
	public static boolean isValidEmail(final String email) {
		final String firstCharOfEmail = getFirstCharAsStringOf(email);
		boolean isValid = email.contains(AT_SYMBOL) && firstCharOfEmail.matches(ONLY_LETTERS);
		if(isValid){
			final String firstPartOfEmail = getPartBeforeAtSymbol(email);
			isValid = firstPartOfEmail.matches(VALID_CHARS_BEFORE_AT); // NOPMD by Lucas on 17/02/17 18:48
		}
		return isValid;
	}

	/** Address is valid if starts with a letter, 
	 * has space because of street's name and 
	 * has not special chars, except for hyphen and dot.
	 * */
	public static boolean isValidAddress(final String address) {
		boolean isValid = true; // NOPMD by Lucas on 17/02/17 18:47
		final String addressFirstChar = getFirstCharAsStringOf(address);
		if(address.matches(ONLY_NUM_REGEX) || 
				!addressFirstChar.matches(ONLY_LETTERS) || // NOPMD by Lucas on 17/02/17 18:48
				!address.contains(SPACE_STRING) ||
				!address.matches(ADDRESS_REGEX)){
			isValid = false;
		}
		return isValid;
	}
	
	/** CEP is valid if it's from Campina Grande city. */
	public static boolean isValidCEP(final String cep) {
		boolean isValid = false; // NOPMD by Lucas on 17/02/17 18:47
		if(cep.matches(ONLY_NUM_REGEX) && cep.length() == 8){
			final int cepAsNumber = Integer.parseInt(cep);
			if(cepAsNumber >= CG_CEP_BEGIN && cepAsNumber <= CG_CEP_END){
				isValid = true;
			}
		}
		return isValid;
	}

	public static boolean isValidHouseNumber(final String houseNumber) {
		boolean isValid = true; // NOPMD by Lucas on 17/02/17 18:47
		final String houseNumFirstChar = getFirstCharAsStringOf(houseNumber);
		if(!houseNumFirstChar.matches(ONLY_NUM_REGEX) || // NOPMD by Lucas on 17/02/17 18:48
				!houseNumber.matches(HOUSE_NUM_REGEX)){
			isValid = false;
		}
		return isValid;
	}

   private static int calcDigit(final String str, final int... peso) {
      int soma = 0; // NOPMD by Lucas on 17/02/17 18:47
      int digit = 0; // NOPMD by Lucas on 17/02/17 18:47
      for (int index=str.length()-1; index >= 0; index-- ) {
         digit = Integer.parseInt(str.substring(index,index+1));
         soma += digit*peso[peso.length-str.length()+index];
      }
      soma = 11 - soma % 11;
      return soma > 9 ? 0 : soma;
   }

   public static boolean isValidCPF(final String cpf) {
	  boolean answer = true; // NOPMD by Lucas on 17/02/17 18:47
      if (cpf==null || cpf.length() != 11){
    	  answer = false;
      }
      else{
	      final Integer digit1 = calcDigit(cpf.substring(0,9), PESOCPF);
	      final Integer digit2 = calcDigit(cpf.substring(0,9) + digit1, PESOCPF);
	      answer = cpf.equals(cpf.substring(0,9) + digit1.toString() + digit2.toString());
	  }
      return answer;
   }

   public static boolean isValidCNPJ(final String cnpj) {
	  boolean answer = true; // NOPMD by Lucas on 17/02/17 18:47
      if (cnpj==null || cnpj.length()!=14){ 
    	  answer = false;
      }
      else{
	      final Integer digit1 = calcDigit(cnpj.substring(0,12), PESOCNPJ);
	      final Integer digit2 = calcDigit(cnpj.substring(0,12) + digit1, PESOCNPJ);
	      answer = cnpj.equals(cnpj.substring(0,12) + digit1.toString() + digit2.toString());
	  }
      return answer;
   }
		

}
