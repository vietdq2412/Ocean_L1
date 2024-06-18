package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.Commune;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.service.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommuneServiceImpl extends GenericServiceImpl<Commune, UUID> implements CommuneService {
    @Autowired
    private CommuneRepository communeRepository;


}
