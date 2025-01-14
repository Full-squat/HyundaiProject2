package com.chysk5.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.chysk5.domain.ResellPriceSearchDTO;
import com.google.gson.Gson;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"})
@Log4j

public class ResellControllerTests {
	
	@Setter(onMethod_ = @Autowired )
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test
	public void resellProductListControllerTest() throws Exception{
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/resell"))
				.andReturn()
				.getModelAndView()
				.getModelMap()
				);
	}
	
	@Test
	public void resellProductDetailControllerTest() throws Exception{
		log.info(
				mockMvc.perform(MockMvcRequestBuilders.get("/resell/146"))
				.andReturn()
				.getModelAndView()
				.getModelMap()
				);
	}
	
	@Test
	public void resellProductPriceControllerTest() throws Exception{
		
		ResellPriceSearchDTO dto = new ResellPriceSearchDTO();
		
		dto.setProName("PINTUCK LOGO CROP TOP IN PINK");
		dto.setSizeVal("S");
		
		String jsonStr = new Gson().toJson(dto);
		
		mockMvc.perform(
				post("/resell/price")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr)
				).andExpect(status().is(200));
				
		
	}
	
	
}
