package com.TestingApp.Applocation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") //
@Import(TestContainerConfiguration.class)

public class ApplocationApplicationTests {
    @Test
    void contextLoads() {
    }

}
