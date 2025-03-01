package com.juaracoding.tugasakhir.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.juaracoding.tugasakhir.enums.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

/*
Created By IntelliJ IDEA 2024.3 (Community Edition)
Build #IC-243.21565.193, built on November 13, 2024
@Author USER Febby Tri Andika
Java Developer
Created on 15/02/2025 18:08
@Last Modified 15/02/2025 18:08
Version 1.0
*/
@Setter
@Getter
@Entity
@Table(name = "LogUser")
public class LogUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "IDUser")
    @JsonProperty("id-user")
    private Long idUser;

    @Column(name = "Username")
    private String username;
    @Column (name = "Email", length = 60, nullable = false )
    private String email;
    @Column(name = "NoHp", length = 16, nullable = false)
    private String noHp;
    @Column (name = "Address", length = 255, nullable = false)
    private String address;

    @Column(name = "CreatedBy",updatable = false,nullable = false)
    private String createdBy;
    @Column(name = "CreatedDate",updatable = false,nullable = false)
    private Date createdDate = new Date();

    @Column (name ="FirstName", length = 50, nullable = false)
    private String firstName;
    @Column (name = "LastName", length = 50, nullable = false)
    private String lastName;
    @ManyToOne
    @JoinColumn(name = "IDRole", foreignKey = @ForeignKey(name = "fk-logUser-to-role"))
    private Role role;

    @Column(name = "TanggalLahir")
    private LocalDate tanggalLahir;
    @Column(name = "Password", length = 60, nullable = false)
    private String password;

    @Column(name = "Flag",length = 1)
    private Character flag;
}
