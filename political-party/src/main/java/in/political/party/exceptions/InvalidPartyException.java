package in.political.party.exceptions;

@SuppressWarnings("serial")
public class InvalidPartyException extends RuntimeException{

	public InvalidPartyException(String message) {
		super(message);
	}

}
