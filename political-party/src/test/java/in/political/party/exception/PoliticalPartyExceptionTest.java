package in.political.party.exception;

import static in.political.party.testutils.TestUtils.currentTest;
import static in.political.party.testutils.TestUtils.exceptionTestFile;
import static in.political.party.testutils.TestUtils.testAssert;
import static in.political.party.testutils.TestUtils.testReport;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



import in.political.party.controller.PartyController;
import in.political.party.dto.PartyDto;
import in.political.party.exceptions.LeaderIdNotFoundException;
import in.political.party.exceptions.PartyNotFoundException;
import in.political.party.service.PartyService;
import in.political.party.testutils.MasterData;

@WebMvcTest(PartyController.class)
@AutoConfigureMockMvc
public class PoliticalPartyExceptionTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PartyService partyService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testRegisterPoliticalPartyInvalidDataException() throws Exception {
		PartyDto politicalPartyDto = MasterData.getPartyDto();
		PartyDto savedPoliticalPartyDto = MasterData.getPartyDto();
		savedPoliticalPartyDto.setPoliticalPartyId(1L);

		politicalPartyDto.setPartyName("Ab");

		when(this.partyService.registerParty(politicalPartyDto)).thenReturn(savedPoliticalPartyDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/politics/api/v1/party/register-party")
				.content(MasterData.asJsonString(politicalPartyDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		testAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}
	
	@Test
	public void testDeleteLeaderWhenPartyRegisteredNotPresent() throws Exception {
		

		when(this.partyService.deleteLeader(2L))
				.thenThrow(new PartyNotFoundException("Party not present to delete the leader 2"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/politics/api/v1/party/delete/2")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println("***********************"+result.getResponse().getContentAsString());
		testAssert(currentTest(),
				(result.getResponse().getContentAsString().contains("Party not present to delete the leader 2") ? "true" : "false"),
				exceptionTestFile);

	}
	

}
