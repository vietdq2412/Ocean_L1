package com.globits.da.service.impl;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.da.domain.Commune;
import com.globits.da.domain.District;
import com.globits.da.domain.Employee;
import com.globits.da.domain.Province;
import com.globits.da.dto.EmployeeDto;
import com.globits.da.dto.search.EmployeeSearchDTO;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.repository.EmployeeRepository;
import com.globits.da.rest.response.ApiResponse;
import com.globits.da.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl extends GenericServiceImpl<Employee, UUID> implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ApiResponse<EmployeeDto> saveOrUpdate(EmployeeDto dto) {
        ApiResponse<EmployeeDto> apiResponse = new ApiResponse<>();

        if (dto != null) {
            Employee entity = null;
            if (dto.getId() != null) {
                entity = employeeRepository.getOne(dto.getId());
            }
            if (entity == null) {
                entity = new Employee();
            }
            entity.setCode(dto.getCode());
            entity.setName(dto.getName());
            entity.setAge(dto.getAge());
            entity.setPhone(dto.getPhone());
            entity.setEmail(dto.getEmail());

            Commune commune = new Commune();
            District district = new District();
            Province province = new Province();

            commune.setId(dto.getCommuneId());
            district.setId(dto.getDistrictId());
            province.setId(dto.getProvinceId());

            entity.setCommune(commune);
            entity.setDistrict(district);
            entity.setProvince(province);

            entity = employeeRepository.save(entity);
            apiResponse.setData(new EmployeeDto(entity));
        }
        return apiResponse;
    }

    @Override
    public EmployeeDto getEmployee(UUID id) {
        return new EmployeeDto(employeeRepository.getOne(id));
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        return employeeRepository.findAllEmployee();
    }

    /**
     * Search by keyword, age, id. Keyword is used to search for name, code, email, phone.
     * keyword, age, id are optional.
     *
     * @param employeeSearchDTO an object contains keyword, age, id
     * @return Page<EmployeeDto>
     */
    @Override
    public Page<EmployeeDto> searchByMultipleCriteria(EmployeeSearchDTO employeeSearchDTO) {
        if (employeeSearchDTO == null) {
            return null;
        }

        int pageIndex = employeeSearchDTO.getPageIndex();
        int pageSize = employeeSearchDTO.getPageSize();

        if (pageIndex > 0) {
            pageIndex--;
        } else {
            pageIndex = 0;
        }

        List<EmployeeDto> employeeDtoList = employeeRepository.search(
                employeeSearchDTO.getKeyword(),
                employeeSearchDTO.getAge(),
                employeeSearchDTO.getId()
        );

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<EmployeeDto> employeeDtos = new PageImpl<>(employeeDtoList, pageable, employeeDtoList.size());
        return employeeDtos;
    }
}
