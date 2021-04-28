package com.dualmeidalima.sandbox.services;

import com.dualmeidalima.sandbox.entities.Solicitation;
import com.dualmeidalima.sandbox.enums.SolicitationType;
import com.dualmeidalima.sandbox.repositories.CompanyRepository;
import com.dualmeidalima.sandbox.repositories.SolicitationRepository;
import com.dualmeidalima.sandbox.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitationService {
    private final SolicitationRepository solicitationRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public SolicitationService(SolicitationRepository solicitationRepository,
                               UserRepository userRepository,
                               CompanyRepository companyRepository
    ) {
        this.solicitationRepository = solicitationRepository;
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
    }

    public Solicitation createSolicitation(Solicitation solicitation) throws Exception {
        this.validateSolicitation(solicitation);

        if (solicitation.getType() == SolicitationType.USER) {
            solicitation.getUser().setSolicitation(solicitation);
        } else {
            solicitation.getCompany().setSolicitation(solicitation);
        }

        return this.solicitationRepository.save(solicitation);
    }

    private void validateSolicitation(Solicitation solicitation) throws Exception {
        if (solicitation.getType() == SolicitationType.USER && solicitation.getUser() == null) {
            throw new Exception("User solicitation with empty user");
        }

        if (solicitation.getType() == SolicitationType.COMPANY && solicitation.getCompany() == null) {
            throw new Exception("Company solicitation with empty company");
        }

        if (solicitation.getType() == SolicitationType.USER &&
            solicitation.getUser() == null &&
            solicitation.getCompany() != null
        ) {
            throw new Exception("User solicitation with Company");
        }

        if (solicitation.getType() == SolicitationType.COMPANY &&
                solicitation.getCompany() == null &&
                solicitation.getUser() != null
        ) {
            throw new Exception("Company solicitation with User");
        }

        if (solicitation.getType() == SolicitationType.USER) {
            var user = this.userRepository.findByEmail(solicitation.getUser().getEmail());
            if (user.isPresent()) {
                throw new Exception("User already created");
            }
        }

        if (solicitation.getType() == SolicitationType.COMPANY) {
            var company = this.companyRepository.findCompanyByIdentifier(
                    solicitation.getCompany().getIdentifier()
            );
            if (company.isPresent()) {
                throw new Exception("User already created");
            }
        }
    }
}
