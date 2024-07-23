package com.iuh.webthietbiamthanh.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class UserCreationRequest {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;

}
