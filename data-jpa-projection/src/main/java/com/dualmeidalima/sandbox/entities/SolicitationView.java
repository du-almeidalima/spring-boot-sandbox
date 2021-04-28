package com.dualmeidalima.sandbox.entities;

import com.dualmeidalima.sandbox.enums.SolicitationStatus;
import com.dualmeidalima.sandbox.enums.SolicitationType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vw_solicitations")
public class SolicitationView {
    @Id
    private Integer id;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private SolicitationType type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private SolicitationStatus status;

    @Column(name = "name")
    private String name;
}
