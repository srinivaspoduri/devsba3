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

import in.political.party.controller.DevelopmentController;
import in.political.party.dto.DevelopmentDto;
import in.political.party.service.DevelopmentService;
import in.political.party.testutils.MasterData;

@WebMvcTest(DevelopmentController.class)
@AutoConfigureMockMvc
public class DevelopmentExceptionTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DevelopmentService developmentService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateDevelopmentInvalidDataException() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		DevelopmentDto savedDevelopmentDto = MasterData.getDevelopmentDto();
		savedDevelopmentDto.setDevelopmentId(1L);

		developmentDto.setActivity("Ab");

		when(this.developmentService.createDevelopment(developmentDto)).thenReturn(savedDevelopmentDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/politics/api/v1/development/add-development")
				.content(MasterData.asJsonString(developmentDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
System.out.println("*********************"+result.getResponse().getStatus());
		testAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

	@Test
	public void testUpdateDevelopmentInvalidDataException() throws Exception {
		DevelopmentDto developmentDto = MasterData.getDevelopmentDto();
		DevelopmentDto savedDevelopmentDto = MasterData.getDevelopmentDto();
		savedDevelopmentDto.setDevelopmentId(1L);

		developmentDto.setActivity("Ab");

		when(this.developmentService.createDevelopment(developmentDto)).thenReturn(savedDevelopmentDto);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/politics/api/v1/development/update-development")
				.content(MasterData.asJsonString(developmentDto)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		testAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);

	}

//	@Test
//	public void testDeleteDevelopmentNotFoundException() throws Exception {
//		ExceptionResponse exResponse = new ExceptionResponse("Development with Id - 2 not Found!",
//				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
//
//		when(this.developmentService.deleteDevelopment(2L))
//				.thenThrow(new PoliticalLeaderNotFoundException("Development with Id - 2 not Found!"));
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/politics/api/v1/development/2")
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		testAssert(currentTest(),
//				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
//				exceptionTestFile);
//
//	}
//	@Test
//	public void testGetDevelopmentByIdDevelopmentNotFoundException() throws Exception {
//		ExceptionResponse exResponse = new ExceptionResponse("Development with Id - 2 not Found!",
//				System.currentTimeMillis(), HttpStatus.NOT_FOUND.value());
//		
//		when(this.developmentService.getDevelopmentById(2L))
//		.thenThrow(new PoliticalLeaderNotFoundException("Development with Id - 2 not Found!"));
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/politics/api/v1/development/2")
//				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
//		
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		testAssert(currentTest(),
//				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
//				exceptionTestFile);
//		
	//}

	

}
