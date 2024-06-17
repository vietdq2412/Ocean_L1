package com.globits.da.repository;

import com.globits.da.domain.Employee;
import com.globits.da.dto.EmployeeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query(value = "SELECT new com.globits.da.dto.EmployeeDto(e) FROM Employee e " +
            "WHERE " +
            "(" +
            ":keyword IS NULL " +
            "OR e.name LIKE "+"%"+":keyword"+"% " +
            "OR e.code LIKE "+"%"+":keyword"+"% " +
            "OR e.email LIKE "+"%"+":keyword"+"% " +
            "OR e.phone LIKE "+"%"+":keyword"+"% " +
            ") " +
            "and (:age = 0 OR e.age = :age) " +
            "and (:id is NULL or e.id = :id) " +
            "ORDER BY e.createDate DESC "
    )
    public List<EmployeeDto> search(@Param("keyword") String keyword,
                                    @Param("age") int age,
                                    @Param("id") UUID id
    );

    @Query("select new com.globits.da.dto.EmployeeDto(ed) from Employee ed")
    List<EmployeeDto> findAllEmployee();


}
