package in.political.party.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import in.political.party.dto.LeaderDto;



public interface LeaderService {

	public LeaderDto registerPoliticalLeader(LeaderDto leaderDto);

	

}
