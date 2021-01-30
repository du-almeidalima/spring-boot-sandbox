package com.dualmeidalima.sandbox.repositories;

import com.dualmeidalima.sandbox.entities.Company;
import com.dualmeidalima.sandbox.entities.Country;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends PagingAndSortingRepository<Country, Integer> { }
