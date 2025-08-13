package com.practice.springprofiles;

import com.practice.springprofiles.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("dev")  // Testing the dev profile
class AppConfigTest {

	@Autowired
	private AppConfig appConfig;

	@Test
	void testConfigMessage() {
		assertEquals("Running in Development Mode", appConfig.getMessage());
	}
}
