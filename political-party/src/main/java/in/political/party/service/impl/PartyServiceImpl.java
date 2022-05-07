package in.political.party.service.impl;


import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.political.party.dto.PartyDto;
import in.political.party.entity.Leader;
import in.political.party.entity.Party;
import in.political.party.exceptions.PartyNotFoundException;
import in.political.party.repository.LeaderRepository;
import in.political.party.repository.PartyRepository;
import in.political.party.service.PartyService;

@Service
public class PartyServiceImpl implements PartyService {

	@Autowired
	private PartyRepository politicalPartyRepository;

	@Autowired
	private LeaderRepository politicalLeaderRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PartyDto registerParty(PartyDto politicalPartyDto) {
		Party politicalParty = convertToEntity(politicalPartyDto);
		Party party = politicalPartyRepository.save(politicalParty);
		return convertToDto(party);
	}

	@Override
	public boolean deleteLeader(Long leaderId) {
		PartyDto leaderPartyDetails = getLeaderPartyDetails(leaderId);
		if(leaderPartyDetails!=null)
		{
			Optional<Leader> politicalLeader= politicalLeaderRepository.findById(leaderId);
			if(politicalLeader.isPresent())
			{
				politicalLeaderRepository.deleteById(leaderId);
				return true;
			}
			else
			{
				return false;
			}
		}else
		{
			throw new PartyNotFoundException("Party not present to delete  "+leaderId);
		}
	}

	public PartyDto getLeaderPartyDetails(Long leaderId)
	{

		List<Leader> partyusingLeaderId = politicalLeaderRepository.findByPoliticalLeaderId(leaderId);
		if(!partyusingLeaderId.isEmpty())

			return convertToDto(partyusingLeaderId.get(0).getPoliticalParty());
		else
			throw new PartyNotFoundException("Leader not registered to any Party....");
	}

	private PartyDto convertToDto(Party politicalparty) {
		return modelMapper.map(politicalparty, PartyDto.class);


	}

	private Party convertToEntity(PartyDto postDto)  {
		return modelMapper.map(postDto, Party.class);

	}

}
