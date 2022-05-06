package in.political.party.entity;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Development {

	@NotNull
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long developmentId;
	@NotNull
	private Long politicalLeaderId;
	@NotNull
	private String title;
	@NotNull
	private String activity;
	@NotNull
	private String budget;

	@NotNull
	private String state;
	@NotNull
	private Integer activityMonth;
	@NotNull
	private Integer activityYear;
	
	//many developments linked to one leader. the FK is politicalLeaderId

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="politicalLeaderId" ,insertable= false,updatable=false)
	private Leader politicalLeader;

	public Long getDevelopmentId() {
		return developmentId;
	}

	public void setDevelopmentId(Long developmentId) {
		this.developmentId = developmentId;
	}

	public Long getPoliticalLeaderId() {
		return politicalLeaderId;
	}

	public void setPoliticalLeaderId(Long candidateId) {
		this.politicalLeaderId = candidateId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getActivityMonth() {
		return activityMonth;
	}

	public void setActivityMonth(Integer activityMonth) {
		this.activityMonth = activityMonth;
	}

	public Integer getActivityYear() {
		return activityYear;
	}

	public void setActivityYear(Integer activityYear) {
		this.activityYear = activityYear;
	}

	public Leader getPoliticalLeader() {
		
		System.out.println("Development getter political leader -" +politicalLeader );
		return politicalLeader;
	}

	public void setPoliticalLeader(Leader politicalLeader) {
		System.out.println("Development setter political leader -" +politicalLeader );
		this.politicalLeader = politicalLeader;
	}

	@Override
	public String toString() {
		return "Development [developmentId=" + developmentId + ", title=" + title + ", activity=" + activity
				+ ", budget=" + budget + ", state=" + state + ", activityMonth=" + activityMonth + ", activityYear="
				+ activityYear + ", politicalLeader=" + politicalLeader + "]";
	}



	


}
