package com.example.SubscriptionManagementSystem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class SubscriptionManagementSystemApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void signUp() throws Exception{
		mockMvc.perform(post("/user/login").header("username","sankalpa").header("password","1234")).andExpect(status().isOk());
	}

}
