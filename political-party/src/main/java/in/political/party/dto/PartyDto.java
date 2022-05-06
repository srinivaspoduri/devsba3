package in.political.party.dto;



import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PartyDto {
	@NotNull
	private Long politicalPartyId;
	@NotNull
	@Length(min = 5, max = 100)
	private String partyName;
	@NotNull
	@Length(min = 5, max = 100)
	private String founderName;
	
	
	@NotNull
	@Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy")
	private Long foundationYear;
	

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

	public Long getFoundationYear() {
		return foundationYear;
	}

	public void setFoundationYear(Long foundationYear) {
		this.foundationYear = foundationYear;
	}

	@Override
	public String toString() {
		return "PoliticalPartyDto [politicalPartyId=" + politicalPartyId + ", partyName=" + partyName + ", founderName="
				+ founderName + ", foundationYear=" + foundationYear + "]";
	}

	
}
