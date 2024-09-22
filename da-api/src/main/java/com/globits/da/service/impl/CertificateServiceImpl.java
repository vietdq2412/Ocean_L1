package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.Certificate;
import com.globits.da.dto.CertificateDto;
import com.globits.da.repository.CertificateRepository;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.repository.ProvinceRepository;
import com.globits.da.rest.response.ApiResponse;
import com.globits.da.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CertificateServiceImpl extends GenericServiceImpl<Certificate, UUID> implements CertificateService {
    private final CertificateRepository certificateRepository;
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final CommuneRepository communeRepository;

    @Autowired
    public CertificateServiceImpl(CertificateRepository certificateRepository, ProvinceRepository provinceRepository, DistrictRepository districtRepository, CommuneRepository communeRepository) {
        this.certificateRepository = certificateRepository;
        this.provinceRepository = provinceRepository;
        this.districtRepository = districtRepository;
        this.communeRepository = communeRepository;
    }


    @Override
    public ApiResponse<Certificate> saveCertificate(CertificateDto certificateDto) {
        ApiResponse<Certificate> apiResponse = new ApiResponse<>();



        Certificate certificate = new Certificate();
        certificate.setId(certificateDto.getId());
        return null;
    }
}
