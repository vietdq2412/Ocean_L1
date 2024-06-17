package com.globits.da.rest;

import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.search.EmployeeSearchDTO;
import com.globits.da.service.EmployeeService;
import com.globits.da.service.ExcelExportService;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employee")
public class RestEmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ExcelExportService excelExportService;

    @GetMapping("")
    public ResponseEntity<List<EmployeeDto>> getAll() {
        List<EmployeeDto> result = employeeService.getAllEmployee();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<EmployeeDto> save(@RequestBody EmployeeDto dto) {
        EmployeeDto result = employeeService.saveOrUpdate(dto);
        return new ResponseEntity<EmployeeDto>(result, HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<EmployeeDto>> search(@RequestBody EmployeeSearchDTO searchDto) {
        Page<EmployeeDto> result = employeeService.searchByMultipleCriteria(searchDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UUID> delete(@PathVariable("id") UUID id) {
        employeeService.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }


    @GetMapping(value = "/export/excel")
    public void downloadExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=employees.xlsx";

        response.setHeader(headerKey, headerValue);

        List<EmployeeDto> listEmployees = employeeService.getAllEmployee();

        excelExportService.setWorkbook(new XSSFWorkbook());
        excelExportService.exportDataToExcel(listEmployees, response);
    }
}

