package com.paypal.bfs.test.employeeserv.api;

import com.paypal.bfs.test.employeeserv.api.model.Employee;

//import io.swagger.annotations.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Interface for employee resource operations.
 */
public interface EmployeeResource{

    /**
     * Retrieves the {@link Employee} resource by id.
     *
     * @param id employee id.
     * @return {@link Employee} resource.
     */
    @RequestMapping("/v1/bfs/employees/{id}")
    ResponseEntity<Employee> employeeGetById(@PathVariable("id") String id);
    // ----------------------------------------------------------
    // TODO - add a new operation for creating employee resource.
    // ----------------------------------------------------------
    @PostMapping("/v1/bfs/employees/add")
    ResponseEntity<Object> addEmployee(@RequestBody Employee employee);
    
    @PostMapping("/v1/bfs/employees/delete")
    ResponseEntity<Object> deleteEmployee(@PathVariable("id") String id);
    
    @PostMapping("/v1/bfs/employees/update")
    ResponseEntity<Object> updateEmployee(@RequestBody Employee employee);
    
}
