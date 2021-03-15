package com.paypal.bfs.test.employeeserv.impl;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.paypal.bfs.test.employeeserv.dao.EmployeeDTO;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDTO,Integer> {

	Optional<EmployeeDTO> findById(Integer id);

}
