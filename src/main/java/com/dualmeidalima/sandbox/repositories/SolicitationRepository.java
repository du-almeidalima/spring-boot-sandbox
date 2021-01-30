package com.dualmeidalima.sandbox.repositories;

import com.dualmeidalima.sandbox.entities.Solicitation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitationRepository extends PagingAndSortingRepository<Solicitation, Integer> { }
