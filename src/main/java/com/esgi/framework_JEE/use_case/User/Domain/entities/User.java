package com.esgi.framework_JEE.use_case.User.Domain.entities;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "user_share")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            updatable = false
    )
    private int id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "password")
    private String password;
}
