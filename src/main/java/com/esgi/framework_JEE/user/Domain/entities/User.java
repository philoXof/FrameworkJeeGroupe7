package com.esgi.framework_JEE.user.Domain.entities;
import javax.persistence.*;

import com.esgi.framework_JEE.role.domain.entity.Role;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "user_share")
public class User {

    @Id
    @Setter(AccessLevel.NONE)
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permission")
    private Role permission;
}
