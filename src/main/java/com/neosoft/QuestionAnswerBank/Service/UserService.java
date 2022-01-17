package com.neosoft.QuestionAnswerBank.Service;

import com.neosoft.QuestionAnswerBank.Entity.Company;
import com.neosoft.QuestionAnswerBank.Entity.Question;
import com.neosoft.QuestionAnswerBank.Entity.QuestionRequest;

import java.util.List;

public interface UserService {

    public Company addCompany(Company company);

    public List<Company> getAllCompanies();

    public Company getCompany(String companyName);

    public Company updateCompany(Company company, int companyId);

    public Company deleteCompany(int companyId);

    public Question addQuestion(Question question);

    public List<Question> getAllQuestions();

    public Question updateQuestion(Question question, int questionId);

    public Question deleteQuestion(int questionId);

    public List<QuestionRequest> getAllRequests();

    public QuestionRequest addQuestionRequest(QuestionRequest questionRequest);

    public List<Question> getQuestionByCompanyName(String companyName);

    public List<QuestionRequest> getQuestionRequestsByCompanyName(String companyName);


}
