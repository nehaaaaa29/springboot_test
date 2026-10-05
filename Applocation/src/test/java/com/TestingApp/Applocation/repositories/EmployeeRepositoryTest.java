package com.TestingApp.Applocation.repositories;

import com.TestingApp.Applocation.TestContainerConfiguration;
import com.TestingApp.Applocation.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

@Import(TestContainerConfiguration.class)
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    private Employee employee;

    @BeforeEach
    void setup(){
        employee=Employee.builder()
                .name("neha")
                .email("nehaa@gmail.com")
                .salary(100L)
                .build();
    }
    @Test
    void testfindByEmail_whenEmailIsValid_thenReturnEmployee() {
        //arrange
        employeeRepository.save(employee);

        //act
        List<Employee> employeeList = employeeRepository.findByEmail(employee.getEmail());


        //assert
      assertThat(employeeList).isNotNull();
      assertThat(employeeList).isNotEmpty();
      assertThat(employeeList.get(0).getEmail()).isEqualTo(employee.getEmail());

    }

    @Test
    void testfindByEmail_whenEmailIsInvalid_thenReturnEmptyList() {

      //given
        String email="notpresent.123@gmail.com";

      //when
        List<Employee> employeeList =employeeRepository.findByEmail(email);

      //then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList).isEmpty();






    }
}