package in.political.party.service.impl;


import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.political.party.dto.LeaderDto;
import in.political.party.entity.Leader;
import in.political.party.entity.Party;
import in.political.party.exceptions.DataExistsException;
import in.political.party.exceptions.PartyNotFoundException;
import in.political.party.repository.LeaderRepository;
import in.political.party.repository.PartyRepository;
import in.political.party.service.LeaderService;



@Service
public class LeaderServiceImpl implements LeaderService {

	@Autowired
	private LeaderRepository leaderrepository;

	@Autowired
	private PartyRepository partyrepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public LeaderDto registerPoliticalLeader(LeaderDto leaderDto) {
		Leader politicalLeader = convertToEntity(leaderDto);
		Long politicalPartyId = politicalLeader.getPoliticalParty().getPoliticalPartyId();
		
		Optional<Leader> findLeader = leaderrepository.findById(politicalLeader.getPoliticalLeaderId());
		if(findLeader.isPresent())
			throw new DataExistsException("Leader alredy registerd with party , please update the leader data");
		
		Optional<Party> findById = partyrepository.findById(politicalPartyId);
		if(findById.isPresent())
		{
			Leader leader = leaderrepository.save(politicalLeader);
			return convertToDto(leader);
		}
		else
			throw new PartyNotFoundException("Party with ID "+leaderDto.getPoliticalPartyId() +" not found to register the leader "+leaderDto.getPoliticalLeaderId());
	}


	private LeaderDto convertToDto(Leader politicalLeader) {
		return modelMapper.map(politicalLeader, LeaderDto.class);

	}

	private Leader convertToEntity(LeaderDto postDto)  {
		return modelMapper.map(postDto, Leader.class);
		
	}




}
