package com.globits.da.service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.search.EmployeeSearchDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface EmployeeService extends GenericService<Employee, UUID>{
    public EmployeeDto saveOrUpdate(EmployeeDto dto);
    public EmployeeDto getEmployee(UUID id);
    public List<EmployeeDto> getAllEmployee();
    public Page<EmployeeDto> searchByMultipleCriteria(EmployeeSearchDTO employeeSearchDTO);
}
