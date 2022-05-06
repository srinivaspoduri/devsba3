package in.political.party.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.political.party.dto.DevelopmentDto;
import in.political.party.dto.LeaderDevelopmentDto;
import in.political.party.dto.LeaderDto;
import in.political.party.entity.Development;
import in.political.party.entity.Leader;
import in.political.party.entity.Party;
import in.political.party.repository.DevelopmentRepository;
import in.political.party.repository.LeaderRepository;
import in.political.party.service.DevelopmentService;


@Service
@Transactional
public class DevelopmentServiceImpl implements DevelopmentService {

	@Autowired
	private DevelopmentRepository developmentRepository;

	@Autowired
	private LeaderRepository leaderRepository;



	@Autowired
	private ModelMapper modelMapper;


	@Override
	@Transactional
	public DevelopmentDto createDevelopment(DevelopmentDto developmentDto) {
		Development development = convertToEntity(developmentDto);
		developmentRepository.save(development);
		return developmentDto;
	}

	@Override
	public DevelopmentDto updateDevelopment(DevelopmentDto developmentDto) {
		Development development = convertToEntity(developmentDto);
		developmentRepository.save(development);
		return developmentDto;
	}



	@Override
	public List<DevelopmentDto> getAllDevelopmentsByLeaderId(Long politicalLeaderId) {

		List<Development> developments = developmentRepository.findByPoliticalLeaderId(politicalLeaderId);

		List<DevelopmentDto> developmentDtos = new ArrayList<>();
		for (Development development : developments) {
			developmentDtos.add(convertToDto(development));
		}
		return developmentDtos;
	}


	public LeaderDevelopmentDto getDevByLeader(Long politicalLeaderId)
	{
		List<Development> developments = developmentRepository.findByPoliticalLeaderId(politicalLeaderId);

		List<DevelopmentDto> developmentDtos = new ArrayList<>();
		for (Development development : developments) {
			developmentDtos.add(convertToDto(development));
		}
		Optional<Leader> leader = leaderRepository.findById(politicalLeaderId);
		LeaderDto leaderdto = new LeaderDto();
		if(leader.isPresent())
		{
			Party party = leader.get().getPoliticalParty();
			BeanUtils.copyProperties(leader.get(), leaderdto);
			leaderdto.setPoliticalPartyId(party.getPoliticalPartyId());
		}
		LeaderDevelopmentDto leaderDevelopmentDto = new LeaderDevelopmentDto();
		leaderDevelopmentDto.setDevelopmentDtos(developmentDtos);
		leaderDevelopmentDto.setLeader(leaderdto);
		return leaderDevelopmentDto;

	}

	private DevelopmentDto convertToDto(Development development) {
		return modelMapper.map(development, DevelopmentDto.class);

	}

	private Development convertToEntity(DevelopmentDto postDto)  {
		return modelMapper.map(postDto, Development.class);

	}
}
