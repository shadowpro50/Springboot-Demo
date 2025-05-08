package com.example.SpringWithPostgreSQL.repository;

import com.example.SpringWithPostgreSQL.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
