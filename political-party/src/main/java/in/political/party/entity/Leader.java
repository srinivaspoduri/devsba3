package in.political.party.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Leader {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long politicalLeaderId;
//	@NotNull
//	private Long politicalPartyId;
	@NotNull
	private String candidateName;
	@NotNull
	private String candidateState;
	
	//one politicalleader may have many developments
	@OneToMany(mappedBy="politicalLeader")
	private List<Development> development;
	
	
	//many leaders related to single party.
	
	@NotNull
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="politicalPartyId")
	private Party politicalParty;
	
	public Party getPoliticalParty() {
		return politicalParty;
	}
	public void setPoliticalParty(Party politicalParty) {
		this.politicalParty = politicalParty;
	}
	public Long getPoliticalLeaderId() {
		return politicalLeaderId;
	}
	public void setPoliticalLeaderId(Long politicalLeaderId) {
		this.politicalLeaderId = politicalLeaderId;
	}
//	public Long getPoliticalPartyId() {
//		return politicalPartyId;
//	}
//	public void setPoliticalPartyId(Long politicalPartyId) {
//		this.politicalPartyId = politicalPartyId;
//	}
	public String getCandidateName() {
		return candidateName;
	}
	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}
	public String getCandidateState() {
		return candidateState;
	}
	public void setCandidateState(String candidateState) {
		this.candidateState = candidateState;
	}
	public List<Development> getDevelopment() {
		return development;
	}
	public void setDevelopment(List<Development> development) {
		this.development = development;
	}
	@Override
	public String toString() {
		return "PoliticalLeader [politicalLeaderId=" + politicalLeaderId + ", candidateName=" + candidateName
				+ ", candidateState=" + candidateState + ", development=" + development + ", politicalParty="
				+ politicalParty + "]";
	}

	
	
}
