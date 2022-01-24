package com.neosoft.QuestionAnswerBank.Controller;

import com.neosoft.QuestionAnswerBank.Entity.Company;
import com.neosoft.QuestionAnswerBank.Entity.Question;
import com.neosoft.QuestionAnswerBank.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/all")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/company")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<List<Company>> getAllCompanies(){
        return ResponseEntity.ok(userService.getAllCompanies());
    }


    @GetMapping("/questions")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return ResponseEntity.ok(userService.getAllQuestions());
    }

    @GetMapping("/questions/{companyName}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<List<Question>> getQuestionsByCompanyName(@PathVariable String companyName){

        return ResponseEntity.ok(userService.getQuestionByCompanyName(companyName));
    }

}
