package in.political.party.functional;
import static in.political.party.testutils.TestUtils.currentTest;
import static in.political.party.testutils.TestUtils.businessTestFile;
import static in.political.party.testutils.TestUtils.testAssert;
import static in.political.party.testutils.TestUtils.testReport;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import in.political.party.controller.PartyController;
import in.political.party.dto.LeaderDto;
import in.political.party.dto.PartyDto;
import in.political.party.service.PartyService;
import in.political.party.testutils.MasterData;





@WebMvcTest(PartyController.class)
@AutoConfigureMockMvc
public class PoliticalPartyControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PartyService partyService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testRegisterPoliticalParty() throws Exception {
		PartyDto politicalPartyDto = MasterData.getPartyDto();
		PartyDto savedPoliticalPartyDto = MasterData.getPartyDto();

		savedPoliticalPartyDto.setPoliticalPartyId(1L);

		when(this.partyService.registerParty(politicalPartyDto)).thenReturn(savedPoliticalPartyDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/politics/api/v1/party/register-party")
				.content(MasterData.asJsonString(politicalPartyDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getStatus()+"*******************"+result.getResponse().getContentAsString());
		testAssert(currentTest(),
				(result.getResponse().getStatus()==200
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testRegisterPoliticalPartyIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		PartyDto politicalPartyDto = MasterData.getPartyDto();
		PartyDto savedPoliticalPartyDto = MasterData.getPartyDto();

		savedPoliticalPartyDto.setPoliticalPartyId(1L);
		when(partyService.registerParty(politicalPartyDto)).then(new Answer<PartyDto>() {

			@Override
			public PartyDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedPoliticalPartyDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/politics/api/v1/party/register-party")
				.content(MasterData.asJsonString(politicalPartyDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getStatus());

		testAssert(currentTest(),
				(result.getResponse().getStatus()==200
						? "true"
						: "false"),
				businessTestFile);

	}
	
	@Test
	public void testDeletePoliticalLeader() throws Exception {
	LeaderDto politicalLeaderDto = MasterData.getLeaderDto();
		politicalLeaderDto.setPoliticalLeaderId(1L);
		politicalLeaderDto.setPoliticalPartyId(1L);
		when(this.partyService.deleteLeader(1L)).thenReturn(true);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/politics/api/v1/party/delete/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		testAssert(currentTest(),
				(result.getResponse().getContentAsString()
						.contentEquals("Leader Deleted Successfully") ? "true" : "false"),
				businessTestFile);

	}
	
	
	@Test
	public void testDeletePoliticalLeaderWhenDeleteFail() throws Exception {
	LeaderDto politicalLeaderDto = MasterData.getLeaderDto();
		politicalLeaderDto.setPoliticalLeaderId(1L);
		politicalLeaderDto.setPoliticalPartyId(1L);
		when(this.partyService.deleteLeader(1L)).thenReturn(false);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/politics/api/v1/party/delete/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		testAssert(currentTest(),
				(result.getResponse().getStatus()==400 ? "true" : "false"),
				businessTestFile);

	}
}
