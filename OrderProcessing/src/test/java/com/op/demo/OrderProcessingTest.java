package com.op.demo;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.op.demo.controllers.OrderProcessingController;
import com.op.demo.model.Order;
import com.op.demo.utils.PaymentTypes;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = OrderProcessingController.class)
@WebAppConfiguration
public abstract class OrderProcessingTest {

	protected MockMvc mvc;
	@Autowired
	WebApplicationContext webApplicationContext;

	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}

}

class OrderProcessingControllerTest extends OrderProcessingTest {
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void processOrder_when_everything_correct() throws Exception {
	   String uri = "/api/v1/processOrder";
	   Order order = new Order();
	   order.setId(1001L);
	   order.setCustomerId(9001L);
	   order.setDescription("Order for Learning to SKI video");
	   order.setUnits(1);
	   order.setPrice(new BigDecimal(100.00));
	   order.setPaymentTypes(PaymentTypes.LEARNING_TO_SKI);
	   
	   String inputJson = super.mapToJson(order);
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(201, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   assertEquals(content, "Action : Add free First Aid video to packing slip");
	}
}