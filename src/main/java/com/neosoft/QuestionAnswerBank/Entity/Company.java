package com.neosoft.QuestionAnswerBank.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@SQLDelete(sql = "UPDATE company SET is_active=1 WHERE company_id=?")
@Where(clause = "is_active=0")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;

    @Column(length = 50)
    @NotEmpty(message = "company name must not be empty")
    private String companyName;

    private boolean isActive = Boolean.FALSE;

    @OneToMany(mappedBy = "companyName")
    private List<Question> question;

}
