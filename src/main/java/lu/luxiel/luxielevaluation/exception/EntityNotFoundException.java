package lu.luxiel.luxielevaluation.exception;

public class EntityNotFoundException extends Exception {

	private static final long serialVersionUID = -8421561705323610810L;

	public EntityNotFoundException() {
		super();
	}

	public EntityNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityNotFoundException(String message) {
		super(message);
	}

}
