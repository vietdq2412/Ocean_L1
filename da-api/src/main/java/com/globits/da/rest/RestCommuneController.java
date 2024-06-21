package com.globits.da.rest;

import com.globits.da.domain.Commune;
import com.globits.da.dto.CommuneDto;
import com.globits.da.rest.response.ApiResponse;
import com.globits.da.service.impl.CommuneServiceImpl;
import com.globits.da.service.impl.DistrictServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/api/commune")
public class RestCommuneController {
    @Autowired
    private CommuneServiceImpl communeService;

    @Autowired
    private DistrictServiceImpl districtService;

    @GetMapping
    public ResponseEntity<Page<Commune>> getAllCommune() {
        return new ResponseEntity<>(communeService.getList(1, 2), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Commune>> getCommuneById(@PathVariable("id") UUID id) {
        ApiResponse<Commune> apiResponse = new ApiResponse<>();
        if (communeService.findOneById(id) == null) {
            apiResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            apiResponse.setErrorMessage("Entity not found with id: " + id);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }

        apiResponse.setData(communeService.findOneById(id));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CommuneDto>> saveCommune(@Valid @RequestBody CommuneDto communeDto) {
        ApiResponse<CommuneDto> apiResponse = communeService.save(communeDto);
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Commune>> updateCommune(@PathVariable("id") UUID id, CommuneDto communeDto) {
        ApiResponse<Commune> apiResponse = new ApiResponse<>();
        if (communeService.findOneById(id) == null) {
            apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrorMessage("Commune is not found with id: " + id);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        apiResponse.setData(communeService.update(id, communeDto));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Commune>> deleteCommune(@PathVariable("id") UUID id) {
        ApiResponse<Commune> apiResponse = new ApiResponse<>();
        if (communeService.findOneById(id) == null) {
            apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrorMessage("Entity not found with id: " + id);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        apiResponse.setData(communeService.delete(id));
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


    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>("Invalid JSON request" + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
