package in.political.party.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Party {

	@Id
	private Long politicalPartyId;
	@NotNull
	private String partyName;
	@NotNull
	private String founderName;
	
	@NotNull
	private Long foundationYear;
	
	
	public Long getFoundationYear() {
		return foundationYear;
	}

	public void setFoundationYear(Long foundationYear) {
		this.foundationYear = foundationYear;
	}

	//one party may have many leaders.
	@OneToMany(mappedBy="politicalParty" ,cascade=CascadeType.ALL)
	private List<Leader> politicalLeader;

	public List<Leader> getPoliticalLeader() {
		return politicalLeader;
	}

	public void setPoliticalLeader(List<Leader> politicalLeader) {
		this.politicalLeader = politicalLeader;
	}

	public Long getPoliticalPartyId() {
		return politicalPartyId;
	}

	public void setPoliticalPartyId(Long partyId) {
		this.politicalPartyId = partyId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getFounderName() {
		return founderName;
	}

	public void setFounderName(String founderName) {
		this.founderName = founderName;
	}

	@Override
	public String toString() {
		return "PoliticalParty [politicalPartyId=" + politicalPartyId + ", partyName=" + partyName + ", founderName="
				+ founderName + ", foundationYear=" + foundationYear + ", politicalLeader=" + politicalLeader + "]";
	}

}
