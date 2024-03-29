package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 3, max = 16)
    @Pattern(regexp = "^[A-Za-z]+$", message = "Username состоит только из латинских букв")
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Size(min = 1, max = 16)
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name состоит только из латинских букв")
    @Column(name = "first_name")
    private String firstName;
    @Size(min = 1, max = 16)
    @Pattern(regexp = "^[A-Za-z]*$",  message = "Last name состоит только из латинских букв")
    @Column(name = "last_name")
    private String lastName;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role_id")
    private Role role;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_id")
    private Status status;
    @Column(name = "created_at")
    private LocalDate createdAt;


}
