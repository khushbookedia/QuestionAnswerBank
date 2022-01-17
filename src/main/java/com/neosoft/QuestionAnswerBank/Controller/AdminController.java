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
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final UserService userService;

    @PostMapping("/company")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Company> addCompany(@Valid @RequestBody Company company){
        try{
            return ResponseEntity.ok(userService.addCompany(company));
        }
        catch(DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException(ex.getLocalizedMessage());
        }
    }

    @GetMapping("/company")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Company>> getAllCompanies(){
        return ResponseEntity.ok(userService.getAllCompanies());
    }



    @PutMapping("/company/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Company> updateCompany(@Valid @PathVariable int id, @RequestBody Company company){

        return ResponseEntity.ok(userService.updateCompany(company,id));

    }

    @DeleteMapping("/company/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity deleteCompany(@PathVariable int id){
        return ResponseEntity.ok(userService.deleteCompany(id));
    }


    @PostMapping("/questions")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Question> addQuestion(@Valid @RequestBody Question question){
        try{
            return ResponseEntity.ok(userService.addQuestion(question));
        }
        catch(DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException(ex.getLocalizedMessage());
        }
    }


    @GetMapping("/questions")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return ResponseEntity.ok(userService.getAllQuestions());
    }


    @PutMapping("/questions/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Question> updateQuestion(@Valid @PathVariable int id, @RequestBody Question question){

        return ResponseEntity.ok(userService.updateQuestion(question,id));

    }


    @DeleteMapping("/questions/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Question> deleteQuestion(@PathVariable int id){

        return ResponseEntity.ok(userService.deleteQuestion(id));

    }


    @GetMapping("/requests")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<QuestionRequest>> getAllQuestionRequests(){

        return ResponseEntity.ok(userService.getAllRequests());
    }


    @GetMapping("/requests/{companyName}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<QuestionRequest>> getQuestionRequestsByCompanyName(@PathVariable String companyName){

        return ResponseEntity.ok(userService.getQuestionRequestsByCompanyName(companyName));
    }


    @GetMapping("/questions/{companyName}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Question>> getQuestionsByCompanyName(@PathVariable String companyName){

        return ResponseEntity.ok(userService.getQuestionByCompanyName(companyName));
    }



}
