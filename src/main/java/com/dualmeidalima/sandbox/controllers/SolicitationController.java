package com.dualmeidalima.sandbox.controllers;

import com.dualmeidalima.sandbox.repositories.SolicitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/solicitations")
public class SolicitationController {

    private final SolicitationRepository solicitationRepository;

    @Autowired
    public SolicitationController(SolicitationRepository solicitationRepository) {
        this.solicitationRepository = solicitationRepository;
    }

    @GetMapping()
    public ResponseEntity<?> getEvents(
        @PageableDefault(size = Integer.MAX_VALUE, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        var solicitations = this.solicitationRepository.findAll(pageable);
        return ResponseEntity.ok(solicitations);
    }
}
