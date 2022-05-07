package in.political.party.dto;
import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
public class DevelopmentDto {

	@NotNull
	private Long developmentId;
	@NotNull
	private Long politicalLeaderId;
	@NotNull
	@Length(min = 5, max = 100)
	private String title;
	@NotNull
	@Length(min = 5, max = 100)
	private String activity;
	@NotNull
	@DecimalMin(value = "0.00", inclusive = false)
	//@Digits(integer=3, fraction=2)
	@Column(precision=10, scale=2)
	private String budget;
	@NotNull
	@Length(min = 3, max = 50)
	private String state;
	@NotNull
	@Range(min = 1, max = 12)
	private Integer activityMonth;
	@NotNull
	@Range(min = 2020, max = 2040)
	private Integer activityYear;

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

}
