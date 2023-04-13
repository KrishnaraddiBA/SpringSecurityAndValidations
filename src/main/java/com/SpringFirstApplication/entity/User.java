package com.SpringFirstApplication.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.data.repository.cdi.Eager;

import java.util.Set;

@Data
@Entity
@Table(name="users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    @Email(message = "email id is inapropriate please check")
    private String email;
    private String name;
    private String password;
    @Column(unique = true)
    private String username;


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)

    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName
                    = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id"))
    private Set<Role> roles;
}
