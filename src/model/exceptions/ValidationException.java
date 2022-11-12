package model.exceptions;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementação de uma exceção personalizada que vai carregar uma coleção contendotodos os erros possíveis nos campos.
 *
 */
public class ValidationException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private Map<String, String> errors = new HashMap<>();private Object object;
	
	public ValidationException(String msg) {
		super(msg);
	}
	
	public Map<String, String> getErrors(){
		return errors;
	}
	
	public void addError(String fieldName, String errorMessage) {
		errors.put(fieldName, errorMessage);
	}
}
