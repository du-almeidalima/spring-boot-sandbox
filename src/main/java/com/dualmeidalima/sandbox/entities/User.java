package com.dualmeidalima.sandbox.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity()
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "userSequenceGenerator")
    @GenericGenerator(
            name = "userSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "seq_users_id"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
