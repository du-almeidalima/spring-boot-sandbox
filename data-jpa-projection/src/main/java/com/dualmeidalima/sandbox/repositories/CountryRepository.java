package com.dualmeidalima.sandbox.repositories;

import com.dualmeidalima.sandbox.entities.Country;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends PagingAndSortingRepository<Country, Integer> { }
