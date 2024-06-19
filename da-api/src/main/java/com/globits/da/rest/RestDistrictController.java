package com.globits.da.rest;

import com.globits.da.domain.District;
import com.globits.da.dto.DistrictDto;
import com.globits.da.rest.response.ApiResponse;
import com.globits.da.service.impl.DistrictServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/district")
public class RestDistrictController {

    @Autowired
    private DistrictServiceImpl districtService;

    @GetMapping
    public ResponseEntity<Page<District>> getAllDistrict() {
        return new ResponseEntity<>(districtService.getList(1, 2), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<District>> getDistrictById(@PathVariable("id") UUID id) {
        ApiResponse<District> apiResponse = new ApiResponse<>();
        if (districtService.findOneById(id) == null) {
            apiResponse.setErrorCode(HttpStatus.NOT_FOUND.toString());
            apiResponse.setErrorMessage("Entity not found with id: " + id);
            return new ResponseEntity<>( apiResponse,HttpStatus.OK);
        }

        apiResponse.setData(districtService.findOneById(id));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<District> saveDistrict(@RequestBody District district) {
        return new ResponseEntity<>(districtService.save(district), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<District>> updateDistrict(@PathVariable("id") UUID id, DistrictDto districtDto) {
        ApiResponse<District> apiResponse = new ApiResponse<>();
        if (districtService.findOneById(id) == null) {
            apiResponse.setErrorCode("404");
            apiResponse.setErrorMessage("Entity not found with id: " + id);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        apiResponse.setData(districtService.update(id,districtDto));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<District>> deleteDistrict(@PathVariable("id") UUID id) {
        ApiResponse<District> apiResponse = new ApiResponse<>();
        if (districtService.findOneById(id) == null) {
            apiResponse.setErrorCode("404");
            apiResponse.setErrorMessage("Entity not found with id: " + id);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        apiResponse.setData(districtService.delete(id));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
