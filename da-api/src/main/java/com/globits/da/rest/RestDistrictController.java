package com.globits.da.rest;

import com.globits.da.dto.DistrictDto;
import com.globits.da.rest.response.ApiResponse;
import com.globits.da.service.impl.DistrictServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/api/district")
@Validated
public class RestDistrictController {
    private final DistrictServiceImpl districtService;

    @Autowired
    public RestDistrictController(DistrictServiceImpl districtService) {
        this.districtService = districtService;
    }

    @GetMapping("/{pageIndex}/{pageSize}")
    public ResponseEntity<Page<DistrictDto>> getAllDistrict(@PathVariable("pageIndex") int pageIndex, @PathVariable("pageSize") int pageSize) {
        Page<DistrictDto> districts = districtService.getAllDistrict(pageIndex, pageSize);
        return new ResponseEntity<>(districts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DistrictDto>> getDistrictById(@PathVariable("id") UUID id) {
        ApiResponse<DistrictDto> apiResponse = new ApiResponse<>();
        if (districtService.findOneById(id) == null) {
            apiResponse.setHttpStatus(HttpStatus.NOT_FOUND);
            apiResponse.setErrorMessage("Entity not found with id: " + id);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }

        apiResponse.setData(new DistrictDto(districtService.findOneById(id)));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DistrictDto>> saveDistrict(@Valid @RequestBody DistrictDto districtDto) {
        return new ResponseEntity<>(districtService.save(districtDto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DistrictDto>> updateDistrict(@PathVariable("id") UUID id, @Valid @RequestBody DistrictDto districtDto) {
        ApiResponse<DistrictDto> apiResponse = new ApiResponse<>();
        if (districtService.findOneById(id) == null) {
            apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrorMessage("Entity not found with id: " + id);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        apiResponse.setData(districtService.update(id, districtDto));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<DistrictDto>> deleteDistrict(@PathVariable("id") UUID id) {
        ApiResponse<DistrictDto> apiResponse = new ApiResponse<>();
        if (districtService.findOneById(id) == null) {
            apiResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
            apiResponse.setErrorMessage("Entity not found with id: " + id);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        apiResponse.setData(new DistrictDto(districtService.delete(id)));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
