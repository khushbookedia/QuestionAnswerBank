package com.neosoft.QuestionAnswerBank.Dao;

import com.neosoft.QuestionAnswerBank.Entity.QuestionRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRequestDao extends JpaRepository<QuestionRequest, Integer> {

    List<QuestionRequest> findByCompanyName(String companyName);
}
