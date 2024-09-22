package com.globits.da.service;

import com.globits.core.service.GenericService;
import com.globits.da.domain.Certificate;
import com.globits.da.dto.CertificateDto;
import com.globits.da.rest.response.ApiResponse;

import java.util.UUID;

public interface CertificateService extends GenericService<Certificate, UUID> {
    ApiResponse<Certificate> saveCertificate(CertificateDto certificateDto);
}
