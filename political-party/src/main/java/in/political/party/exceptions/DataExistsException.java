package in.political.party.exceptions;

public class DataExistsException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DataExistsException(String message) {
		super(message);
	}

}
