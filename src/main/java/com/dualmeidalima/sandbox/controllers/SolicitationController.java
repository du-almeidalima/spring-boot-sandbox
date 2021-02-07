package com.dualmeidalima.sandbox.controllers;

import com.dualmeidalima.sandbox.entities.Solicitation;
import com.dualmeidalima.sandbox.enums.SolicitationStatus;
import com.dualmeidalima.sandbox.repositories.SolicitationRepository;
import com.dualmeidalima.sandbox.repositories.SolicitationViewRepository;
import com.dualmeidalima.sandbox.services.SolicitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/solicitations")
public class SolicitationController {

    private final SolicitationRepository solicitationRepository;
    private final SolicitationViewRepository solicitationViewRepository;
    private final SolicitationService solicitationService;

    @Autowired
    public SolicitationController(SolicitationRepository solicitationRepository,
                                  SolicitationViewRepository solicitationViewRepository,
                                  SolicitationService solicitationService) {
        this.solicitationRepository = solicitationRepository;
        this.solicitationViewRepository = solicitationViewRepository;
        this.solicitationService = solicitationService;
    }

    @GetMapping()
    public ResponseEntity<?> getSolicitations(
        @RequestParam(name = "name", required = false) String name,
        @PageableDefault(size = Integer.MAX_VALUE, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        var solicitations = this.solicitationViewRepository.findWithFilters(name, pageable);
        return ResponseEntity.ok(solicitations);
    }

    @PostMapping()
    public ResponseEntity<?> createSolicitation(
            @RequestBody Solicitation solicitation
    ){
        try {
            return ResponseEntity.ok(this.solicitationService.createSolicitation(solicitation));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PatchMapping("/approve/{id}")
    public ResponseEntity<?> approveSolicitation(
            @PathVariable Integer id
    ) throws Exception {
        var solicitation = this.solicitationRepository.findById(id).orElseThrow(
                () -> new Exception("Invalid Solicitation id")
        );

        solicitation.setStatus(SolicitationStatus.ACCEPTED);

        return ResponseEntity.ok(this.solicitationRepository.save(solicitation));
    }

    @PatchMapping("/refuse/{id}")
    public ResponseEntity<?> refuseSolicitation(
            @PathVariable Integer id
    ) throws Exception {
        var solicitation = this.solicitationRepository.findById(id).orElseThrow(
                () -> new Exception("Invalid Solicitation id")
        );

        solicitation.setStatus(SolicitationStatus.REFUSED);

        return ResponseEntity.ok(this.solicitationRepository.save(solicitation));
    }
}
