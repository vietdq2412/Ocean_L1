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
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Controller
@RequestMapping("/api/commune")
@Validated
public class RestCommuneController {
    private final CommuneServiceImpl communeService;
    private final DistrictServiceImpl districtService;

    @Autowired
    public RestCommuneController(CommuneServiceImpl communeService, DistrictServiceImpl districtService) {
        this.communeService = communeService;
        this.districtService = districtService;
    }

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


}
