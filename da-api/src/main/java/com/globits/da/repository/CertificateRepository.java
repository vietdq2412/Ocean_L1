package com.globits.da.repository;

import com.globits.da.domain.Certificate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, UUID> {
    @Query("select new com.globits.da.dto.CertificateDto(e) from Certificate e")
    Page<Certificate> findAllCertificates(Pageable pageable);

    int countByCertificateTypeIdAndProvinceId(UUID certificateTypeId, UUID provinceId);
}
