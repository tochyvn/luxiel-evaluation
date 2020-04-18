package lu.luxiel.luxielevaluation.exception;

public class AlreadyExistEntityException extends Exception {

	private static final long serialVersionUID = -8421561705323610810L;

	public AlreadyExistEntityException() {
		super();
	}

	public AlreadyExistEntityException(String message, Throwable cause) {
		super(message, cause);
	}

	public AlreadyExistEntityException(String message) {
		super(message);
	}

}
