package com.TestingApp.Applocation.services.impl;

import com.TestingApp.Applocation.TestContainerConfiguration;
import com.TestingApp.Applocation.dto.EmployeeDto;
import com.TestingApp.Applocation.entities.Employee;
import com.TestingApp.Applocation.repositories.EmployeeRepository;
import com.TestingApp.Applocation.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
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

    private Employee mockEmployee;
    private EmployeeDto mockEmployeeDto;
    @BeforeEach
    void setUp(){
        mockEmployee=Employee.builder()
                .id(1L)
                .email("nehaaa@gmail.com")
                .name("neha")
                .salary(100L)
                .build();
        mockEmployeeDto = modelMapper.map(mockEmployee, EmployeeDto.class);
    }

   @Test
    void testgetEmployeeById_whenIdIsPresent_thenReturnEmployeeDto() {




       when(employeeRepository.findById(1L)).thenReturn(Optional.of(mockEmployee));
       //act

       EmployeeDto employeeDto = employeeService.getEmployeeById(1L);

       //assert
      assertThat(employeeDto.getId()).isEqualTo(1L);
      assertThat(employeeDto.getEmail()).isEqualTo(mockEmployee.getEmail());
      verify(employeeRepository,only()).findById(1L);
   }
    @Test
    void testCreateNewEmployee_whenAttemptingToCreateEmployeeWithExistingEmail_thenThrowException(){
   //     arrange


        when(employeeRepository.findByEmail(mockEmployeeDto.getEmail())).thenReturn(List.of(mockEmployee));


    //    act and assert
        assertThatThrownBy(()->employeeService.createNewEmployee(mockEmployeeDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Employee already exists with email: " + mockEmployee.getEmail());

        verify(employeeRepository).findByEmail(mockEmployeeDto.getEmail());
        verify(employeeRepository,never()).save(any());

    }
     @Test
     void testGetEmployeeById_whenEmployeeIsNotPresent_thenThrowException(){
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(()->employeeService.getEmployeeById(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Employee not found with id: 1");

        verify(employeeRepository).findById(1L);
     }







}