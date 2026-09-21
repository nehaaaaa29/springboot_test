package com.TestingApp.Applocation.services.impl;

import com.TestingApp.Applocation.TestContainerConfiguration;
import com.TestingApp.Applocation.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestContainerConfiguration.class)
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
    @Mock
    private EmployeeService employeeServiceMock;

    @Mock
    private ModelMapper modelMapper;


    @InjectMocks
    private EmployeeServiceImpl employeeService;

   @Test
    void testgetEmployeeById_whenIdIsPresent_thenReturnEmployeeDto() {
       employeeService.getEmployeeById(1L);
   }



}