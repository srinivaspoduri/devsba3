package in.political.party.boundary;

import static in.political.party.testutils.TestUtils.boundaryTestFile;
import static in.political.party.testutils.TestUtils.currentTest;
import static in.political.party.testutils.TestUtils.testAssert;
import static in.political.party.testutils.TestUtils.testReport;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import in.political.party.dto.DevelopmentDto;
import in.political.party.dto.LeaderDto;
import in.political.party.dto.PartyDto;
import in.political.party.testutils.MasterData;

@ExtendWith(SpringExtension.class)
public class BoundaryTest {
	private static Validator validator;

	// ----------------------------------------------------------------------------------------------
	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testPoliticalPartyNameNotNull() throws Exception {
		PartyDto PartyDto = MasterData.getPartyDto();
		PartyDto.setPartyName("");
		Set<ConstraintViolation<PartyDto>> violations = validator.validate(PartyDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPoliticalPartyNameMinFive() throws Exception {
		PartyDto PartyDto = MasterData.getPartyDto();
		PartyDto.setPartyName("Ab");
		Set<ConstraintViolation<PartyDto>> violations = validator.validate(PartyDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPoliticalPartyNameMaxHundred() throws Exception {
		PartyDto PartyDto = MasterData.getPartyDto();
		String partyName = "";
		for (int i = 0; i < 120; i++) {
			partyName.concat("A");
		}
		PartyDto.setPartyName(partyName);
		Set<ConstraintViolation<PartyDto>> violations = validator.validate(PartyDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPartyFounderNameNotNull() throws Exception {
		PartyDto PartyDto = MasterData.getPartyDto();
		PartyDto.setFounderName("");
		Set<ConstraintViolation<PartyDto>> violations = validator.validate(PartyDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPartyFounderNameMinFive() throws Exception {
		PartyDto PartyDto = MasterData.getPartyDto();
		PartyDto.setFounderName("Ab");
		Set<ConstraintViolation<PartyDto>> violations = validator.validate(PartyDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPartyFounderNameMaxHundred() throws Exception {
		PartyDto PartyDto = MasterData.getPartyDto();
		String founderName = "";
		for (int i = 0; i < 120; i++) {
			founderName.concat("A");
		}
		PartyDto.setFounderName(founderName);
		Set<ConstraintViolation<PartyDto>> violations = validator.validate(PartyDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPoliticalLeaderPartyIdNotNull() throws Exception {
		LeaderDto LeaderDto = MasterData.getLeaderDto();
		LeaderDto.setPoliticalPartyId(null);
		Set<ConstraintViolation<LeaderDto>> violations = validator.validate(LeaderDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPoliticalLeaderCandidateNameNotNull() throws Exception {
		LeaderDto LeaderDto = MasterData.getLeaderDto();
		LeaderDto.setCandidateName("");
		Set<ConstraintViolation<LeaderDto>> violations = validator.validate(LeaderDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPoliticalLeaderCandidateNameMinFive() throws Exception {
		LeaderDto LeaderDto = MasterData.getLeaderDto();
		LeaderDto.setCandidateName("Ab");
		Set<ConstraintViolation<LeaderDto>> violations = validator.validate(LeaderDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPoliticalLeaderCandidateNameMaxHundred() throws Exception {
		LeaderDto LeaderDto = MasterData.getLeaderDto();
		String candidateName = "";
		for (int i = 0; i < 120; i++) {
			candidateName.concat("A");
		}
		LeaderDto.setCandidateName(candidateName);
		Set<ConstraintViolation<LeaderDto>> violations = validator.validate(LeaderDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPoliticalLeaderCandidateStateNotNull() throws Exception {
		LeaderDto LeaderDto = MasterData.getLeaderDto();
		LeaderDto.setCandidateState("");
		Set<ConstraintViolation<LeaderDto>> violations = validator.validate(LeaderDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPoliticalLeaderCandidateStateMinFive() throws Exception {
		LeaderDto LeaderDto = MasterData.getLeaderDto();
		LeaderDto.setCandidateState("Ab");
		Set<ConstraintViolation<LeaderDto>> violations = validator.validate(LeaderDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testPoliticalLeaderCandidateStateMax100() throws Exception {
		LeaderDto LeaderDto = MasterData.getLeaderDto();
		String candidateState = "";
		for (int i = 0; i < 100; i++) {
			candidateState.concat("A");
		}
		LeaderDto.setCandidateState(candidateState);
		Set<ConstraintViolation<LeaderDto>> violations = validator.validate(LeaderDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentPoliticalLeaderIdNotNull() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setPoliticalLeaderId(null);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentTitleNotNull() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setTitle(null);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentTitleMinFive() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setTitle("Ab");
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentTitleMaxHundred() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		String title = "";
		for (int i = 0; i < 120; i++) {
			title.concat("A");
		}
		developmentDto.setTitle(title);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentActivityNotNull() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setActivity("");
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentActivityMinFive() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setActivity("Ab");
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentActivityMaxHundred() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		String activity = "";
		for (int i = 0; i < 120; i++) {
			activity.concat("A");
		}
		developmentDto.setActivity(activity);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentBudgetNotNull() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setBudget("");
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentBudgetMinFive() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setBudget("Ab");
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentBudgetMaxHundred() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		String budget = "";
		for (int i = 0; i < 120; i++) {
			budget.concat("A");
		}
		developmentDto.setBudget(budget);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentStateNotNull() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setState("");
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentStateMinThree() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setState("Ab");
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentStateMaxFifty() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		String state = "";
		for (int i = 0; i < 60; i++) {
			state.concat("A");
		}
		developmentDto.setState(state);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentActivityMonthNotNull() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setActivityMonth(null);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentActivityMonthMinOne() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setActivityMonth(0);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentActivityMonthMaxTwelve() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();

		developmentDto.setActivityMonth(13);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentActivityYearNotNull() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setActivityYear(null);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentActivityYearMin2020() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		developmentDto.setActivityYear(2012);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

	@Test
	public void testDevelopmentActivityYearMax2040() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();

		developmentDto.setActivityYear(2045);
		Set<ConstraintViolation<DevelopmentDto>> violations = validator.validate(developmentDto);
		testAssert(currentTest(), !violations.isEmpty() ? true : false, boundaryTestFile);
	}

}
