package ineat.exception;

/**
 * Classe d'exception de conversion 
 */
public class ConversionException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ConversionException() {
	    super();
	}

	public ConversionException(String message) {
		super(message);
	}
	
}
