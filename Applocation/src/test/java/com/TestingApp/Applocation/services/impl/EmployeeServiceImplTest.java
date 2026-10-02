package com.TestingApp.Applocation.services.impl;

import com.TestingApp.Applocation.TestContainerConfiguration;
import com.TestingApp.Applocation.dto.EmployeeDto;
import com.TestingApp.Applocation.entities.Employee;
import com.TestingApp.Applocation.exceptions.ResourceNotFoundException;
import com.TestingApp.Applocation.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
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
    void setUp() {
        mockEmployee = Employee.builder()
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
        verify(employeeRepository, only()).findById(1L);
    }

    @Test
    void testCreateNewEmployee_whenAttemptingToCreateEmployeeWithExistingEmail_thenThrowException() {
        //     arrange


        when(employeeRepository.findByEmail(mockEmployeeDto.getEmail())).thenReturn(List.of(mockEmployee));


        //    act and assert
        assertThatThrownBy(() -> employeeService.createNewEmployee(mockEmployeeDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Employee already exists with email: " + mockEmployee.getEmail());

        verify(employeeRepository).findByEmail(mockEmployeeDto.getEmail());
        verify(employeeRepository, never()).save(any());

    }

    @Test
    void testGetEmployeeById_whenEmployeeIsNotPresent_thenThrowException() {
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.getEmployeeById(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Employee not found with id: 1");

        verify(employeeRepository).findById(1L);
    }

    @Test
    void testUpdateEmployee_whenEmployeeDoesNotExists_thenThrowException() {
        //    arrange
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        //act and assert

        assertThatThrownBy(() -> employeeService.updateEmployee(1L, mockEmployeeDto))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: 1");

        verify(employeeRepository).findById(1L);
        verify(employeeRepository, never()).save(any());

    }

    @Test
    void testUpdateEmployee_whenAttemptingToUpdateEmail_thenThrowException() {

//arrange
        when(employeeRepository.findById(mockEmployeeDto.getId())).thenReturn(Optional.of(mockEmployee));
        mockEmployeeDto.setName("Random");
        mockEmployeeDto.setEmail("random@gmail.com");
        //act assert

        assertThatThrownBy(() -> employeeService.updateEmployee(mockEmployeeDto.getId(), mockEmployeeDto))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("The email of the employee cannot be updated");

        verify(employeeRepository).findById(mockEmployeeDto.getId());
        verify(employeeRepository, never()).save(any());


    }
    @Test
    void testUpdateEmployee_whenValidEmployee_thenUpdateEmployee(){
        when(employeeRepository.findById(mockEmployeeDto.getId())).thenReturn(Optional.of(mockEmployee));
        mockEmployeeDto.setName("random name");
        mockEmployeeDto.setSalary(199L);

        Employee newEmployeeDto = modelMapper.map(mockEmployee, Employee.class);
        when(employeeRepository.save(any(Employee.class))).thenReturn(newEmployeeDto);
        //act
        EmployeeDto updatedEmployeeDto = employeeService.updateEmployee(mockEmployeeDto.getId(), mockEmployeeDto);
        //assert
        assertThat(updatedEmployeeDto).isEqualTo(mockEmployeeDto);

        verify(employeeRepository).findById(1L);
        verify(employeeRepository).save(any());


    }
    @Test
    void testDeleteEmployee_whenEMployeeDoesNotExists_thenThrowException(){
       when(employeeRepository.existsById(1L)).thenReturn(false);

       //act
        assertThatThrownBy(()-> employeeService.deleteEmployee(1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Employee not found with id: 1");
        verify(employeeRepository,never()).deleteById(anyLong());
    }
    @Test
    void testDeleteEmployee_whenEmployeeExists_thenDeleteEmployee(){
        when(employeeRepository.existsById(1L)).thenReturn(true);

        //act
       assertThatCode(() -> employeeService.deleteEmployee(1L))
               .doesNotThrowAnyException();

        //assert
        verify(employeeRepository).deleteById(1L);
    }


}