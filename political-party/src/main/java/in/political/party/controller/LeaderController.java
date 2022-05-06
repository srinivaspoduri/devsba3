package in.political.party.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.political.party.dto.LeaderDto;
import in.political.party.exceptions.InvalidDataException;
import in.political.party.exceptions.PartyNotFoundException;
import in.political.party.service.LeaderService;



@RestController
@RequestMapping("/politics/api/v1")
public class LeaderController {
	
	@Autowired
	private LeaderService leaderService;
	
	
	@PostMapping("/leader/register-leader")
	public ResponseEntity<LeaderDto> addPoliticalLeader(@Valid @RequestBody LeaderDto politicalLeaderDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
         {
          
           throw new InvalidDataException("Invalid data Recived to register new Leader");
       }
		LeaderDto registerPoliticalLeader = leaderService.registerPoliticalLeader(politicalLeaderDto);
		if(registerPoliticalLeader!=null)
			return new ResponseEntity<LeaderDto>(registerPoliticalLeader, HttpStatus.OK);
		
		else
			throw new PartyNotFoundException("Party with ID "+politicalLeaderDto.getPoliticalPartyId() +" not found to register the leader "+politicalLeaderDto.getPoliticalLeaderId());

	}
}
