package com.ifarma.ifarma.util;

import android.widget.EditText;

public final class Validate {

	private static final int CG_CEP_END = 58449999;
	private static final int CG_CEP_BEGIN = 58400000;
	private static final String NAME_REGEX = "[a-z A-Z]+";
	private static final String ONLY_NUM_REGEX = "[0-9]+";
	private static final String ONLY_LETTERS = "[a-zA-Z]+";
	private static final String AT_SYMBOL = "@";
	private static final String SPACE_STRING = " ";
	private static final int MAX_CHARS_DESCRIP = 50;
	private static final int MAX_CHARS_PRODUCTNAME = 30;
	private static final int[] PESOCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	private static final int[] PESOCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
	
	private Validate(){}
	
	/**Name is valid if not contains number or special chars,
	 * except for space.*/
	public static boolean isValidName(final String name) {
		boolean isValid = true;
		if(name == null || name.trim().isEmpty() ){
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
		String answer = "";
		if(data != null && !data.trim().isEmpty()){
			answer = data.substring(0,1);
		}
		return answer;
	}

	/** Email is valid if has at(@) symbol.*/
	public static boolean isValidEmail(final String email) {
		boolean isValid = false;
		if(email != null && !email.trim().isEmpty()){
			isValid = email.contains(AT_SYMBOL); // NOPMD by Lucas on 17/02/17 18:48
		}
		return isValid;
	}

	/** Address is valid if isn't empty or null.*/
	public static boolean isValidAddress(final String address) {
		boolean isValid = true; // NOPMD by Lucas on 17/02/17 18:47
		if(address == null || address.trim().isEmpty()){
			isValid = false;
		}
		return isValid;
	}
	
	/** CEP is valid if it's from Campina Grande city. */
	public static boolean isValidCEP(final String cep) {
		boolean isValid = false; // NOPMD by Lucas on 17/02/17 18:47
		if(cep != null && !cep.trim().isEmpty() && cep.matches(ONLY_NUM_REGEX) && cep.length() == 8){
			final int cepAsNumber = Integer.parseInt(cep);
			if(cepAsNumber >= CG_CEP_BEGIN && cepAsNumber <= CG_CEP_END){
				isValid = true;
			}
		}
		return isValid;
	}

	/** A house number is valid if starts with a number. */
	public static boolean isValidHouseNumber(final String houseNumber) {
		boolean isValid = true; // NOPMD by Lucas on 17/02/17 18:47
		final String houseNumFirstChar = getFirstCharAsStringOf(houseNumber);
		if(!houseNumFirstChar.matches(ONLY_NUM_REGEX)){  // NOPMD by Lucas on 17/02/17 18:48
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

	/** Check if certain CPF is valid according to how this number is constructed in Brazil.
	 * For example, has exactly 11 characters.*/
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

	/** Check if certain CNPJ is valid according to how this number is constructed in Brazil.
	 * For example, has exactly 14 characters.*/
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
	public static boolean isValidMedicine(EditText _nameProductInput, EditText _priceProductInput,
										  EditText _labProductInput, EditText _descriptionProductInput){

		boolean valid = true;
		String name = _nameProductInput.getText().toString();
		String price = _priceProductInput.getText().toString();
		String lab = _labProductInput.getText().toString();
		String description = _descriptionProductInput.getText().toString();

		if (!isValidProductName(name)) {
			_nameProductInput.setError("Nome inválido.");
			valid = false;
		} else {
			_nameProductInput.setError(null);
		}

		if (price.isEmpty()) {
			_priceProductInput.setError("Preço inválido.");
			valid = false;
		} else {
			_priceProductInput.setError(null);
		}

		if (!isValidProductLab(lab)) {
			_labProductInput.setError("Laboratório inválido.");
			valid = false;
		} else {
			_labProductInput.setError(null);
		}

		if (!isValidProductDescription(description)) {
			_descriptionProductInput.setError("Descrição inválida.");
			valid = false;
		} else {
			_descriptionProductInput.setError(null);
		}

		return valid;
	}

    /**A product name is valid if isn't empty or null and has no more than 30 chars.*/
	public static boolean isValidProductName(final String productName){
		boolean isValid = true;
		if(productName == null ||
				productName.trim().isEmpty() ||
				productName.length() > MAX_CHARS_PRODUCTNAME){
			isValid = false;
		}
		return isValid;
	}

    /**A description is valid if isn't empty or null and has no more than 50 chars.*/
	public static boolean isValidProductDescription(final String newDescription) {
		boolean isValid = true;
		if(newDescription == null ||
				newDescription.trim().isEmpty() ||
				newDescription.length() > MAX_CHARS_DESCRIP){
			isValid = false;
		}
		return isValid;
	}

    /**A lab name is valid if isn't empty or null.*/
	public static boolean isValidProductLab(final String newLab) {
		boolean isValid = true;
		if(newLab == null || newLab.trim().isEmpty()){
			isValid = false;
		}
		return isValid;
	}

	public static boolean isValidPharmaID(final String id) {
		boolean isValid = true;
		if(id == null || id.trim().isEmpty()){
			isValid = false;
		}
		return isValid;
	}
}
