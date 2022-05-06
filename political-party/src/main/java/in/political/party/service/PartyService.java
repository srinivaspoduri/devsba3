package in.political.party.service;


import in.political.party.dto.PartyDto;



public interface PartyService {

	public PartyDto registerParty(PartyDto politicalPartyDto);

	public boolean deleteLeader(Long partyId);
	public PartyDto getLeaderPartyDetails(Long leaderId);
	

}
