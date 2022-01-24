package com.neosoft.QuestionAnswerBank.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;

    @Column(length = 1000)
    @NotEmpty(message = "description must not be empty")
    private String description;

    @Column(length = 1000)
    @NotEmpty(message = "answer must not be empty")
    private String answer;

    @Column(length = 100)
    @NotEmpty(message = "company name must not be empty")
    private String companyName;


    private Long userId;
}
