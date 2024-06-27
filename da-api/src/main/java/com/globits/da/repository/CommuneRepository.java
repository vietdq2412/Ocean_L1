package com.globits.da.repository;

import com.globits.da.domain.Commune;
import com.globits.da.dto.CommuneDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CommuneRepository extends JpaRepository<Commune, UUID>{
    @Query("select new com.globits.da.dto.CommuneDto(ed) from Commune ed")
    List<CommuneDto> findAllCommune();
    Boolean existsByName(String name);

}
