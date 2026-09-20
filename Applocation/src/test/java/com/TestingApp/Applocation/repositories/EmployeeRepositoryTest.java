package com.TestingApp.Applocation.repositories;

import com.TestingApp.Applocation.entities.Employee;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

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