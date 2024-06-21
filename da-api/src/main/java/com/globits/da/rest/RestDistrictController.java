package com.globits.da.rest;

import com.globits.da.domain.District;
import com.globits.da.domain.Province;
import com.globits.da.dto.DistrictDto;
import com.globits.da.rest.response.ApiResponse;
import com.globits.da.service.impl.DistrictServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        ApiResponse<DistrictDto> apiResponse = new ApiResponse<>();

        District district = new District();

        Province province = new Province();
        province.setId(districtDto.getProvinceId());

        district.setName(districtDto.getName());
        district.setProvince(province);

        apiResponse.setData(new DistrictDto(districtService.save(district)));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<District>> updateDistrict(@PathVariable("id") UUID id, DistrictDto districtDto) {
        ApiResponse<District> apiResponse = new ApiResponse<>();
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
