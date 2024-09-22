package com.globits.da.rest;

import com.globits.da.domain.Certificate;
import com.globits.da.dto.CertificateDto;
import com.globits.da.rest.response.ApiResponse;
import com.globits.da.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/certificate")
public class RestCertificateController {
    private final CertificateService certificateService;

    @Autowired
    public RestCertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @PostMapping
    public ApiResponse<Certificate> post(@Valid @RequestBody CertificateDto certificateDto) {
        ApiResponse apiResponse = new ApiResponse();
        certificateService.saveCertificate(certificateDto);
        return apiResponse;
    }
}
