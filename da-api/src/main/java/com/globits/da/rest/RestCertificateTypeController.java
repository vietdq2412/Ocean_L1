package com.globits.da.rest;

import com.globits.da.domain.CertificateType;
import com.globits.da.service.CertificateTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/certificateType")
public class RestCertificateTypeController {
    private final CertificateTypeService certificateTypeService;

    @Autowired
    public RestCertificateTypeController(CertificateTypeService certificateTypeService) {
        this.certificateTypeService = certificateTypeService;
    }

    @PostMapping
    public ResponseEntity<CertificateType> create(@Valid @RequestBody CertificateType certificateType) {
        return ResponseEntity.ok(certificateTypeService.save(certificateType));
    }
}
