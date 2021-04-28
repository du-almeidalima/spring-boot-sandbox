package com.dualmeidalima.sandbox.entities;

import com.dualmeidalima.sandbox.enums.SolicitationStatus;
import com.dualmeidalima.sandbox.enums.SolicitationType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity()
@Table(name = "solicitations")
public class Solicitation {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "solicitationSequenceGenerator")
    @GenericGenerator(
            name = "solicitationSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "seq_solicitations_id"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private int id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private SolicitationStatus status = SolicitationStatus.PENDING;

    @Column(name = "type", updatable = false)
    @Enumerated(EnumType.STRING)
    private SolicitationType type;

    @OneToOne(mappedBy = "solicitation", cascade = CascadeType.PERSIST)
    private User user;

    @OneToOne(mappedBy = "solicitation", cascade = CascadeType.PERSIST)
    private Company company;
}
