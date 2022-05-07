package in.political.party.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.political.party.dto.DevelopmentDto;
import in.political.party.dto.LeaderDevelopmentDto;
import in.political.party.dto.LeaderDto;
import in.political.party.entity.Development;
import in.political.party.entity.Leader;
import in.political.party.entity.Party;
import in.political.party.exceptions.DevelopmentNotFoundException;
import in.political.party.exceptions.LeaderIdNotFoundException;
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
		Optional<Leader> findById = leaderRepository.findById(development.getPoliticalLeader().getPoliticalLeaderId());
		if(findById.isPresent())
		{
			Development createdDevelopment = developmentRepository.save(development);
			createdDevelopment.setDevelopmentId(developmentDto.getDevelopmentId());
			return convertToDto(createdDevelopment);
		}
		else
			throw new LeaderIdNotFoundException("Leader not present");

	}

	@Override
	public DevelopmentDto updateDevelopment(DevelopmentDto developmentDto) {
		Development development = convertToEntity(developmentDto);
		Development savedDevelopment = developmentRepository.save(development);
		Leader leader =development.getPoliticalLeader();
			savedDevelopment.setPoliticalLeader(leader);
			return convertToDto(savedDevelopment);
		
	}



	@Override
	public List<DevelopmentDto> getAllDevelopmentsByLeaderId(Long politicalLeaderId) {

		List<Development> developments = developmentRepository.findByPoliticalLeaderId(politicalLeaderId, null);

		List<DevelopmentDto> developmentDtos = new ArrayList<>();
		for (Development development : developments) {
			developmentDtos.add(convertToDto(development));
		}
		return developmentDtos;
	}

	public LeaderDevelopmentDto getDevByLeader(Long politicalLeaderId)
	{
		List<Development> developments = developmentRepository.findByPoliticalLeaderId(politicalLeaderId,Sort.by(Sort.Direction.DESC,"activityYear"));
		Optional<Leader> leader = leaderRepository.findById(politicalLeaderId);
		if(!leader.isPresent())
		{
			throw new LeaderIdNotFoundException("Leader not present to get development details");
		}
		if(!developments.isEmpty())
		{
			List<DevelopmentDto> developmentDtos = new ArrayList<>();
			for (Development development : developments) {
				developmentDtos.add(convertToDto(development));
			}
			leader = leaderRepository.findById(politicalLeaderId);
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
		else
		{
			throw new DevelopmentNotFoundException("No Development Activities found for the leader");
		}

	}

	private DevelopmentDto convertToDto(Development development) {
		return modelMapper.map(development, DevelopmentDto.class);

	}

	private Development convertToEntity(DevelopmentDto postDto)  {
		return modelMapper.map(postDto, Development.class);

	}
}
