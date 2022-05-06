package in.political.party.exceptions;

@SuppressWarnings("serial")
public class LeaderIdNotFoundException extends RuntimeException{

	
public LeaderIdNotFoundException(String message) {
	super (message);
}
}
