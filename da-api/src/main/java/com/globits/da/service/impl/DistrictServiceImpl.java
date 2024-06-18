package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.District;
import com.globits.da.repository.DistrictRepository;
import com.globits.da.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DistrictServiceImpl extends GenericServiceImpl<District, UUID> implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;
}
