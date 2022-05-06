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

import in.political.party.controller.DevelopmentController;
import in.political.party.dto.DevelopmentDto;
import in.political.party.dto.LeaderDto;
import in.political.party.service.DevelopmentService;
import in.political.party.testutils.MasterData;



@WebMvcTest(DevelopmentController.class)
@AutoConfigureMockMvc
public class DevelopmentControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DevelopmentService developmentService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testAddDevelopment() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		DevelopmentDto savedDevelopmentDto = MasterData.getDevelopmentDto();

		savedDevelopmentDto.setPoliticalLeaderId(1L);

		when(this.developmentService.createDevelopment(developmentDto)).thenReturn(savedDevelopmentDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/politics/api/v1/development/add-development")
				.content(MasterData.asJsonString(developmentDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		testAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedDevelopmentDto))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testAddDevelopmentIsServiceMethodCalled() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		DevelopmentDto savedDevelopmentDto = MasterData.getDevelopmentDto();
		final int count[] = new int[1];
		savedDevelopmentDto.setPoliticalLeaderId(1L);

		when(developmentService.createDevelopment(developmentDto)).then(new Answer<DevelopmentDto>() {

			@Override
			public DevelopmentDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return savedDevelopmentDto;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/politics/api/v1/development/add-development")
				.content(MasterData.asJsonString(developmentDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		testAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(savedDevelopmentDto))
						? "true"
						: "false"),
				businessTestFile);

	}

		
	@Test
	public void testGetAllDevelopmentsByPoliticalLeaderId() throws Exception {
		List<DevelopmentDto> developmentDtos = MasterData.getDevelopmentDtoList();

		when(this.developmentService.getAllDevelopmentsByLeaderId(1L)).thenReturn(developmentDtos);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/politics/api/v1/development/get-development-by/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		testAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(developmentDtos))
						? "true"
						: "false"),
				businessTestFile);

	}

	@Test
	public void testGetAllDevelopmentsByPoliticalLeaderIdIsServiceMethodCalled() throws Exception {
		final int count[] = new int[1];
		List<DevelopmentDto> developmentDtos = MasterData.getDevelopmentDtoList();
		when(this.developmentService.getAllDevelopmentsByLeaderId(1L)).then(new Answer<List<DevelopmentDto>>() {

			@Override
			public List<DevelopmentDto> answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return developmentDtos;
			}
		});
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/politics/api/v1/development/get-development-by/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		testAssert(currentTest(), count[0] == 1 ? true : false, businessTestFile);

	}
}