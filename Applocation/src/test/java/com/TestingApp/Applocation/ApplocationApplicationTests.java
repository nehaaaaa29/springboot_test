package com.TestingApp.Applocation;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
@Slf4j
public class ApplocationApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(ApplocationApplicationTests.class);
 @BeforeEach
	void setUp(){
		log.info("STarting the method,setting up configuration");
	}
	@AfterAll
	static void tearDown(){
		log.info("Ending the method,tearing down configuration");
	}
	@Test
	void testNumberOne() {
		int a = 5;
		int b = 3;
		int result = addTwoNumbers(a, b);
		//Assertions.assertEquals(8, result);

		Assertions.assertThat(result)
				 .isEqualTo(8)
		         .isCloseTo(9, Offset.offset(1));
	}
	//@DisplayName("nehaTesting")
	@Test
	void testing_test(){
		log.info("Test Number Two is running");

	}
	int addTwoNumbers(int a,int b){
	 return a+b;
	}

}
