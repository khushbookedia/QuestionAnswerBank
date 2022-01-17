package com.neosoft.QuestionAnswerBank.Dao;

import com.neosoft.QuestionAnswerBank.Entity.ERole;
import com.neosoft.QuestionAnswerBank.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleName(ERole name);
}
