package in.political.party.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(value=InvalidPartyException.class)
	public ResponseEntity<ApiError> handleInvalidPartyException(InvalidPartyException invalidPartyException)
	{
		String expmsg = invalidPartyException.getMessage();
		String expcode = "400";
		ApiError error = new ApiError();
		error.setCode(expcode);
		error.setMsg(expmsg);
		return ResponseEntity.badRequest().body(error);
	}


	@ExceptionHandler(value=LeaderIdNotFoundException.class)
	public ResponseEntity<ApiError> handleLeaderIdNotFoundException(LeaderIdNotFoundException leaderIdNotFoundException)
	{
		String expmsg = leaderIdNotFoundException.getMessage();
		String expcode = "400";
		ApiError error = new ApiError();
		error.setCode(expcode);
		error.setMsg(expmsg);

		return ResponseEntity.badRequest().body(error);

	}


	@ExceptionHandler(value=PartyNotFoundException.class)
	public ResponseEntity<ApiError> handlePartyNotFoundException(PartyNotFoundException partyNotFoundException)
	{
		String expmsg = partyNotFoundException.getMessage();
		String expcode = "400";
		ApiError error = new ApiError();
		error.setCode(expcode);
		error.setMsg(expmsg);
		return ResponseEntity.badRequest().body(error);

	}

	@ExceptionHandler(value=InvalidDataException.class)
	public ResponseEntity<ApiError> handleInvalidDataException(InvalidDataException invalidDataException)
	{
		String expmsg = invalidDataException.getMessage();
		String expcode = "400";
		ApiError error = new ApiError();
		error.setCode(expcode);
		error.setMsg(expmsg);

		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler(value=DevelopmentNotFoundException.class)
	public ResponseEntity<ApiError> handleDevelopmentNotFoundException(DevelopmentNotFoundException developmentNotFoundException)
	{
		String expmsg = developmentNotFoundException.getMessage();
		String expcode = "400";
		ApiError error = new ApiError();
		error.setCode(expcode);
		error.setMsg(expmsg);

		return ResponseEntity.badRequest().body(error);
	}
	
	@ExceptionHandler(value=DataExistsException.class)
	public ResponseEntity<ApiError> handleDevelopmentNotFoundException(DataExistsException dataExistsException)
	{
		String expmsg = dataExistsException.getMessage();
		String expcode = "400";
		ApiError error = new ApiError();
		error.setCode(expcode);
		error.setMsg(expmsg);

		return ResponseEntity.badRequest().body(error);
	}
}
