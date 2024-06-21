package com.globits.da.rest;

import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.search.EmployeeSearchDTO;
import com.globits.da.service.EmployeeService;
import com.globits.da.service.ExcelExportService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/employee")
@Validated
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
    public ResponseEntity<EmployeeDto> save(@Valid @RequestBody EmployeeDto dto) {
        try {
            if (dto.getId() != null) {
                EmployeeDto result = employeeService.saveOrUpdate(dto);
                return new ResponseEntity<EmployeeDto>(result, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        EmployeeDto result = employeeService.saveOrUpdate(dto);
        return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<EmployeeDto>> search(@Valid @RequestBody EmployeeSearchDTO searchDto) {
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("errorCode", HttpStatus.BAD_REQUEST.toString());
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

