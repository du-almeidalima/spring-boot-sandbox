package com.dualmeidalima.sandbox.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Data
@Entity()
@Table(name = "companies")
public class Company {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "companySequenceGenerator")
    @GenericGenerator(
            name = "companySequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "seq_companies_id"),
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "identifier")
    private String identifier;

    @ManyToOne()
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "solicitation_id", referencedColumnName = "id")
    @JsonIgnore
    private Solicitation solicitation;
}
