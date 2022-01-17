package com.neosoft.QuestionAnswerBank.payload.response;

import com.neosoft.QuestionAnswerBank.Entity.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import java.util.List;

@Getter
@Setter
public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private Long userId;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> roles;

    public JwtResponse(String token, Long userId, String username, String firstName, String lastName, String email, List<String> roles) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }
}
