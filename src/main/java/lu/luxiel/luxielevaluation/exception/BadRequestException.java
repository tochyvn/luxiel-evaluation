package lu.luxiel.luxielevaluation.exception;

public class BadRequestException extends Exception {

	private static final long serialVersionUID = -8421561705323610810L;

	public BadRequestException() {
		super();
	}

	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadRequestException(String message) {
		super(message);
	}

}
