package com.neosoft.QuestionAnswerBank.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE question SET is_active=1 WHERE question_id=?")
@Where(clause = "is_active=0")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    @Column(length = 1000)
    @NotNull(message = "description must not be empty")
    private String description;

    @Column(length = 1000)
    @NotNull(message = "answer must not be empty")
    private String answer;

    //@JoinTable(name="company_questions", joinColumns = @JoinColumn(name = "question_id"), inverseJoinColumns = @JoinColumn(name = "company_id"))
    @Column(length = 100)
    @NotNull(message = "company name must not be empty")
    private String companyName;

    private boolean isActive = Boolean.FALSE;


}
