package in.political.party.dto;

import java.util.List;



public class LeaderDevelopmentDto {
	
	private List<DevelopmentDto> developmentDtos;
	private LeaderDto leader;
		public List<DevelopmentDto> getDevelopmentDtos() {
			return developmentDtos;
		}
		public void setDevelopmentDtos(List<DevelopmentDto> developmentDtos) {
			this.developmentDtos = developmentDtos;
		}
		public LeaderDto getLeader() {
			return leader;
		}
		public void setLeader(LeaderDto leader) {
			this.leader = leader;
		}
		
		

}
