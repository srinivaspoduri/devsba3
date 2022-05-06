package in.political.party.exceptions;

@SuppressWarnings("serial")
public class PartyNotFoundException extends RuntimeException {
	
	public PartyNotFoundException(String message) {
		super(message);
	}

}
