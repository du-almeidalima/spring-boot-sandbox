package com.dualmeidalima.sandbox.repositories;

import com.dualmeidalima.sandbox.dto.CompanyDTO;
import com.dualmeidalima.sandbox.entities.Company;
import com.dualmeidalima.sandbox.entities.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends PagingAndSortingRepository<Company, Integer> {
    Optional<Company> findCompanyByCountryEquals(Country country);
    Optional<Company> findCompanyByIdentifier(String identifier);

    @Query(value = "SELECT c.name as nominho, c.identifier as identificador" +
            " FROM Company c" +
            " WHERE (:pName IS NULL OR :pName = '') OR UPPER(c.name) LIKE CONCAT('%', UPPER(:pName), '%')")
    Page<CompanyDTO> findCompanyDTO(@Param("pName") String name,
                                    Pageable pageable);
}
