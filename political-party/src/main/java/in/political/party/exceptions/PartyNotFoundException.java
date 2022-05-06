package in.political.party.exceptions;

public class PartyNotFoundException extends RuntimeException {
	
	public PartyNotFoundException(String message) {
		super(message);
	}

}
