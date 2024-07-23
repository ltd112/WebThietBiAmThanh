package com.iuh.webthietbiamthanh.models;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "User")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String role;


    // Getters and setters
}
