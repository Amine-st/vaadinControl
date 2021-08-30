/*
package com.example.application.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String authority;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Person person;

    public Authority() {

    }
    public Authority(String username, String role, Person utilisateur) {
        this.username = username;
        this.authority = role;
        this.person = utilisateur;
    }
}//
*/
