package in.political.party.controller;

import javax.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.political.party.dto.DevelopmentDto;
import in.political.party.dto.LeaderDevelopmentDto;
import in.political.party.exceptions.InvalidDataException;
import in.political.party.service.DevelopmentService;



@RestController
@RequestMapping("/politics/api/v1/development")
public class DevelopmentController {

	@Autowired
	private DevelopmentService developmentService;

	@PostMapping("/add-development")
	public ResponseEntity<DevelopmentDto> addDevelopments(@Valid @RequestBody DevelopmentDto developmentDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
         {
           
           throw new InvalidDataException("Invalid data Recived to register new Leader");
       }
		DevelopmentDto createDevelopment = developmentService.createDevelopment(developmentDto);
		return new ResponseEntity<DevelopmentDto>(createDevelopment, HttpStatus.OK);
		
		
	}
	@GetMapping("get-development-by/{leaderId}")
	public ResponseEntity<List<DevelopmentDto>> getAllDevelopmentsByPoliticalLeaderId(@PathVariable Long leaderId) {

		List<DevelopmentDto> allDevelopmentsByLeaderId = developmentService
				.getAllDevelopmentsByLeaderId(leaderId);
		return new ResponseEntity<List<DevelopmentDto>>(allDevelopmentsByLeaderId, HttpStatus.OK);

	}
	@PutMapping("/update-development")
	public ResponseEntity<DevelopmentDto> updateDevelopments(@Valid @RequestBody DevelopmentDto developmentDto,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException("Invalid data Recived to update the development");
		}
		DevelopmentDto updateDevelopment = developmentService.updateDevelopment(developmentDto);
		return new ResponseEntity<DevelopmentDto>(updateDevelopment, HttpStatus.OK);

	}
	
	@GetMapping("getdev/{leaderId}")
	public ResponseEntity<LeaderDevelopmentDto> getAllDevByLeader(@PathVariable Long leaderId) {

		LeaderDevelopmentDto devByLeader = developmentService.getDevByLeader(leaderId);
		return new ResponseEntity<LeaderDevelopmentDto>(devByLeader, HttpStatus.OK);

	}
	
}
