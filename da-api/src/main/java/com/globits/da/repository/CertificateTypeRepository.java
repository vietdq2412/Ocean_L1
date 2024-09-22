package com.globits.da.repository;

import com.globits.da.domain.CertificateType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CertificateTypeRepository extends JpaRepository<CertificateType, UUID> {

}
