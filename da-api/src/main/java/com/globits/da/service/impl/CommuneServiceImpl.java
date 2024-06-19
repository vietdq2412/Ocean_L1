package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.Commune;
import com.globits.da.dto.CommuneDto;
import com.globits.da.repository.CommuneRepository;
import com.globits.da.service.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommuneServiceImpl extends GenericServiceImpl<Commune, UUID> implements CommuneService {
    @Autowired
    private CommuneRepository communeRepository;


    @Override
    public Commune findOneById(UUID id) {
        return communeRepository.findById(id).orElse(null);
    }

    @Override
    public Commune update(UUID id,CommuneDto communeDto) {
        if (communeDto != null && id != null) {
            Commune commune = communeRepository.findById(id).orElse(null);
            if (commune != null) {
                commune.setName(communeDto.getName());
                return communeRepository.save(commune);
            }
        }
        return null;
    }


}
