package in.political.party.dto;

import java.util.Objects;

import javax.persistence.Column;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class LeaderDto {
	@NotNull
	private Long politicalLeaderId;
	@NotNull
	private Long politicalPartyId;
	@NotNull
	@Length(min = 5, max = 100)
	@Column(unique = true)
	private String candidateName;
	@NotNull
	@Length(min = 5, max = 100)
	private String candidateState;

	public Long getPoliticalLeaderId() {
		return politicalLeaderId;
	}

	public void setPoliticalLeaderId(Long candidateId) {
		this.politicalLeaderId = candidateId;
	}

	public Long getPoliticalPartyId() {
		return politicalPartyId;
	}

	public void setPoliticalPartyId(Long partyId) {
		this.politicalPartyId = partyId;
	}

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

	@Override
	public String toString() {
		return "PoliticalLeaderDto [politicalLeaderId=" + politicalLeaderId + ", politicalPartyId=" + politicalPartyId
				+ ", candidateName=" + candidateName + ", candidateState=" + candidateState + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(politicalLeaderId, candidateName, candidateState, politicalPartyId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LeaderDto other = (LeaderDto) obj;
		return Objects.equals(politicalLeaderId, other.politicalLeaderId)
				&& Objects.equals(candidateName, other.candidateName)
				&& Objects.equals(candidateState, other.candidateState)
				&& Objects.equals(politicalPartyId, other.politicalPartyId);
	}
}
