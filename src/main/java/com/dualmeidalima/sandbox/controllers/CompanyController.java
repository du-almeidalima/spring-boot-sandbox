package com.dualmeidalima.sandbox.controllers;

import com.dualmeidalima.sandbox.repositories.CompanyRepository;
import com.dualmeidalima.sandbox.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyRepository companyRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public CompanyController(CompanyRepository solicitationRepository, CountryRepository countryRepository) {
        this.companyRepository = solicitationRepository;
        this.countryRepository = countryRepository;
    }

    @GetMapping()
    public ResponseEntity<?> getCompanies(
            @PageableDefault(size = Integer.MAX_VALUE, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        var companies = this.companyRepository.findAll(pageable);
        return ResponseEntity.ok(companies);
    }

    @GetMapping(value = "/by-country/{id}")
    public ResponseEntity<?> getCompanyByCountry( @PathVariable Integer id) {
        var country = this.countryRepository.findById(id);

        if (country.isPresent()) {
            var company = this.companyRepository.findCompanyByCountryEquals(country.get());
            return ResponseEntity.ok(company);
        }

        return ResponseEntity.notFound().build();
    }
}
