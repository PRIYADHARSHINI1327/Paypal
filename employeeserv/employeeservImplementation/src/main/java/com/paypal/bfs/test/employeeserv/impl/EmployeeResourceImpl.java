package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.dao.AddressDTO;
import com.paypal.bfs.test.employeeserv.dao.EmployeeDTO;
import com.paypal.bfs.test.employeeserv.dao.EmployeeStatusDTO;
import com.paypal.bfs.test.employeeserv.utils.EmployeeException;

import ch.qos.logback.core.status.Status;
import io.swagger.annotations.ApiResponse;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import com.paypal.bfs.test.employeeserv.utils.EmployeeNotFoundException;
import com.paypal.bfs.test.employeeserv.utils.InvalidEmployeeDobException;
/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {
	@Autowired 
	private EmployeeRepository emprep;
	
    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {
    	Employee u_emp=new Employee();
    	
    	Integer ids=Integer.parseInt(id);
    	Optional<EmployeeDTO> empdtlss=emprep.findById(ids);
    	
    	if(!emprep.existsById(ids)) {
    		throw new EmployeeNotFoundException("Employee id "+ids+" is not available");
    	}
    	EmployeeDTO empdtls=empdtlss.get();
    	
		u_emp.setId(empdtls.getId());
		u_emp.setFirstName(empdtls.getFirstName());
		u_emp.setLastName(empdtls.getLastName());
		u_emp.setDob(empdtls.getDob());
		Address add=new Address();
		add.setLine1(empdtls.getAddress().getLine1());
		add.setLine2(empdtls.getAddress().getLine2());
		add.setCity(empdtls.getAddress().getCity());
		add.setState(empdtls.getAddress().getState());
		add.setCountry(empdtls.getAddress().getCountry());
		add.setZipCode(empdtls.getAddress().getZipCode());
		u_emp.setAddress(add);
		return new ResponseEntity<>(u_emp, HttpStatus.OK);
    	
    	 
    }

	@Override
	public ResponseEntity<Object> addEmployee(Employee employee) {
		
		EmployeeDTO emp=new EmployeeDTO();
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setDob(employee.getDob());
		if(!EmployeeResourceImpl.isValidDate(employee.getDob())) {
			throw new InvalidEmployeeDobException("Invalid Date of Birth and the valid one is dd/MM/yyyy");
		}
		AddressDTO addrs=new AddressDTO();
		addrs.setLine1(employee.getAddress().getLine1());
		addrs.setLine2(employee.getAddress().getLine2());
		addrs.setCity(employee.getAddress().getCity());
		addrs.setState(employee.getAddress().getState());
		addrs.setCountry(employee.getAddress().getCountry());
		addrs.setZipCode(employee.getAddress().getZipCode());
		emp.setAddress(addrs);
		EmployeeDTO empdtls = emprep.save(emp);
		Employee u_emp=new Employee();
		u_emp.setId(empdtls.getId());
		u_emp.setFirstName(empdtls.getFirstName());
		u_emp.setLastName(empdtls.getLastName());
		u_emp.setDob(empdtls.getDob());
		Address add=new Address();
		add.setLine1(empdtls.getAddress().getLine1());
		add.setLine2(empdtls.getAddress().getLine2());
		add.setCity(empdtls.getAddress().getCity());
		add.setState(empdtls.getAddress().getState());
		add.setCountry(empdtls.getAddress().getCountry());
		add.setZipCode(empdtls.getAddress().getZipCode());
		u_emp.setAddress(add);
		
		return new ResponseEntity<>("Employee Data Saved Successfully",HttpStatus.OK);
		
	}

	@Override
	public ResponseEntity<Object> deleteEmployee(String id) {
		if(!emprep.existsById(Integer.parseInt(id))) {
    		throw new EmployeeNotFoundException("Employee id "+id+" is not available");
    	}
		emprep.deleteById(Integer.parseInt(id));
		return new ResponseEntity<>("Employee id"+id+" is deleted Successfully",HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> updateEmployee(Employee employee) {
		Optional<EmployeeDTO> empdtlss=emprep.findById(employee.getId());
		if(null != empdtlss) {
			EmployeeDTO emp=new EmployeeDTO();
			emp.setFirstName(employee.getFirstName());
			emp.setLastName(employee.getLastName());
			emp.setDob(employee.getDob());
			AddressDTO addrs=new AddressDTO();
			addrs.setLine1(employee.getAddress().getLine1());
			addrs.setLine2(employee.getAddress().getLine2());
			addrs.setCity(employee.getAddress().getCity());
			addrs.setState(employee.getAddress().getState());
			addrs.setCountry(employee.getAddress().getCountry());
			addrs.setZipCode(employee.getAddress().getZipCode());
			emp.setAddress(addrs);
			EmployeeDTO empdtls = emprep.save(emp);
			Employee u_emp=new Employee();
			u_emp.setId(empdtls.getId());
			u_emp.setFirstName(empdtls.getFirstName());
			u_emp.setLastName(empdtls.getLastName());
			u_emp.setDob(empdtls.getDob());
			Address add=new Address();
			add.setLine1(empdtls.getAddress().getLine1());
			add.setLine2(empdtls.getAddress().getLine2());
			add.setCity(empdtls.getAddress().getCity());
			add.setState(empdtls.getAddress().getState());
			add.setCountry(empdtls.getAddress().getCountry());
			add.setZipCode(empdtls.getAddress().getZipCode());
			u_emp.setAddress(add);
			return new ResponseEntity<>("Employee id "+employee.getId()+" is updated successfully",HttpStatus.OK);
			
		}
		else {
			throw new EmployeeNotFoundException("Empoyee id"+employee.getId()+"is not available to update");
		}
	} 
	
	public static boolean isValidDate(String d) 
    { 
        String regex = "^(1[0-2]|0[1-9])/(3[01]"
                       + "|[12][0-9]|0[1-9])/[0-9]{4}$"; 
        Pattern pattern = Pattern.compile(regex); 
        Matcher matcher = pattern.matcher((CharSequence)d); 
        return matcher.matches(); 
    }

}

