package com.globits.da.service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.search.EmployeeSearchDTO;
import com.globits.da.rest.response.ApiResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface EmployeeService extends GenericService<Employee, UUID> {
    ApiResponse<EmployeeDto> saveOrUpdate(EmployeeDto dto);

    EmployeeDto getEmployee(UUID id);

    List<EmployeeDto> getAllEmployee();

    Page<EmployeeDto> searchByMultipleCriteria(EmployeeSearchDTO employeeSearchDTO);
}
