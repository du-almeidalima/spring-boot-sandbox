package com.dualmeidalima.sandbox.repositories;

import com.dualmeidalima.sandbox.entities.SolicitationView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface SolicitationViewRepository extends PagingAndSortingRepository<SolicitationView, Integer> {

    @Query(
            " FROM SolicitationView s" +
            " WHERE (:pName IS NULL AND :pName <> '') OR UPPER(s.name) LIKE CONCAT('%', UPPER(:pName) ,'%')"
    )
    Page<SolicitationView> findWithFilters(@Param("pName") String name, Pageable pageable);
}
