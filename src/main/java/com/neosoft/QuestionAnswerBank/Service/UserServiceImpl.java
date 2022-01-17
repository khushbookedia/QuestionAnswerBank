package com.neosoft.QuestionAnswerBank.Service;

import com.neosoft.QuestionAnswerBank.Dao.CompanyDao;
import com.neosoft.QuestionAnswerBank.Dao.QuestionDao;
import com.neosoft.QuestionAnswerBank.Dao.QuestionRequestDao;
import com.neosoft.QuestionAnswerBank.Entity.Company;
import com.neosoft.QuestionAnswerBank.Entity.Question;
import com.neosoft.QuestionAnswerBank.Entity.QuestionRequest;
import com.neosoft.QuestionAnswerBank.Exception.ResourceNotFoundException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private QuestionRequestDao questionRequestDao;

    @Override
    public Company addCompany(Company company) {

        try {
            return companyDao.save(company);
        }
        catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException(ex.getLocalizedMessage());
        }
    }

    @Override
    public List<Company> getAllCompanies() {

        return companyDao.findAll();
    }

    public Company getCompany(String companyName){
            return companyDao.findByCompanyName(companyName);
    }

    public Company getCompanyById(int companyId){
        return companyDao.findById(companyId).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.BAD_REQUEST,"Invalid Company Id"));
    }

    @Override
    public Company updateCompany(Company company, int companyId) {

        Company savedCompany = getCompanyById(companyId);
        savedCompany.setCompanyName(company.getCompanyName());

        try {
            return companyDao.save(savedCompany);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException(ex.getLocalizedMessage());
        }



    }

    @Override
    public Company deleteCompany(int companyId) {

        Company savedCompany = getCompanyById(companyId);
        companyDao.delete(savedCompany);
        return savedCompany;
    }

    @Override
    public Question addQuestion(Question question) {

        String companyName = question.getCompanyName();

        Company savedCompany = getCompany(companyName);

        if(savedCompany!=null) {
            try {
                return questionDao.save(question);
            }
            catch (DataIntegrityViolationException ex){
                throw new DataIntegrityViolationException(ex.getLocalizedMessage());
            }
        }
        else
            throw new ResourceNotFoundException(HttpStatus.BAD_REQUEST, "Company doesn't exist");

    }

    @Override
    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public Question getQuestionById(int questionId){
        return questionDao.findById(questionId).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.BAD_REQUEST,"Invalid Company Id"));
    }

    @Override
    public Question updateQuestion(Question question, int questionId) {

            Question savedQuestion = getQuestionById(questionId);

            savedQuestion.setDescription(question.getDescription());
            savedQuestion.setAnswer(question.getAnswer());
            savedQuestion.setQuestionId(question.getQuestionId());

            try {
                return questionDao.save(savedQuestion);
            }
            catch (DataIntegrityViolationException ex){
                throw new DataIntegrityViolationException(ex.getLocalizedMessage());
            }
    }

    @Override
    public Question deleteQuestion(int questionId) {

            Question savedQuestion = getQuestionById(questionId);

            questionDao.delete(savedQuestion);
            return savedQuestion;

    }

    @Override
    public List<QuestionRequest> getAllRequests() {
        return questionRequestDao.findAll();
    }

    @Override
    public QuestionRequest addQuestionRequest(QuestionRequest questionRequest) {
        String companyName = questionRequest.getCompanyName();

        Company savedCompany = getCompany(companyName);

        if(savedCompany!=null) {
            try {
                return questionRequestDao.save(questionRequest);
            }
            catch (DataIntegrityViolationException ex) {
                throw new DataIntegrityViolationException(ex.getLocalizedMessage());
            }
        }
        else
            throw new ResourceNotFoundException(HttpStatus.BAD_REQUEST, "Company doesn't exist");
    }

    @Override
    public List<Question> getQuestionByCompanyName(String companyName) throws ResourceNotFoundException{

          List<Question> list = questionDao.findByCompanyName(companyName);

          if(list.size()!=0)
              return list;
          else
              throw new ResourceNotFoundException(HttpStatus.BAD_REQUEST, "No question from this company exist");

    }

    @Override
    public List<QuestionRequest> getQuestionRequestsByCompanyName(String companyName) throws ResourceNotFoundException{

            List<QuestionRequest> list =  questionRequestDao.findByCompanyName(companyName);

            if(list.size()!=0)
                return list;
            else
                throw new ResourceNotFoundException(HttpStatus.BAD_REQUEST, "No question requests from this company exist");

    }
}
