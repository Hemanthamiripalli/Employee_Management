package com.employee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.models.Employees;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees, Long> {

	Optional<Employees> findByEmpEmail(String empEmail);

	Optional<Employees> findByEmpName(String empName);

	List<Employees> findByStatus(String status);

	boolean existsByEmpEmail(String empEmail);

}
