package lu.luxiel.luxielevaluation.exception;

public class InitializationRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -1715970534282140771L;
	
	private final static String MESSAGE = "Une erreur est survenue lors de l'initialisation du jeu de données";

	public InitializationRuntimeException() {
		super();
	}

	public InitializationRuntimeException(Throwable cause) {
		super(MESSAGE, cause);
	}

	public InitializationRuntimeException(String message) {
		super(MESSAGE);
	}
	
}
