package com.ChallengeApp.ChallengeApp.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String username;
    private String email;

    @JsonIgnore
    private String password;
    public User(String name) {
        this.username = name;
    }

    @ManyToMany
    private Set<Role> roles;

    public User(long id, String name) {
        this.username = name;
        this.id = id;
    }

    public User(String username, String email, String encode) {
        this.username = username;
        this.email = email;
        this.password = encode;
    }

}
