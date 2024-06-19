package com.globits.da.rest;

import com.globits.da.domain.Commune;
import com.globits.da.dto.CommuneDto;
import com.globits.da.rest.response.ApiResponse;
import com.globits.da.service.impl.CommuneServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/commune")
public class RestCommuneController {

    @Autowired
    private CommuneServiceImpl communeService;

    @GetMapping
    public ResponseEntity<Page<Commune>> getAllCommune() {
        return new ResponseEntity<>(communeService.getList(1, 2), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Commune>> getCommuneById(@PathVariable("id") UUID id) {
        ApiResponse<Commune> apiResponse = new ApiResponse<>();
        if (communeService.findOneById(id) == null) {
            apiResponse.setErrorCode(HttpStatus.NOT_FOUND.toString());
            apiResponse.setErrorMessage("Entity not found with id: " + id);
            return new ResponseEntity<>( apiResponse,HttpStatus.OK);
        }

        apiResponse.setData(communeService.findOneById(id));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Commune> saveCommune(@RequestBody Commune commune) {
        return new ResponseEntity<>(communeService.save(commune), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Commune>> updateCommune(@PathVariable("id") UUID id, CommuneDto communeDto) {
        ApiResponse<Commune> apiResponse = new ApiResponse<>();
        if (communeService.findOneById(id) == null) {
            apiResponse.setErrorCode("404");
            apiResponse.setErrorMessage("Entity not found with id: " + id);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        apiResponse.setData(communeService.update(id,communeDto));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Commune>> deleteCommune(@PathVariable("id") UUID id) {
        ApiResponse<Commune> apiResponse = new ApiResponse<>();
        if (communeService.findOneById(id) == null) {
            apiResponse.setErrorCode("404");
            apiResponse.setErrorMessage("Entity not found with id: " + id);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }

        apiResponse.setData(communeService.delete(id));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
