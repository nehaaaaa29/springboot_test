package com.TestingApp.Applocation.services.impl;

import com.TestingApp.Applocation.TestContainerConfiguration;
import com.TestingApp.Applocation.dto.EmployeeDto;
import com.TestingApp.Applocation.entities.Employee;
import com.TestingApp.Applocation.repositories.EmployeeRepository;
import com.TestingApp.Applocation.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestContainerConfiguration.class)
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @Spy
    private ModelMapper modelMapper;


    @InjectMocks
    private EmployeeServiceImpl employeeService;

   @Test
    void testgetEmployeeById_whenIdIsPresent_thenReturnEmployeeDto() {


       //assign
       Long id=1L;
       Employee mockEmployee =Employee.builder()
               .id(id)
               .email("nehaaa@gmail.com")
               .name("neha")
               .salary(100L)
               .build();

       when(employeeRepository.findById(id)).thenReturn(Optional.of(mockEmployee));
       //act

       EmployeeDto employeeDto = employeeService.getEmployeeById(id);

       //assert
      assertThat(employeeDto.getId()).isEqualTo(id);
      assertThat(employeeDto.getEmail()).isEqualTo(mockEmployee.getEmail());
      verify(employeeRepository,only()).findById(id);
   }
   @Test
    void testCreateNewEmployee_whenValidEmployee_ThenCreateNewEmployee(){
       //assign
       when(employeeRepository.findByEmail(""))

       //act

       //assert


   }


}