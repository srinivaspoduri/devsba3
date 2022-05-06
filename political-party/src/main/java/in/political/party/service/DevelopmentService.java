package in.political.party.service;

import java.util.List;

import in.political.party.dto.DevelopmentDto;
import in.political.party.dto.LeaderDevelopmentDto;



public interface DevelopmentService {

	public DevelopmentDto createDevelopment(DevelopmentDto developmentDto);

	public DevelopmentDto updateDevelopment(DevelopmentDto developmentDto);

	public List<DevelopmentDto> getAllDevelopmentsByLeaderId(Long politicalLeaderId);
	
	public LeaderDevelopmentDto getDevByLeader(Long politicalLeaderId);
	
}
