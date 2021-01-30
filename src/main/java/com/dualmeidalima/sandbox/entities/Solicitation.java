package com.dualmeidalima.sandbox.entities;

import com.dualmeidalima.sandbox.enums.SolicitationStatus;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
