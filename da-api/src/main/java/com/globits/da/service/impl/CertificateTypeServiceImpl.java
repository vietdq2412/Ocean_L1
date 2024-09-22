package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.CertificateType;
import com.globits.da.service.CertificateTypeService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CertificateTypeServiceImpl  extends GenericServiceImpl<CertificateType, UUID> implements CertificateTypeService {
}
