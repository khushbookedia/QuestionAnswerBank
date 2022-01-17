package com.neosoft.QuestionAnswerBank.payload.request;

import com.neosoft.QuestionAnswerBank.Entity.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class SignupRequest {

    @Column(nullable = false, length = 30)
    private String username;

    @NotEmpty(message = "firstName must not be empty")
    private String firstName;

    @NotEmpty(message = "lastName must not be empty")
    private String lastName;

    @Column(length = 100)
    @NotEmpty(message = "email must not be empty")
    @Email(message = "Email should be a valid email")
    private String email;

    @Column(length = 120)
    @NotEmpty(message = "password must not be empty")
    private String password;

    @NotEmpty(message = "role must not be empty")
    private Set<String> roles;
}
