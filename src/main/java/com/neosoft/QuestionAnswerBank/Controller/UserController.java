package com.neosoft.QuestionAnswerBank.Controller;

import com.neosoft.QuestionAnswerBank.Entity.Company;
import com.neosoft.QuestionAnswerBank.Entity.Question;
import com.neosoft.QuestionAnswerBank.Entity.QuestionRequest;
import com.neosoft.QuestionAnswerBank.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;


    @GetMapping("/company")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Company>> getAllCompanies(){
        return ResponseEntity.ok(userService.getAllCompanies());
    }



    @GetMapping("/questions")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return ResponseEntity.ok(userService.getAllQuestions());
    }


    @PostMapping("/requests")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<QuestionRequest> addQuestionRequest(@Valid @RequestBody QuestionRequest questionRequest){
        try{
            return ResponseEntity.ok(userService.addQuestionRequest(questionRequest));

        }
            catch(DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException(ex.getLocalizedMessage());
        }
    }



    @GetMapping("/questions/{companyName}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Question>> getQuestionsByCompanyName(@PathVariable String companyName){

        return ResponseEntity.ok(userService.getQuestionByCompanyName(companyName));
    }



}
