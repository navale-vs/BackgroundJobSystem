package backgroundjobsystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import backgroundjobsystem.BackgroundJobSystemController;


public class BackgroundJobSystemControllerTest {
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(new BackgroundJobSystemController()).setControllerAdvice().build();
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	//@Test
	void testAddJob() {
		try {
			ResultActions ra = mockMvc.perform(MockMvcRequestBuilders.post("/addjob"));

			Map<String, Object> modelMap = ra.andReturn().getModelAndView().getModel();
			assertEquals(modelMap.size(), 1);
			assert(modelMap.containsKey("allJobs"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Test
	void testGetAllJobs() {
		try {
			ResultActions ra = mockMvc.perform(MockMvcRequestBuilders.post("/getalljobs"));

			Map<String, Object> modelMap = ra.andReturn().getModelAndView().getModel();
			assert(modelMap.containsKey("getalljobs"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}