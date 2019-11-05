package dad.javafx.dogs.client;

@SuppressWarnings("serial")
public class DogsServiceException extends Exception {

	public DogsServiceException() {
	}

	public DogsServiceException(String message) {
		super(message);
	}

	public DogsServiceException(Throwable cause) {
		super(cause);
	}

	public DogsServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public DogsServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
