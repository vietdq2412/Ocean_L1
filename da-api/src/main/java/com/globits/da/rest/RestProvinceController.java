package com.globits.da.rest;

import com.globits.da.domain.Province;
import com.globits.da.dto.ProvinceDto;
import com.globits.da.rest.response.ApiResponse;
import com.globits.da.service.impl.ProvinceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
public class RestProvinceController {
    private static final int DEFAULT_PAGE_INDEX = 1;
    private static final int PAGE_SIZE = 2;
    @Autowired
    private ProvinceServiceImpl provinceService;

    @GetMapping
    public ResponseEntity<Page<Province>> getAllProvince() {
        return new ResponseEntity<>(provinceService.getList(DEFAULT_PAGE_INDEX, PAGE_SIZE), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Province>> getProvinceById(@PathVariable("id") UUID id) {
        ApiResponse<Province> apiResponse = new ApiResponse<>();
        if (provinceService.findOneById(id) == null) {
            apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrorMessage("Entity not found with id: " + id);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }

        apiResponse.setData(provinceService.findOneById(id));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Province> saveProvince(@Valid @RequestBody Province province) {
        return new ResponseEntity<>(provinceService.save(province), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Province>> updateProvince(@PathVariable("id") UUID id, ProvinceDto provinceDto) {
        ApiResponse<Province> apiResponse = new ApiResponse<>();
        if (provinceService.findOneById(id) == null) {
            apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrorMessage("Entity not found with id: " + id);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        apiResponse.setData(provinceService.update(id, provinceDto));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Province>> deleteProvince(@PathVariable("id") UUID id) {
        ApiResponse<Province> apiResponse = new ApiResponse<>();
        if (provinceService.findOneById(id) == null) {
            apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrorMessage("Entity not found with id: " + id);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        apiResponse.setData(provinceService.delete(id));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
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
